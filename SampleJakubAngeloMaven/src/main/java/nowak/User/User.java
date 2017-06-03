package nowak.User;

import lombok.Data;
import nowak.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "_user")
public class User extends BaseEntity {

    private Integer id;
    private String name;
    private String surname;
}
