package some.cursov_templates.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import some.cursov_templates.entity.PcComponent;
import some.cursov_templates.repo.ComponentsRepo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static some.cursov_templates.Constants.*;
import static some.cursov_templates.entity.PcComponent.Type.AMOUNT;
import some.cursov_templates.entity.PcComponent.Type;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequiredArgsConstructor
@Service
public class ComponentsService {
    @ImplicitAutowire
    private final ComponentsRepo repo;
    @ImplicitAutowire
    private final SessionFactory sessionFactory;
    private Session session = null;

    public void setSelection(HttpServletRequest request, @Nullable String _id) {
        val session = request.getSession();
        if (_id == null) {
            session.removeAttribute(SESSION_CHOSEN_ITEMS);
            return;
        }
        Items selections;
        if ((selections = (Items) session.getAttribute(SESSION_CHOSEN_ITEMS)) == null)
            selections = new Items(1);


        val id = Integer.parseInt(_id);

        val _component = repo.findById(id);
        if (_component.isEmpty()) throw new IllegalStateException();
        val component = _component.get();

        val t = findByType(selections, component.getType());

        selections.add((t == null ? new StringPairMap() : t)
            .set(ATTRIBUTE_ON_CLICK, FROM_BROWSE_TO_INDEX_WITH_TYPE + component.getType().name())
            .set(COMPONENT_IMAGE, getPathForImage(component.getImage()))
            .set(ENTITY_NAME, component.getName())
            .set(COMPONENT_TYPE, component.getType().REAL_NAME)
            .set(COMPONENT_COST, component.getCost().toString() + DOLLAR)
            .set(COMPONENT_DESCRIPTION, component.getDescription()));

        session.setAttribute(SESSION_CHOSEN_ITEMS, selections);
    }

    public Items getOverviewItems(HttpServletRequest request) {
        val session = request.getSession();
        val chosenItems = (Items) session.getAttribute(SESSION_CHOSEN_ITEMS);

        val items = new Items(AMOUNT);

        for (val type : Type.values()) {
            StringPairMap map;

            if ((map = findByType(chosenItems, type)) == null)
                map = new StringPairMap()
                    .set(ATTRIBUTE_ON_CLICK, FROM_BROWSE_TO_INDEX_WITH_TYPE + type.name())
                    .set(COMPONENT_IMAGE, getStubImagePathByType(type))
                    .set(ENTITY_NAME, UNSELECTED_DESCRIPTION)
                    .set(COMPONENT_TYPE, type.REAL_NAME)
                    .set(COMPONENT_COST, UNSELECTED_COST)
                    .set(COMPONENT_DESCRIPTION, UNSELECTED_DESCRIPTION);
            items.add(map);
        }
        return items;
    }

    @Nullable
    private static StringPairMap findByType(@Nullable Items items, Type type) {
        if (items == null) return null;

        for (val i : items)
            if (i.get(COMPONENT_TYPE).equals(type.REAL_NAME))
                return i;
        return null;
    }

    //TODO: add image for water cooling system
    private static String getStubImagePathByType(Type type) {
        return RESOURCES_DIR_FROM_HTML + ROOT + COMPONENT_IMAGE_PREFIX +
            type.name().toLowerCase() + COMPONENT_IMAGE_POSTFIX;
    }

    public Items getComponentsByType(String type) {
        val components = repo
            .getAllByType(Type.valueOf(type).TYPE);
        val items = new Items(components.size());

        for (val component : components)
            items.add(new StringPairMap()
                .set(ENTITY_ID, Objects.requireNonNull(component.getId()).toString())
                .set(COMPONENT_IMAGE, getPathForImage(component.getImage()))
                .set(ENTITY_NAME, component.getName())
                .set(COMPONENT_COST, component.getCost().toString() + DOLLAR));

        return items;
    }

    private static String getPathForImage(String image) {
        return RESOURCES_BACK_END_DIR_FROM_HTML + ROOT +
            image + COMPONENT_IMAGE_POSTFIX;
    }

    public StringPairMap getComponent(Integer id) {
        val _component = repo.findById(id);
        if (_component.isEmpty()) return null;
        val component = _component.get();

        return new StringPairMap()
            .set(ENTITY_ID, Objects.requireNonNull(component.getId()).toString())
            .set(ENTITY_NAME, component.getName())
            .set(COMPONENT_TYPE, component.getType().name())
            .set(COMPONENT_DESCRIPTION, component.getDescription())
            .set(COMPONENT_COST, component.getCost().toString() + DOLLAR)
            .set(COMPONENT_IMAGE, component.getImage());
    }

    public String getTotalCost(HttpServletRequest request) {
        val chosenItems = (Items) request.getSession().getAttribute(SESSION_CHOSEN_ITEMS);

        var cost = 0;
        if (chosenItems != null)
            for (val i : chosenItems)
                cost += toInt(rmDollar(i.get(COMPONENT_COST)));

        return TOTAL_COST + toStr(cost) + DOLLAR;
    }

    public List<PcComponent> getAllComponents() {
        return repo.findAll();
    }

    public void removeComponent(Integer id) {
        repo.deleteById(id);
    }

