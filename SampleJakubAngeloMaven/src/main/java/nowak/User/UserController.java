package nowak.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<Object>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/userById/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Integer id) {
        return new ResponseEntity<Object>(userService.findUserById(id), HttpStatus.OK);
    }
}
