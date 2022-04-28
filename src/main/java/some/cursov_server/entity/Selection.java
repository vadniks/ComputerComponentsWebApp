package some.cursov_server.entity;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.List;

import static some.cursov_server.Constants.TABLE_SELECTIONS;

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
