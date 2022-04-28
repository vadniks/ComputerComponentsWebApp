package some.cursov_server.entity;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class User implements Serializable {

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

    @RequiredArgsConstructor
    public enum Role {
        USER  (0),
        ADMIN (1);

        public final Integer ROLE;
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
    protected Object clone() {
        val a = new User(name, role, password);
        a.id = id;
        return a;
    }
}
