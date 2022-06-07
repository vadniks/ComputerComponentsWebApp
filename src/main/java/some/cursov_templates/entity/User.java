package some.cursov_templates.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static some.cursov_templates.Constants.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = TABLE_USERS)
@Entity
public class User implements Serializable, UserDetails {

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    @NonNull
    private String name;

    @NonNull
    private Role role;

    @NonNull
    private String password; // hash

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private String phone;

    @Nullable
    private String address;

    @NonNull
    @OneToMany
    private List<PcComponent> selection = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
            new SimpleGrantedAuthority(role.mkRole()));
    }

    @NonNull
    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return name; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @RequiredArgsConstructor
    public enum Role {
        USER  (0),
        ADMIN (1);

        public final Integer ROLE;

        public String mkRole() { return "%s%d".formatted(USER_ROLE, ROLE); }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        return Objects.equals(id, user.id) && name.equals(user.name) &&
            role == user.role && password.equals(user.password) &&
            Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) &&
            Objects.equals(phone, user.phone) && Objects.equals(address, user.address) &&
            selection.equals(user.selection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role, password, firstName, lastName, phone, address, selection);
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Object clone() {
        val a = new User(name, role, password);
        a.id = id;
        a.firstName = firstName;
        a.lastName = lastName;
        a.phone = phone;
        a.address = address;
        a.selection.addAll(selection);
        return a;
    }

    @Override
    public String toString() {
        return "User(%d %s %s %s %s %s %s %s %d)".formatted(id, name, role, !password.isEmpty(),
            firstName, lastName, phone, address, selection.size());
    }
}
