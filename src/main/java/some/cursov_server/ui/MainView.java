package some.cursov_server.ui;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public final class MainView extends VerticalLayout {
    private HorizontalLayout header;
    private VerticalLayout componentsList;
    private HorizontalLayout footer;

    public MainView() {
        header = new HorizontalLayout();
        componentsList = new VerticalLayout();
        footer = new HorizontalLayout();

        componentsList.add();
    }
}