    public void saveComponent(PcComponent component) {
        repo.save(component);
    }

    public List<PcComponent> selectComponents(String byWhich, String selection) {
        val parameter = switch (byWhich) {
            case ENTITY_ID, COMPONENT_COST -> toInt(selection);
            case COMPONENT_TYPE -> Type.valueOf(selection).TYPE;
            case ENTITY_NAME, COMPONENT_DESCRIPTION, COMPONENT_IMAGE -> selection;
            default -> throw new IllegalArgumentException();
        };

        val builder = session.getCriteriaBuilder();
        val query = builder.createQuery(PcComponent.class);
        val root = query.from(PcComponent.class);

        query
            .select(root)
            .where(builder.equal(root.get(byWhich), parameter));

        return session.createQuery(query).getResultList();
    }

    @PostConstruct
    public void postConstruct() {
        session = sessionFactory.openSession();

        //TODO: debug only
        if (repo.findAll().isEmpty())
            test();
    }

    @PreDestroy
    public void preDestroy() {
        session.close();
    }

    @TestOnly
    @Deprecated
    public void test() {
        repo.save(new PcComponent(
            "Intel Core I5",
            Type.CPU,
            "Intel Core I5",
            800,
            "intel_i5"));
        repo.save(new PcComponent(
            "AMD Ryzen 5 5600X",
            Type.CPU,
            "AMD Ryzen 5 5600X",
            700,
            "amd_r5"));
        repo.save(new PcComponent(
            "AMD Ryzen 5 3600X",
            Type.CPU,
            "AMD Ryzen 5 3600X",
            700,
            "amd_r5"));
        repo.save(new PcComponent(
            "Asus Prime Z590-P",
            Type.MB,
            "Asus Prime Z590-P",
            400,
            "asus_prime_mb"
        ));
        repo.save(new PcComponent(
            "Gigabyte Aorus X570 Elite",
            Type.MB,
            "Gigabyte Aorus X570 Elite",
            450,
            "gigabyte_aorus_mb"
        ));
        repo.save(new PcComponent(
            "Maxsun Radeon RX550",
            Type.GPU,
            "Maxsun Radeon RX550",
            750,
            "maxsun_rx550_gpu"
        ));
        repo.save(new PcComponent(
            "Asus GeForce GTX 1650",
            Type.GPU,
            "Asus GeForce GTX 1650",
            750,
            "asus_1650_gpu"));
        repo.save(new PcComponent(
            "Micron Crucial DDR4 SODIMM 8Gb",
            Type.RAM,
            "Micron Crucial DDR4 SODIMM 8Gb",
            300,
            "crucial_ram"));
        repo.save(new PcComponent(
            "GSkill DDR4 DIMM 16Gb",
            Type.RAM,
            "GSkill DDR4 DIMM 16Gb",
            400,
            "gskill_ram"));
        repo.save(new PcComponent(
            "Hitachi 1Tb 250 Mb/sec",
            Type.HDD,
            "Hitachi 1Tb 250 Mb/sec",
            200,
            "hitachi_hdd"));
        repo.save(new PcComponent(
            "MaxDigital 1Tb 250 Mb/sec",
            Type.HDD,
            "MaxDigital 1Tb 250 Mb/sec",
            200,
            "maxdigital_hdd"));
        repo.save(new PcComponent(
            "Kingston SATA 1Tb 1000Mb/Sec",
            Type.SSD,
            "Kingston SATA 1Tb 1000Mb/Sec",
            500,
            "kingston_ssd"));
        repo.save(new PcComponent(
            "Sabrent M2 PCIExpress 1Tb 1000Mb/Sec",
            Type.SSD,
            "Sabrent M2 PCIExpress 1Tb 1000Mb/Sec",
            500,
            "sabrent_ssd"));
        repo.save(new PcComponent(
            "Corsair RM 850x 500W",
            Type.PSU,
            "Corsair RM 850x 500W",
            200,
            "corsair_psu"));
        repo.save(new PcComponent(
            "Cooler Master MWE 750W",
            Type.PSU,
            "Cooler Master MWE 750W",
            500,
            "cooler_master_psu"));
        repo.save(new PcComponent(
            "Corsair A50 AM4",
            Type.FAN,
            "Corsair A50 AM4",
            100,
            "corsair_fan"));
        repo.save(new PcComponent(
            "Noctua R200 AM3",
            Type.FAN,
            "Noctua R200 AM3",
            80,
            "noctua_fan"));
        repo.save(new PcComponent(
            "Cooler Master LQD",
            Type.WATER,
            "Cooler Master LQD",
            100,
            "cooler_master_lqd_fan"));
        repo.save(new PcComponent(
            "Musetex ALT 905",
            Type.CASE,
            "Musetex ALT 905",
            125,
            "musetex_case"));
        repo.save(new PcComponent(
            "Phanteks S5",
            Type.CASE,
            "Phanteks S5",
            200,
            "phanteks_case"));
        repo.save(new PcComponent(
            "Thermaltake ZTX",
            Type.CASE,
            "Thermaltake ZTX",
            190,
            "thermaltake_case"));
    }
}
