package uz.pdp.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.dto.LessonDto;
import uz.pdp.dto.ModuleDto;
import uz.pdp.dto.UserDto;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Component
public class LessonDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    


    public List<LessonDto> getAllLessons() {
        String sqlQuery = "select * from get_all_lessons";
        List<LessonDto> lessonDtoListFromDb = jdbcTemplate.query(sqlQuery, (rs, row) -> {
            LessonDto lessonDto = new LessonDto();
            lessonDto.setId(UUID.fromString(rs.getString(1)));
            lessonDto.setTitle(rs.getString(2));
            Object modules =  rs.getObject(3);
            Type listType = new TypeToken<ModuleDto>(){}.getType();
            ModuleDto moduleDto = new Gson().fromJson( modules.toString(), listType);
             lessonDto.setModuleDto(moduleDto);
            return lessonDto;
        });
        return lessonDtoListFromDb;
    }

    public int addLesson(LessonDto lessonDto) {
        String sqlQuery ="Insert into lessons(title, module_id) values('" + lessonDto.getTitle() +
                "'," + lessonDto.getModuleDto().getId() + ","+ " + returning id";
        String idStr = jdbcTemplate.queryForObject(sqlQuery, (rs, rowNum) -> rs.getString("id"));
        UUID uuid = UUID.fromString(Objects.requireNonNull(idStr));
      return 1;
    }
//
//    public int deleteLesson(UUID id) {
//        String sqlQuery1 = "Delete from module_lessons where lesson_id='"+id+"'";
//       int res = jdbcTemplate.update(sqlQuery1);
//        String sqlQuery = "Delete from lessons where id ='" + id+"'";
//       int  res1 = jdbcTemplate.update(sqlQuery);
//
//        return res-res1;
//    }

    public LessonDto getLessonById(UUID id) {
        String sqlQuery = "select * from get_all_lessons where lesson_id ='" + id+"'";
        return  jdbcTemplate.queryForObject(sqlQuery, (rs, row) -> {
            LessonDto lessonDto = new LessonDto();
            lessonDto.setId(UUID.fromString(rs.getString(1)));
            lessonDto.setTitle(rs.getString(2));
            Object module = rs.getObject(6);

            Type listType = new TypeToken<ModuleDto>(){}.getType();
            ModuleDto moduleDto = new Gson().fromJson(module.toString(), listType);

            lessonDto.setModuleDto(moduleDto);
            return lessonDto;
        });
    }

    public int editLesson(LessonDto lessonDto) {
        String sqlString =
                "update lessons set title='"+lessonDto.getTitle()+"',module_id='"+ lessonDto.getModuleId()+"' where id='" + lessonDto.getId() +"'";
    return jdbcTemplate.update(sqlString);
    }
    
    
}
