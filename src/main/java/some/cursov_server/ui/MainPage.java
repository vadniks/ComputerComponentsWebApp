package some.cursov_server.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static some.cursov_server.Constants.*;

@Route(ENDPOINT_INDEX)
@PageTitle(APP_NAME)
public final class MainPage extends VerticalLayout implements IView {
    private Header header;

    public MainPage() {
        setSizeFull();

        header = new Header();

        add(header);
    }
}
