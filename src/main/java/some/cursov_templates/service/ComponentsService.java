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
    private final ComponentsRepo repo;
    @Value("classpath:static")
    private Resource resDir;

    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getOverviewItems(HttpServletRequest request) {
        val session = request.getSession();
        val chosenItems = (List<Map<String, String>>) session.getAttribute(SESSION_CHOSEN_ITEMS);

        if (chosenItems == null) {
            val items = new ArrayList<Map<String, String>>(AMOUNT);
            val types = Type.values();

            for (int i = 0; i < AMOUNT; i++) {
                val type = types[i];

                val map = new HashMap<String, String>();
                map.put("image", getImagePathByType(type));
                map.put("name", type.name());
                map.put("cost", UNSELECTED_COST);
                map.put("details", UNSELECTED_DETAILS);

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
}
