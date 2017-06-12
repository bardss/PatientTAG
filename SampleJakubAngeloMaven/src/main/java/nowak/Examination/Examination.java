package nowak.Examination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import nowak.BaseEntity;
import nowak.User.User;
import nowak.Views;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "_examination")
public class Examination extends BaseEntity {
    @JsonView(Views.Examination.class)
    LocalDateTime dateOfExamination;
    @JsonView(Views.Examination.class)
    Double temperature;
    @JsonView(Views.Examination.class)
    Double pressure;

    @JsonIgnore
    @ManyToOne
    User user;

}
