package some.cursov_templates.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import some.cursov_templates.entity.User;
import some.cursov_templates.repo.UsersRepo;

import java.util.List;

import static some.cursov_templates.Constants.*;
import static some.cursov_templates.entity.User.Role;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequiredArgsConstructor
@Service
public class UsersService implements UserDetailsService {
    @ImplicitAutowire
    private final UsersRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        val a = repo.getByName(username);
        if (a == null) throw new UsernameNotFoundException(EMPTY);
        return a;
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUser(Integer id) {
        return repo.getById(id);
    }

    public void removeUser(Integer id) {
        repo.deleteById(id);;
    }

    public void saveUser(User user) {
        repo.save(user);
    }

    public List<User> selectUsers(String byWhich, String selection) {
        return repo.getAllBy(byWhich, switch (byWhich) {
            case ENTITY_ID -> toInt(selection);
            case USER_ROLE -> Role.valueOf(selection).ROLE;
            case ENTITY_NAME, USER_PASSWORD -> selection;
            default -> throw new IllegalArgumentException();
        });
    }
}
