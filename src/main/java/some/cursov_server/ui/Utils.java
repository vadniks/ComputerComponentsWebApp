package some.cursov_server.ui;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.server.StreamResource;
import lombok.SneakyThrows;

import static some.cursov_server.Constants.HEAD_FONT;
import static some.cursov_server.Constants.STYLE_FONT;

public class Utils {

    public static void setFont(Span span) {
        span.getStyle().set(STYLE_FONT, HEAD_FONT);
    }

    public static StreamResource getResourceAsStream(String file) {
        return new StreamResource(file, () ->
            Utils.class.getClassLoader().getResourceAsStream(file));
    }

    @SneakyThrows
    private Utils() { throw new IllegalAccessException(); }
}
