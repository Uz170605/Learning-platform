package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.dao.UserDao;
import uz.pdp.dto.UserDto;

import java.util.List;
import java.util.UUID;


public class UserService {

    @Autowired
    UserDao userDao;


    public List<UserDto> getAllMentors() {
        List<UserDto> allMentors = userDao.getAllMentors();
        return allMentors;
    }

    public UserDto getMentorById(UUID id) {
       return userDao.getMentorById(id);
    }

    public List<UserDto> getAllUsers() {
      return   userDao.getAllUsers();
    }
}
