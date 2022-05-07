package some.cursov_templates.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import some.cursov_templates.repo.UsersRepo;

import static some.cursov_templates.Constants.*;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @ImplicitAutowire
    private final UsersRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.getByName(username);
    }
}
