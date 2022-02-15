package uz.pdp.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.ModuleDto;
import uz.pdp.model.Module;
import uz.pdp.service.CourseService;
import uz.pdp.service.ModuleService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/modules")
public class ModuleController {
    @Autowired
ModuleService moduleService;
    @Autowired
    CourseService courseService;

@GetMapping
    public String getAllModules(Model model){
        List<ModuleDto> allModules=moduleService.getAllModules();
        model.addAttribute("moduleList",allModules);
        return "view-modules";
    }
    @GetMapping(    "/addModule")
    public String getModule(@ModelAttribute("module" ) ModuleDto moduleDto,Model model){
    String str=moduleService.addModules(moduleDto);
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("message",str);
        model.addAttribute("courseList",allCourses);
    return "module-form";
    }
    @PostMapping
    public String addModule(@ModelAttribute ("modules")ModuleDto moduleDto, Model model){
        String s = moduleService.addModules(moduleDto);
        model.addAttribute("message",s);
        return "redirect:/modules";
    }

    @GetMapping("{id}")
    public String getModuleById(@PathVariable(required = false)String id ,Model model){
        UUID uuid = UUID.fromString(id);
        ModuleDto module=moduleService.getAllModules(uuid);
        model.addAttribute("selectModule",module);
        return "module-form";
    }
    @DeleteMapping("/{id}")
    public String deleteModule(@PathVariable String id,Model model){
        UUID uuid = UUID.fromString(id);
        String str= moduleService.delete(uuid);
        model.addAttribute("message",str);
        return "redirect:/modules";
    }
}
