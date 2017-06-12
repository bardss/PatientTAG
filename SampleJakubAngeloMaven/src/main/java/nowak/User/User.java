package nowak.User;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import nowak.BaseEntity;
import nowak.Examination.Examination;
import nowak.Views;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "_user")
public class User extends BaseEntity {

    @JsonView(Views.User.class)
    private String name;
    @JsonView(Views.User.class)
    private String surname;
    @JsonView(Views.User.class)
    private String dateOfBirth;
    @JsonView(Views.User.class)
    private Sex sex;

    @JsonView(Views.User.class)
    @OneToMany(mappedBy = "user")
    List<Examination> examinations;
}
