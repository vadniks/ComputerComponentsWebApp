package some.cursov_server.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;
import com.vaadin.flow.server.StreamResource;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;

import static some.cursov_server.Constants.HEAD_FONT;
import static some.cursov_server.Constants.STYLE_FONT;

public class Utils {

    public static void setFont(Span span) {
        span.getStyle().set(STYLE_FONT, HEAD_FONT);
    }

    @SneakyThrows
    public static StreamResource getResourceAsStream(String name, Resource dir) {
        val stream = new FileInputStream(dir.getFile().getAbsolutePath() + '/' + name);
        return new StreamResource(name, () -> stream);
    }

    public static void removeMarginAndPaddingAndSpacing(ThemableLayout layout) {
        layout.setMargin(false);
        layout.setPadding(false);
        layout.setSpacing(false);
    }

    public static void setDefaultMargin(Component component) {
        component.getElement().getStyle().set("margin", "5px");
    }

    @SneakyThrows
    private Utils() { throw new IllegalAccessException(); }
}
