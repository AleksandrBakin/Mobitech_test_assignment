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
        // check if user with this email already exists
        User userFromDb = userRepo.findByEmail(user.getEmail());
        if(userFromDb != null) {
            model.put("message", "User already exists!");
            return "registration";
        }

        // check if e-mail correct
        String emailPattern1 = "^[a-zA-Z0-9][a-zA-Z0-9_.-]*@[a-zA-Z0-9._-]*[.][a-zA-Z]*$";
        if(!user.getEmail().matches(emailPattern1)) {
            model.put("message", "Please enter real e-mail address.");
            return "registration";
        }

        // check if age correct
        int userAge = user.getAge();
        if(userAge < 0 || userAge > 116) {
            model.put("message", "Please enter real your real age.");
            return "registration";
        }
        if(userAge < 14) {
            model.put("message", "Sorry you can't sign up to our website if you are less then 14 years old.");
            return "registration";
        }

        // check if password is difficult enough
        String passPattern1 = ".*[!-/:-@^-`{-~]+.*";
        String passPattern2 = ".*[a-z]+.*";
        String passPattern3 = ".*[A-Z]+.*";
        String passPattern4 = ".*[0-9]+.*";
        String passPattern5 = "^.{8,30}$";
        if(!user.getPassword().matches(passPattern1) || !user.getPassword().matches(passPattern2) ||
                !user.getPassword().matches(passPattern3) || !user.getPassword().matches(passPattern4) ||
                !user.getPassword().matches(passPattern5)) {
            model.put("message", "Password must contain 8-30 symbols. " +
                    "You need to use at lest one low and one UPPER case English letter. " +
                    "You need to use at lest one number and one of these symbols: " +
                    "'!\"#$%&'()*+,-./:;<=>?@^_`{|}~'.");

            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/main?name="+user.getName();
    }
}
