package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.CourseDto;
import uz.pdp.service.CourseService;
import uz.pdp.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
     CourseService courseService;

    @Autowired
    UserService userService;
    @GetMapping
    public String getAllCourses(Model model){
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("courseList",allCourses);
        return "view-courses";
    }

    @GetMapping("/courseAllData/{id}")
    public String getCourseByIdWithAuthor(@PathVariable(required = false) String id, Model model){
        UUID id1 =UUID.fromString(id);
        CourseDto courseById = courseService.getCourseById(id1);
        model.addAttribute("selectCourse",courseById);
        return "view-select-course";
    }
    @GetMapping("/{id}")
    public String getCourseById(@PathVariable(required = false) String id, Model model){
        UUID id1 =UUID.fromString(id);
        CourseDto courseById = courseService.getCourseById(id1);
        model.addAttribute("authors",userService.getAllMentors());
        model.addAttribute("selectCourse",courseById);
        return "course-form";
    }
    @GetMapping("/addCourse")
    public String getCourse(Model model){
        model.addAttribute("authors",userService.getAllMentors());
        return "course-form";
    }

    @PostMapping
    public String addCourse(@ModelAttribute("courses") CourseDto courseDto, Model model){
        String str = courseService.addCourse(courseDto);
        model.addAttribute("message",str);
        return "redirect:/courses";
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable String id, Model model){
       UUID id1=UUID.fromString(id);
        String str = courseService.deleteCourse(id1);
        model.addAttribute("message",str);
        return "redirect:/courses";
    }

}
