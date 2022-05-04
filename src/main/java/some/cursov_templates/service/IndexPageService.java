package some.cursov_templates.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import some.cursov_templates.entity.OverviewItem;
import some.cursov_templates.entity.PcComponent;
import some.cursov_templates.repo.ComponentsRepo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static some.cursov_templates.Constants.*;

@RequiredArgsConstructor
@Service
public class IndexPageService implements IPresenter {
    private final ComponentsRepo repo;
    @Value("classpath:static")
    private Resource resDir;

    @SuppressWarnings("unchecked")
    public List<OverviewItem> getOverviewItems(HttpServletRequest request) {
        val session = request.getSession();
        val chosenItems = (List<OverviewItem>) session.getAttribute(SESSION_CHOSEN_ITEMS);

        if (chosenItems == null) {
            val items = new ArrayList<OverviewItem>(PcComponent.Type.AMOUNT);

        }
        return chosenItems;
    }
}
