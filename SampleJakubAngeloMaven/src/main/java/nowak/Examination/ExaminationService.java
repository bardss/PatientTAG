package nowak.Examination;

import nowak.User.User;
import nowak.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationService {
    @Autowired
    ExaminationRepository examinationRepository;

    @Autowired
    UserService userService;

    public Examination save(Integer userId, Examination examination){
        User user = userService.findById(userId);
        examination.setUser(user);
        return examinationRepository.save(examination);
    }
}
