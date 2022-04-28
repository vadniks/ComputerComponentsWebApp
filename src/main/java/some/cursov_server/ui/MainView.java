package some.cursov_server.ui;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.val;
import some.cursov_server.entity.PcComponent;
import some.cursov_server.service.ComponentsService;

import static some.cursov_server.Constants.ENDPOINT_INDEX;

@Route(ENDPOINT_INDEX)
public final class MainView extends VerticalLayout implements IView {
    private HorizontalLayout header;
    private VerticalLayout componentsList;
    private HorizontalLayout footer;
    private ComponentsService componentsService;

    public MainView() {
        header = new HorizontalLayout();
        header.add(new H1("PC Configurator"));

        componentsList = new VerticalLayout();
        footer = new HorizontalLayout();

        add(header, componentsList, footer);

        componentsList.add(new ListItem(new PcComponent(
            "Name", PcComponent.Type.CASE, "Description", 100, "Image")));
    }

    @SuppressWarnings("FieldCanBeLocal") // For the future
    public final static class ListItem extends HorizontalLayout {
        final PcComponent component;
        final VerticalLayout texts = new VerticalLayout();
        final Label name = new Label();
        final Label type = new Label();
        final Label description = new Label();
        final Label cost = new Label();

        public ListItem(PcComponent _component) {
            component = _component;

            name.setText(component.name);
            type.setText(component.type.name());
            description.setText(component.description);
            cost.setText(component.cost.toString());

            texts.add(name, type, cost);
            add(texts, description);
        }
    }
}
