package some.cursov_templates.entity;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static some.cursov_templates.Constants.USER_ROLE;
import static some.cursov_templates.Constants.TABLE_USERS;

@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = TABLE_USERS)
@Entity
public class User implements Serializable, UserDetails {
    @Serial
    private static final long serialVersionUID = 4822085747731748307L;

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id = null;

    @NonNull
    public String  name;

    @NonNull
    public Role role;

    @NonNull
    public String password; // hash

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
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(id, user.id) && name.equals(user.name) &&
            role == user.role && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role, password);
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Object clone() {
        val a = new User(name, role, password);
        a.id = id;
        return a;
    }

    @Override
    public String toString() {
        return "User(%d %s %s %s)".formatted(id, name, role, password);
    }
}
