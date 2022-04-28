package some.cursov_server.ui;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
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
        componentsList = new VerticalLayout();
        footer = new HorizontalLayout();

        add(header, componentsList, footer, new ListItem(new PcComponent(
            "Name", PcComponent.Type.CASE, "Description", 100, "Image")));

    }

    @SuppressWarnings("FieldCanBeLocal") // For the future
    public final static class ListItem extends HorizontalLayout {
        final PcComponent component;
        final VerticalLayout texts = new VerticalLayout();
        final TextField name = new TextField();
        final TextField type = new TextField();
        final TextField description = new TextField();
        final TextField cost = new TextField();

        public ListItem(PcComponent _component) {
            component = _component;

            name.setLabel(component.name);
            type.setLabel(component.type.name());
            description.setLabel(component.description);
            cost.setLabel(component.cost.toString());

            texts.add(name, type, cost);
            add(texts, description);
        }
    }
}
