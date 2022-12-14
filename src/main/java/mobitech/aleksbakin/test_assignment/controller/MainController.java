package mobitech.aleksbakin.test_assignment.controller;

import mobitech.aleksbakin.test_assignment.domain.Message;
import mobitech.aleksbakin.test_assignment.domain.User;
import mobitech.aleksbakin.test_assignment.repos.MessageRepo;
import mobitech.aleksbakin.test_assignment.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(name="name", required = false, defaultValue = "") String name, Map<String, Object> model) {
        model.put("name", name);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }
    
    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        model.put("name", "");

        Message message = new Message(text,tag);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        model.put("name", "");

        Iterable<Message> messages;
        if(filter != null && filter.isEmpty()) {
            messages = messageRepo.findAll();
        } else {
            messages = messageRepo.findByTag(filter);
        }
        model.put("messages", messages);
        return "main";
    }

    @GetMapping("/users")
    public String users(Map<String, Object> model) {
        Iterable<User> users = userRepo.findAll();
        model.put("users", users);
        return "users";
    }
}
