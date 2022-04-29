package some.cursov_server.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import some.cursov_server.entity.PcComponent;
import some.cursov_server.service.ComponentsService;

import static some.cursov_server.Constants.*;

@Route(ENDPOINT_INDEX)
@PageTitle(PAGE_INDEX)
public final class MainPage extends UI implements IView {
    private HorizontalLayout header;
    private VerticalLayout componentsList;
    private HorizontalLayout footer;
    private ComponentsService componentsService;

    public MainPage() {
        header = new HorizontalLayout();
        header.add(new Span("PC Configurator"));
        header.getStyle().set("margin", "5px").set("padding", "5px");

        componentsList = new VerticalLayout();
        componentsList.getStyle().set("margin", "5px").set("padding", "5px");
        footer = new HorizontalLayout();
        footer.getElement().getStyle().clear().set("margin", "5px").set("padding", "5px");

        add(header/*, componentsList, footer*/);
        getElement().getStyle().clear().set("margin", "5px").set("padding", "5px");

        componentsList.add(new ListItem(new PcComponent(
            "Name", PcComponent.Type.CASE, "Description", 100, "Image")));
        componentsList.add(new ListItem(new PcComponent(
            "Name2", PcComponent.Type.CASE, "Description2", 200, "Image2")));
    }

    @SuppressWarnings("FieldCanBeLocal") // For the future
    public final static class ListItem extends HorizontalLayout {
        final PcComponent component;
        final VerticalLayout texts = new VerticalLayout();
        final Span name = new Span();
        final Span type = new Span();
        final Span description = new Span();
        final Span cost = new Span();

        public ListItem(PcComponent _component) {
            component = _component;

            name.getStyle().set("margin", "5px").set("padding", "5px");
            name.setText(component.name);

            type.setText(component.type.name());
            type.getStyle().set("margin", "5px").set("padding", "5px");

            description.setText(component.description);
            description.getStyle().set("margin", "5px").set("padding", "5px");

            cost.setText(component.cost.toString());
            cost.getStyle().set("margin", "5px").set("padding", "5px");

            texts.add(name, type, cost);
            texts.getStyle().set("margin", "5px").set("padding", "5px");

            add(texts, description);
            getStyle().set("margin", "5px").set("padding", "5px");
        }
    }
}
