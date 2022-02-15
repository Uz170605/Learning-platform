package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.LessonDto;
import uz.pdp.service.LessonService;
import uz.pdp.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
     LessonService lessonService;

    @Autowired
    UserService userService;
    @GetMapping
    public String getAllLessons(Model model){
        List<LessonDto> allLessons = lessonService.getAllLessons();
        model.addAttribute("lessonList",allLessons);
        return "view-lessons";
    }

    @GetMapping("/lessonAllData/{id}")
    public String getLessonByIdWithAuthor(@PathVariable(required = false) String id, Model model){
        UUID id1 =UUID.fromString(id);
        LessonDto lessonById = lessonService.getLessonById(id1);
        model.addAttribute("selectLesson",lessonById);
        return "view-select-lesson";
    }
//    @GetMapping("/{id}")
//    public String getLessonById(@PathVariable(required = false) String id, Model model){
//        UUID id1 =UUID.fromString(id);
//        LessonDto lessonById = lessonService.getLessonById(id1);
//        model.addAttribute("authors",userService.getAllMentors());
//        model.addAttribute("selectLesson",lessonById);
//        return "lesson-form";
//    }
    @GetMapping("/addLesson")
    public String getLesson(Model model){
        model.addAttribute("modules",lessonService.getAllModules());
        return "lesson-form";
    }
//
//    @PostMapping
//    public String addLesson(@ModelAttribute("lessons") LessonDto lessonDto, Model model){
//        String str = lessonService.addLesson(lessonDto);
//        model.addAttribute("message",str);
//        return "redirect:/lessons";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteLesson(@PathVariable String id, Model model){
//       UUID id1=UUID.fromString(id);
//        String str = lessonService.deleteLesson(id1);
//        model.addAttribute("message",str);
//        return "redirect:/lessons";
//    }

}
