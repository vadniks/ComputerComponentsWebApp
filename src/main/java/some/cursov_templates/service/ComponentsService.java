package some.cursov_templates.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import some.cursov_templates.entity.PcComponent;
import some.cursov_templates.repo.ComponentsRepo;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static some.cursov_templates.Constants.*;
import static some.cursov_templates.entity.PcComponent.Type.AMOUNT;
import some.cursov_templates.entity.PcComponent.Type;

@RequiredArgsConstructor
@Service
public class ComponentsService {
    private final ComponentsRepo componentsRepo;
    @Value("classpath:static")
    private Resource resDir;

    public void setSelection(HttpServletRequest request, @Nullable String _id) {
        val session = request.getSession();
        if (_id == null) {
            session.removeAttribute(SESSION_CHOSEN_ITEMS);
            return;
        }
        System.out.println("rgvfrbrfgv " + _id);
        Items selections;
        if ((selections = (Items) session.getAttribute(SESSION_CHOSEN_ITEMS)) == null)
            selections = new Items(1);


        val id = Integer.parseInt(_id);
        val component = componentsRepo.getById(id);
        val t = findByType(selections, component.type);

        selections.add((t == null ? new StringPairMap() : t)
            .set(ATTRIBUTE_ON_CLICK, FROM_BROWSE_TO_INDEX_WITH_TYPE + component.type.name())
            .set(IMAGE, getPathForImage(component.image))
            .set(NAME, component.name)
            .set(TYPE, component.type.REAL_NAME)
            .set(COST, component.cost.toString() + '$')
            .set(DESCRIPTION, component.description));

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
                    .set(IMAGE, getStubImagePathByType(type))
                    .set(NAME, UNSELECTED_DESCRIPTION)
                    .set(TYPE, type.REAL_NAME)
                    .set(COST, UNSELECTED_COST)
                    .set(DESCRIPTION, UNSELECTED_DESCRIPTION);
            items.add(map);
        }
        return items;
    }

    @Nullable
    private static StringPairMap findByType(@Nullable Items items, Type type) {
        if (items == null) return null;

        for (val i : items)
            if (i.get(TYPE).equals(type.REAL_NAME))
                return i;
        return null;
    }

    //TODO: add image for water cooling system
    private static String getStubImagePathByType(Type type) {
        return RESOURCES_DIR_FROM_HTML + '/' + COMPONENT_IMAGE_PREFIX +
            type.name().toLowerCase() + COMPONENT_IMAGE_POSTFIX;
    }

    public Items getComponentsByType(String type) {
        val components = componentsRepo
            .getAllByType(Type.valueOf(type).TYPE);
        val items = new Items(components.size());

        for (val component : components)
            items.add(new StringPairMap()
                .set(ID, Objects.requireNonNull(component.id).toString())
                .set(IMAGE, getPathForImage(component.image))
                .set(NAME, component.name)
                .set(COST, component.cost.toString() + '$'));

        return items;
    }

    private static String getPathForImage(String image) {
        return RESOURCES_BACK_END_DIR_FROM_HTML + '/' +
            image + COMPONENT_IMAGE_POSTFIX;
    }

    @TestOnly
    void test() {
        System.out.println("gvbftghbtrhgbt");
        componentsRepo.save(new PcComponent(
            "Asus GeForce GTX 1650",
            Type.GPU,
            "Asus GeForce GTX 1650",
            750,
            "asus_1650_gpu"));
    }

    public StringPairMap getComponent(Integer id) {
        val _component = componentsRepo.findById(id);
        if (_component.isEmpty()) return null;
        val component = _component.get();

        return new StringPairMap()
            .set(ID, Objects.requireNonNull(component.id).toString())
            .set(NAME, component.name)
            .set(TYPE, component.type.name())
            .set(DESCRIPTION, component.description)
            .set(COST, component.cost.toString() + '$')
            .set(IMAGE, component.image);
    }

    // aka alias for the extended type
    public static class StringPairMap extends HashMap<String, String> {
        public StringPairMap() { super(); }
        public StringPairMap set(String a, String b) { put(a, b); return this; }
    }

    // aka alias for the extended type
    public static class Items extends ArrayList<StringPairMap>
    { public Items(int initialCapacity) { super(initialCapacity); } }
}
