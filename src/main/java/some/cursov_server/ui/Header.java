package some.cursov_server.ui;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.AbstractStreamResource;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static some.cursov_server.Constants.*;
import static some.cursov_server.ui.Utils.setFont;

@RequiredArgsConstructor
public class Header extends Composite<HorizontalLayout> {
    private final AbstractStreamResource logo;

    @Override
    protected HorizontalLayout initContent() {
        val image = new Image(logo, EMPTY);
        image.setHeight("100px");
        image.setWidth("100px");

        val name = new Span(APP_NAME);
        setFont(name);

        val since = new Span(APP_SINCE);
        setFont(since);

        val slogan = new Span(APP_SLOGAN);
        setFont(slogan);

        val texts = new VerticalLayout(name, since, slogan);
        Utils.removeMarginAndPaddingAndSpacing(texts);
        texts.setSizeFull();

        val layout = new HorizontalLayout(image, texts);
        Utils.removeMarginAndPaddingAndSpacing(layout);
        layout.setWidthFull();
        return layout;
    }
}
