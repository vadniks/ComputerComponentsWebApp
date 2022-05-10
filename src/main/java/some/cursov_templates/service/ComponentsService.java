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
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
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
        val component = repo.getById(id);
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

    @TestOnly
    @Deprecated
    public void test() {
        repo.save(new PcComponent(
            "Asus GeForce GTX 1650",
            Type.GPU,
            "Asus GeForce GTX 1650",
            750,
            "asus_1650_gpu"));
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
    }

    @PreDestroy
    public void preDestroy() {
        session.close();
    }
}
