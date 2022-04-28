package some.cursov_server.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public final class PcComponent implements Serializable {

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NonNull
    public String  name;

    @NonNull
    public Type    type;

    @NonNull
    public String  description;

    @NonNull
    public Integer cost;

    @NonNull
    public String  image;

    @RequiredArgsConstructor
    public enum Type {
        CPU   (0),
        MB    (1),
        GPU   (2),
        RAM   (3),
        HDD   (4),
        SSD   (5),
        PSU   (6),
        FAN   (7),
        WATER (8),
        CASE  (9);

        public final Integer TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PcComponent)) return false;

        if (Hibernate.getClass(this)
                != Hibernate.getClass(o))
            return false;

        return id != null && Objects.equals(id, ((PcComponent) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public PcComponent clone() {
        return new PcComponent(id, name, type, description, cost, image);
    }
}
