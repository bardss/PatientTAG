package nowak;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.swing.text.View;
import java.io.Serializable;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Base.class)
    private Integer id;
}

