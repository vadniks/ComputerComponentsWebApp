package some.cursov_server.service;

import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import some.cursov_server.repo.ComponentsRepo;
import some.cursov_server.ui.Utils;

@UIScope
@RequiredArgsConstructor
@Service
public class MainPageService implements IPresenter {
    private final ComponentsRepo repo;
    @Value("classpath:static")
    private Resource resDir;

    @SneakyThrows
    public AbstractStreamResource getLogo() {
        return Utils.getResourceAsStream("pc_icon3.svg", resDir);
    }
}
