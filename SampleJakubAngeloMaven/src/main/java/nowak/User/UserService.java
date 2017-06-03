package nowak.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();}

    public User findUserById(int id) {
        User user =  userRepository.findById(id);
        user.toString();
        return user;
    }
}
