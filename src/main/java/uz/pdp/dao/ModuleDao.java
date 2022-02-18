package uz.pdp.dao;


import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.ModuleDto;

import java.lang.reflect.Type;
import java.util.*;

@Component
public class ModuleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ModuleDto> getAllModules(){
        String sql="select id,name,isactive,price from modules;";
        List<ModuleDto> moduleDtoList=jdbcTemplate.query(sql,(rs,row)->{
            ModuleDto moduleDto=new ModuleDto();
            moduleDto.setId(UUID.fromString(rs.getString(1)));
            moduleDto.setName(rs.getString(2));
            moduleDto.setActive(rs.getBoolean(3));
            moduleDto.setPrice(rs.getDouble(4));
            return moduleDto;
        });
        return moduleDtoList;
    }
    public ModuleDto getModuleById(UUID uuid){
        String sql="select * from modules where id='"+uuid+"'";
        return  jdbcTemplate.queryForObject(sql,(rs,row)->{
            ModuleDto moduleDto=new ModuleDto();
            moduleDto.setId(UUID.fromString(rs.getString(1)));
            moduleDto.setName(rs.getString(2));
            moduleDto.setActive(rs.getBoolean(3));
            moduleDto.setCourseId(UUID.fromString(rs.getString(4)));
            moduleDto.setPrice(rs.getDouble(5));
            return moduleDto;
        });
    }
    public int addModule(ModuleDto moduleDto){
        String sql="insert into modules(name, isactive, course_id, price) VALUES (\n" +
                "        '"+moduleDto.getName()+"',"+moduleDto.isActive()+",'"+moduleDto.getCourseId()+"',"+moduleDto.getPrice()+"\n" +
                " )";
        return jdbcTemplate.update(sql);
    }
    public int deleteModule(UUID uuid){
        String sql="delete from modules where id='"+uuid+"'";

        return jdbcTemplate.update(sql);
    }
    public int editModule(ModuleDto moduleDto){
        String sql="update modules set name='"+moduleDto.getName()+"',isactive="+moduleDto.isActive()+"," +
                "course_id='"+moduleDto.getCourseId()+"'," +
                "price="+moduleDto.getPrice()+" where id='"+moduleDto.getId()+"';";
        return jdbcTemplate.update(sql);
    }
    public List<ModuleDto> viewModuleBYPage(int startPage,int totalPage){
        String sql="select * from modules limit "+(startPage - 1)+" offset "+totalPage+"";
        return jdbcTemplate.query(sql,(rs,row)->{
            ModuleDto moduleDto=new ModuleDto();
            moduleDto.setId(UUID.fromString(rs.getString(1)));
            moduleDto.setName(rs.getString(2));
            moduleDto.setPrice(rs.getDouble(3));
            moduleDto.setActive(rs.getBoolean(4));
            return moduleDto;
        });
    }
}
