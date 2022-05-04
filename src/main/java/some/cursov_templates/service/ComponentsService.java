package some.cursov_templates.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
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

    public Items getOverviewItems(HttpServletRequest request) {
        val session = request.getSession();
        val chosenItems = (Items) session.getAttribute(SESSION_CHOSEN_ITEMS);

        if (chosenItems == null) {
            val items = new Items(AMOUNT);
            val types = Type.values();

            for (int i = 0; i < AMOUNT; i++) {
                val type = types[i];

                val map = new StringPairMap();
                map.put(IMAGE, getImagePathByType(type));
                map.put(NAME, type.name());
                map.put(COST, UNSELECTED_COST);
                map.put(DESCRIPTION, UNSELECTED_DESCRIPTION);

                items.add(map);
            }
            return items;
        }
        return chosenItems;
    }

    //TODO: add image for water cooling system
    private static String getImagePathByType(Type type) {
        return RESOURCES_DIR_FROM_HTML + "/pc_" + type.name().toLowerCase() + ".jpg";
    }

    public Items getComponentsByType(String type) {
        val components = componentsRepo
            .getAllByType(Type.valueOf(type).TYPE);
        val items = new Items(components.size());

        for (val component : components) {
            val map = new StringPairMap();
            map.put(IMAGE, component.image);
            map.put(NAME, component.name);
            map.put(COST, component.cost.toString());
//            map.put(DESCRIPTION, component.description);

            items.add(map);
        }

        return items;
    }

    // aka alias for the extended type
    public static class StringPairMap extends HashMap<String, String>
    { public StringPairMap() { super(); } }

    // aka alias for the extended type
    public static class Items extends ArrayList<Map<String, String>>
    { public Items(int initialCapacity) { super(initialCapacity); } }
}
