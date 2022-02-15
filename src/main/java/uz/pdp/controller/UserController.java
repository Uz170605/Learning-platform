package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.UserDto;
import uz.pdp.model.Role;
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

    @GetMapping("/{id}")
    public String getUserById(@PathVariable String id,Model model){
        UUID uuid = UUID.fromString(id);
        UserDto userById = userService.getUserById(uuid);
        model.addAttribute("selectUser",userById);
        List<Role> userRole = userService.getUserRole();
        model.addAttribute("roles" , userRole);
        return "user-form";
    }

    @GetMapping("/addUser")
    public String getUser(Model model){
        List<Role> userRole = userService.getUserRole();
        model.addAttribute("roles" , userRole);
        return "user-form";
    }

    @GetMapping("/userAllData/{id}")
    public String getSelectUserById(@PathVariable String id, Model model){
        UUID id1 = UUID.fromString(id);
        UserDto mentorById = userService.getMentorById(id1);
        model.addAttribute("author",mentorById);
        return "view-select-user";
    }

    @GetMapping("/userData/{id}")
    public String getSelectUser(@PathVariable String id, Model model){
        UUID id1 = UUID.fromString(id);
        UserDto mentorById = userService.getUserById(id1);
        model.addAttribute("user",mentorById);
        return "view-select-user-by-id";
    }

    @PostMapping
    public String saveUser(UserDto userDto,Model model){
        String str = userService.saveUser(userDto);
        model.addAttribute("message",str);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id,Model model){
        UUID userId = UUID.fromString(id);
        String str=userService.deleteUser(userId);
        model.addAttribute("message",str);
        return "redirect:/users";
    }
}
