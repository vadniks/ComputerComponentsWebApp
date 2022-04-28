package some.cursov_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import some.cursov_server.repo.ComponentsRepo;

@RequiredArgsConstructor
@Service
public class ComponentsService implements IPresenter {
    private final ComponentsRepo repo;

    public void onConstruct() {

    }
}
