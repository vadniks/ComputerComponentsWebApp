package some.cursov_server.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public final class PcComponent implements Serializable {

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id = null;

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

    @ManyToOne
    @Nullable
    public User buyer = null;

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
        if (o == null || getClass() != o.getClass()) return false;

        PcComponent that = (PcComponent) o;
        return Objects.equals(id, that.id) && name.equals(that.name) &&
            type == that.type && description.equals(that.description) &&
            cost.equals(that.cost) && image.equals(that.image) &&
            Objects.equals(buyer, that.buyer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description, cost, image, buyer);
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public PcComponent clone() {
        val a = new PcComponent(name, type, description, cost, image);
        a.id = id;
        a.buyer = buyer;
        return a;
    }
}
