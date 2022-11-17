package mobitech.aleksbakin.test_assignment.controller;

import mobitech.aleksbakin.test_assignment.domain.Role;
import mobitech.aleksbakin.test_assignment.domain.User;
import mobitech.aleksbakin.test_assignment.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
//        User userFromDb = userRepo.findByUsername(user.getUsername());
        User userFromDb = userRepo.findByEmail(user.getEmail());
        if(userFromDb != null) {
            model.put("message", "User already exists!");
            return "registration";
        }
//        String loginPattern1 = "^([a-zA-Z0-9][a-zA-Z0-9-_]?){7,19}[a-zA-Z0-9]$";
//        String loginPattern2 = "^.{8,20}$";
//        if(!user.getUsername().matches(loginPattern1)|| !user.getUsername().matches(loginPattern2)) {
//            model.put("message", "Username must contain 8-20 symbols. " +
//                    "You may use low and UPPER case English letters and numbers. " +
//                    "You may also divide word using '_' or '-' symbols. " +
//                    "Try one more time.");
//            return "registration";
//        }
//        String passPattern1 = ".*[!-/:-@^-`{-~]+.*";
//        String passPattern2 = ".*[a-z]+.*";
//        String passPattern3 = ".*[A-Z]+.*";
//        String passPattern4 = ".*[0-9]+.*";
//        String passPattern5 = "^.{8,30}$";
//        if(!user.getPassword().matches(passPattern1) || !user.getPassword().matches(passPattern2) ||
//                !user.getPassword().matches(passPattern3) || !user.getPassword().matches(passPattern4) ||
//                !user.getPassword().matches(passPattern5)) {
//            model.put("message", "Password must contain 8-30 symbols. " +
//                    "You need to use at lest one low and one UPPER case English letter. " +
//                    "You need to use at lest one number and one special symbol from list: '!\"#$%&'()*+,-./:;<=>?@^_`{|}~'. " +
//                    "Try one more time.");
//
//            return "registration";
//        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        //return "redirect:/main";
        return "redirect:/main?name="+user.getName();
    }
}
