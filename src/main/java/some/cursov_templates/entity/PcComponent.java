package some.cursov_templates.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import static some.cursov_templates.Constants.TABLE_COMPONENTS;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = TABLE_COMPONENTS)
@Entity
public class PcComponent implements Serializable {

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    @NonNull
    private String name;

    @NonNull
    private Type type;

    @NonNull
    private String description;

    @NonNull
    private Integer cost;

    @NonNull
    private String image;

    @RequiredArgsConstructor
    public enum Type {
        CPU   (0, "Processor"),
        MB    (1, "Motherboard"),
        GPU   (2, "Graphics adapter"),
        RAM   (3, "Operating memory"),
        HDD   (4, "Hard drive"),
        SSD   (5, "Solid state drive"),
        PSU   (6, "Power supply unit"),
        FAN   (7, "Cooler"),
        WATER (8, "Water cooling system"),
        CASE  (9, "Case");

        public final Integer TYPE;
        public final String REAL_NAME;
        public static final int AMOUNT = 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PcComponent that = (PcComponent) o;
        return Objects.equals(id, that.id) && name.equals(that.name) &&
            type == that.type && description.equals(that.description) &&
            cost.equals(that.cost) && image.equals(that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description, cost, image);
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public PcComponent clone() {
        val a = new PcComponent(name, type, description, cost, image);
        a.id = id;
        return a;
    }

    @Override
    public String toString() {
        return "PcComponent(%d %s %s %s %d %s)"
            .formatted(id, name, type, description, cost, image);
    }
}
