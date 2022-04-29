package some.cursov_server.ui;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.val;

import static some.cursov_server.Constants.*;
import static some.cursov_server.ui.Utils.setFont;

public class Header extends Composite<HorizontalLayout> {

    @Override
    protected HorizontalLayout initContent() {
        val image = new Image();

        val name = new Span(APP_NAME);
        setFont(name);

        val since = new Span(APP_SINCE);
        setFont(since);

        val slogan = new Span(APP_SLOGAN);
        setFont(slogan);

        val texts = new VerticalLayout(name, since, slogan);
        texts.setSizeFull();

        val layout = new HorizontalLayout(texts);
        layout.setWidthFull();
        return layout;
    }
}
