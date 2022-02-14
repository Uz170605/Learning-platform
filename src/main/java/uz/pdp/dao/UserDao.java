package uz.pdp.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.UserDto;
import uz.pdp.model.Role;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<UserDto> getAllMentors() {
        String sqlQuery = "Select * from get_author;";
        List<UserDto> getUserDb = jdbcTemplate.query(sqlQuery,(rs, row) ->{
           UserDto userDto = new UserDto();
           userDto.setId(UUID.fromString(rs.getString(1)));
           userDto.setFirstName(rs.getString(2));
           userDto.setLastName(rs.getString(3));
           userDto.setPhoneNumber(rs.getString(4));
           userDto.setEmail(rs.getString(5));
           userDto.setPassword(rs.getString(6));
           return userDto;
        });
        return getUserDb;
    }

    public UserDto getMentorById(UUID id) {
        String sqlQuery = "Select * from get_all_mentors where id='"+id+"'";
      return   jdbcTemplate.queryForObject(sqlQuery,(rs, rowNum) -> {
           UserDto userDto = new UserDto();
            userDto.setId(UUID.fromString(rs.getString(1)));
            userDto.setFirstName(rs.getString(2));
            userDto.setLastName(rs.getString(3));
            userDto.setPhoneNumber(rs.getString(4));
            userDto.setEmail(rs.getString(5));
            userDto.setPassword(rs.getString(6));
            userDto.setBio(rs.getString(7));
            Array course = rs.getArray(8);

            Type type = new TypeToken<ArrayList<CourseDto>>() {
            }.getType();
            List<CourseDto> courses = new Gson().fromJson(course.toString(), type);
            userDto.setCourses(courses);
            return userDto;
        });
    }

    public List<UserDto> getAllUsers() {
       String sqlQuery="select * from get_all_users;";
    List<UserDto> userDtoList =  jdbcTemplate.query(sqlQuery,(rs, rowNum) -> {
         UserDto userDto = new UserDto();
         userDto.setId(UUID.fromString(rs.getString(1)));
         userDto.setFirstName(rs.getString(2));
         userDto.setLastName(rs.getString(3));
         userDto.setPhoneNumber(rs.getString(4));
         userDto.setEmail(rs.getString(5));
         userDto.setPassword(rs.getString(6));
         Array array = rs.getArray(8);
         Type type = new TypeToken<ArrayList<Role>>() {
         }.getType();
         List<Role> roles = new Gson().fromJson(array.toString(), type);
         userDto.setRoles(roles);
         return userDto;
     });

 return userDtoList;
    }
}
