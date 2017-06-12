package nowak.Examination;


import com.fasterxml.jackson.annotation.JsonView;
import nowak.User.User;
import nowak.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/examination")
public class ExaminationController {

    @Autowired
    ExaminationService examinationService;

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> save(@PathVariable("id") Integer userId, @RequestBody Examination examination) {
        return new ResponseEntity<>(examinationService.save(userId,examination), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Examination examination) {
        return new ResponseEntity<>(examination, HttpStatus.OK);
    }
}
