package some.cursov_templates.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.TestOnly;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import some.cursov_templates.entity.User;
import some.cursov_templates.repo.UsersRepo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

import static some.cursov_templates.Constants.*;
import static some.cursov_templates.entity.User.Role;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
@RequiredArgsConstructor
@Service
public class UsersService implements UserDetailsService {
    @ImplicitAutowire
    private final UsersRepo repo;
    @ImplicitAutowire
    private final SessionFactory sessionFactory;
    private Session session;

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

    @Deprecated
    @TestOnly
    public void test() {
        repo.save(new User(
            "admin",
            Role.ADMIN,
            new BCryptPasswordEncoder().encode("admin")));
    }

    public List<User> selectUsers(String byWhich, String selection) {
        val parameter = switch (byWhich) {
            case ENTITY_ID -> toInt(selection);
            case USER_ROLE -> Role.valueOf(selection).ROLE;
            case ENTITY_NAME, USER_PASSWORD -> selection;
            default -> throw new IllegalArgumentException();
        };

        val builder = session.getCriteriaBuilder();
        val query = builder.createQuery(User.class);
        val root = query.from(User.class);

        query
            .select(root)
            .where(builder.equal(root.get(byWhich), parameter));

        return session.createQuery(query).getResultList();
    }

    @PostConstruct
    public void postConstruct() {
        session = sessionFactory.openSession();
    }

    @PreDestroy
    public void preDestroy() {
        session.close();
    }
}
