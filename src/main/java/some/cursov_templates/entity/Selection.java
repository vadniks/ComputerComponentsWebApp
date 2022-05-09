package some.cursov_templates.entity;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.List;

import static some.cursov_templates.Constants.TABLE_SELECTIONS;

@Deprecated(forRemoval = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = TABLE_SELECTIONS)
@Entity
public class Selection {

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id = null;

    @OneToOne
    @NonNull
    public User buyer;

    @OneToMany
    @NonNull
    public List<PcComponent> bought;
}
