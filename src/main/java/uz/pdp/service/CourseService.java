package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.CourseDao;
import uz.pdp.dto.CourseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class CourseService {

    @Autowired
    CourseDao courseDao;

    public List<CourseDto> getAllCourses(){
        List<CourseDto> allCourses = courseDao.getAllCourses();
        return allCourses;
    }

    public String
    addCourse(CourseDto courseDto) {
        if (courseDto.getId() != null) {
            if (courseDao.editCourse(courseDto) != 0) {
                return "Successfuly edited!";
            } else {
                return "Could not edited!";
            }
        } else {
            if (courseDao.addCourse(courseDto) != 0) {
                return "Successfuly added!";
            } else {
                return "Could not added!";
            }
        }
  }

    public String deleteCourse(UUID id) {
        if (courseDao.deleteCourse(id) == 0) {
            return "Successfuly deleted!";
        } else {
        return "Could not deleted!";
        }
    }

    public CourseDto getCourseById(UUID id) {
        CourseDto courseDto = courseDao.getCourseById(id);
        return courseDto;
    }
}
