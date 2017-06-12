package nowak.User;

import nowak.Examination.Examination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public User save(User examination) {
        return userRepository.save(examination);
    }

    public User findById(Integer id){
        return userRepository.findOne(id);
    }
}
