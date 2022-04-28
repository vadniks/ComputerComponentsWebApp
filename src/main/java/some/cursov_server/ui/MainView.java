package some.cursov_server.ui;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import static some.cursov_server.Constants.ENDPOINT_INDEX;

@Route(ENDPOINT_INDEX)
public final class MainView extends VerticalLayout {
    private HorizontalLayout header;
    private VerticalLayout componentsList;
    private HorizontalLayout footer;

    public MainView() {
        header = new HorizontalLayout();
        componentsList = new VerticalLayout();
        footer = new HorizontalLayout();

        add(header, componentsList, footer);
    }

    private void initList() {

    }
}
