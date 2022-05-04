package some.cursov_templates.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import some.cursov_templates.repo.ComponentsRepo;

@RequiredArgsConstructor
@Service
public class MainPageService implements IPresenter {
    private final ComponentsRepo repo;
    @Value("classpath:static")
    private Resource resDir;


}
