package some.cursov_server.entity;

import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public final class PcComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String name;
    public Type type;
    public String description;
    public Integer cost;
    public String image;

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
}
