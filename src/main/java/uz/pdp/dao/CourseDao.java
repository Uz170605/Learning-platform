package uz.pdp.dao;

import com.google.gson.Gson;


import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.ModuleDto;
import uz.pdp.dto.UserDto;


import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CourseDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<CourseDto> getAllCourses() {
        String sqlQuery = "select * from get_course_by_user_and_module()";
        List<CourseDto> courseDtoListFromDb = jdbcTemplate.query(sqlQuery, (rs, row) -> {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(UUID.fromString(rs.getString(1)));
            courseDto.setName(rs.getString(2));
            courseDto.setPrice(rs.getDouble(3));
            courseDto.setActive(rs.getBoolean(5));
            courseDto.setDescription(rs.getString(4));
            Array authors = rs.getArray(6);

            Type listType = new TypeToken<ArrayList<UserDto>>() {
            }.getType();
            List<UserDto> authorList = new Gson().fromJson(authors.toString(), listType);

            courseDto.setAuthors(authorList);
            Array module=rs.getArray(7);
            Type type=new TypeToken<ArrayList<ModuleDto>>(){}.getType();
            List<ModuleDto> moduleDtoList=new Gson().fromJson(module.toString(),type);
            courseDto.setModule(moduleDtoList);
            return courseDto;
        });
        return courseDtoListFromDb;
    }

    public int addCourse(CourseDto courseDto) {
        String sqlQuery = "Insert into courses(name,price,is_active,description) values('" + courseDto.getName() + "'," + courseDto.getPrice() + "," + courseDto.isActive() + ",'" + courseDto.getDescription() + "') returning id";
        String idStr = jdbcTemplate.queryForObject(sqlQuery, (rs, rowNum) -> rs.getString("id"));
        UUID uuid = UUID.fromString(Objects.requireNonNull(idStr));
        int res = 0;
        for (UUID uuid1 : courseDto.getAuthorsId()) {
            res = jdbcTemplate.update("INSERT INTO authors_courses values ('" + uuid1 + "','" + uuid + "');");
        }
        return res;
    }

    public int deleteCourse(UUID id) {
        String sqlQuery1 = "Delete from authors_courses where course_id='" + id + "'";
        int res = jdbcTemplate.update(sqlQuery1);
        String sqlQuery = "Delete from courses where id ='" + id + "'";
        int res1 = jdbcTemplate.update(sqlQuery);

        return res - res1;
    }

    public CourseDto getCourseById(UUID id) {
        String sqlQuery = "select * from get_course_by_user_and_module() where id='" + id + "'";
        return jdbcTemplate.queryForObject(sqlQuery, (rs, row) -> {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(UUID.fromString(rs.getString(1)));
            courseDto.setName(rs.getString(2));
            courseDto.setPrice(rs.getDouble(3));
            courseDto.setActive(rs.getBoolean(5));
            courseDto.setDescription(rs.getString(4));
            Array authors = rs.getArray(6);
            Type listType = new TypeToken<ArrayList<UserDto>>() {
            }.getType();
            List<UserDto> authorList = new Gson().fromJson(authors.toString(), listType);
            courseDto.setAuthors(authorList);
            Array module=rs.getArray(7);
            Type type=new TypeToken<ArrayList<ModuleDto>>(){}.getType();
            List<ModuleDto> moduleDtoList=new Gson().fromJson(module.toString(),type);
            courseDto.setModule(moduleDtoList);
            return courseDto;
        });
    }

    public int editCourse(CourseDto courseDto) {
        int res2 = 0;
        int res = 0;
        if (courseDto.getAuthorsId().length != 0) {
            String sqlQuery1 = "Delete from authors_courses where course_id='" + courseDto.getId() + "'";
            res = jdbcTemplate.update(sqlQuery1);
            for (UUID uuid : courseDto.getAuthorsId()) {
                res2 = jdbcTemplate.update("Insert INTO authors_courses values ('" + uuid + "','" + courseDto.getId() + "')");
            }
        }
        String sqlQuery = "Update courses Set name='" + courseDto.getName() + "', price=" + courseDto.getPrice() + ", is_active =" + courseDto.isActive() + ", description='" + courseDto.getDescription() + "', updated_at=now()  where id='" + courseDto.getId() + "'";
        int res1 = jdbcTemplate.update(sqlQuery);
        return res1 + (res + res2);
    }
}
