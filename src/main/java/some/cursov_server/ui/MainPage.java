package some.cursov_server.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import some.cursov_server.service.MainPageService;

import static some.cursov_server.Constants.*;

@Route(ENDPOINT_INDEX)
@PageTitle(APP_NAME)
public final class MainPage extends VerticalLayout implements IView {
    private final MainPageService service;
    private Header header;

    public MainPage(@Autowired MainPageService _service) {
        service = _service;
        setSizeFull();

        header = new Header(service.getLogo());

        Utils.removeMarginAndPaddingAndSpacing(this);
        add(header);
    }
}
