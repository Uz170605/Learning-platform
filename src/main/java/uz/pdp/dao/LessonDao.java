package uz.pdp.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dto.LessonDto;
import uz.pdp.dto.UserDto;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class LessonDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<LessonDto> getAllLessons() {
        String sqlQuery = "select * from get_all_lessons";
        List<LessonDto> lessonDtoListFromDb = jdbcTemplate.query(sqlQuery, (rs, row) -> {
            LessonDto lessonDto = new LessonDto();
            lessonDto.setId(UUID.fromString(rs.getString(1)));
            lessonDto.setName(rs.getString(2));
            lessonDto.setVideo(rs.getString(3));
            lessonDto.setTask(rs.getString(5));
            lessonDto.setGuide(rs.getString(4));
//            Array authors = rs.getArray(6);

//            Type listType = new TypeToken<ArrayList<UserDto>>(){}.getType();
//            List<UserDto> authorList = new Gson().fromJson(authors.toString(), listType);
//
//             lessonDto.setAuthors(authorList);
            return lessonDto;
        });
        return lessonDtoListFromDb;
    }

//    public int addLesson(LessonDto lessonDto) {
//        String sqlQuery ="Insert into lessons(name, module_id, guide) values('" + lessonDto.getName() + "'," + lessonDto + "," + lessonDto.isActive() + ",'"+lessonDto.getDescription()+"') returning id";
//        String idStr = jdbcTemplate.queryForObject(sqlQuery, (rs, rowNum) -> rs.getString("id"));
//        UUID uuid = UUID.fromString(Objects.requireNonNull(idStr));
//        int res=0;
//        for (UUID uuid1 : lessonDto.getAuthorsId()) {
//            res=jdbcTemplate.update("INSERT INTO authors_lessons values ('"+uuid1+"','"+uuid+"');");
//        }
//        return res;
//    }
//
//    public int deleteLesson(UUID id) {
//        String sqlQuery1 = "Delete from authors_lessons where lesson_id='"+id+"'";
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
            lessonDto.setName(rs.getString(2));
            lessonDto.setVideo(rs.getString(3));
            lessonDto.setTask(rs.getString(4));
            lessonDto.setGuide(rs.getString(5));
//            Array authors = rs.getArray(6);
//
//            Type listType = new TypeToken<ArrayList<UserDto>>(){}.getType();
//            List<UserDto> authorList = new Gson().fromJson(authors.toString(), listType);
//
//            lessonDto.setAuthors(authorList);
            return lessonDto;
        });
    }
//
//    public int editLesson(LessonDto lessonDto) {
//            int res2= 0;
//            int res = 0;
//        if (lessonDto.getAuthorsId().length != 0) {
//        String sqlQuery1 = "Delete from authors_lessons where lesson_id='"+lessonDto.getId()+"'";
//         res = jdbcTemplate.update(sqlQuery1);
//        for (UUID uuid : lessonDto.getAuthorsId()) {
//            res2 = jdbcTemplate.update("Insert INTO authors_lessons values ('"+uuid+"','"+lessonDto.getId()+"')");
//        }
//        }
//        String sqlQuery = "Update lessons Set name='"+lessonDto.getName()+"', price="+lessonDto.getPrice()+", is_active ="+lessonDto.isActive()+", description='"+lessonDto.getDescription()+"', updated_at=now()  where id='"+lessonDto.getId()+"'";
//            int res1 =  jdbcTemplate.update(sqlQuery);
//        return res1+(res+res2);
//    }
}
