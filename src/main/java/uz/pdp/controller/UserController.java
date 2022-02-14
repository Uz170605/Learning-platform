package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.dto.UserDto;
import uz.pdp.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getAllUsers(Model model){
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users",allUsers);
        return "view-user";
    }
    @GetMapping("/mentors")
    public String getAllMentors(Model model){
        List<UserDto> allMentors = userService.getAllMentors();
        model.addAttribute("mentorList",allMentors);
        return "view-user";
    }
    @GetMapping("/addUser")
    public String getUser(Model model){

        return "user-form";
    }

    @GetMapping("/userAllData/{id}")
    public String getSelectUserById(@PathVariable String id, Model model){
        UUID id1 = UUID.fromString(id);
        UserDto mentorById = userService.getMentorById(id1);
        model.addAttribute("author",mentorById);
        return "view-select-user";
    }
}
