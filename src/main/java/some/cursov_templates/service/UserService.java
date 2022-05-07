package some.cursov_templates.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import some.cursov_templates.repo.UsersRepo;

import static some.cursov_templates.Constants.*;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    @ImplicitAutowire
    private final UsersRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        val a = repo.getByName(username);
        if (a == null) throw new UsernameNotFoundException(EMPTY);
        return a;
    }
}
