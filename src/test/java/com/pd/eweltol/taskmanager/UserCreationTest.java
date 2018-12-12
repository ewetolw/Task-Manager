package com.pd.eweltol.taskmanager;


import com.pd.eweltol.taskmanager.controller.UserController;
import com.pd.eweltol.taskmanager.model.types.Role;
import com.pd.eweltol.taskmanager.model.User;
import com.pd.eweltol.taskmanager.repository.UserRepository;
import com.pd.eweltol.taskmanager.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserCreationTest {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;
    private static User testUser;
    private static User testUser2;

    @BeforeClass
    public static void createTestUser(){
        testUser = new User("username","password","Ewelina", "Tołwińska",
                "ewetolw@gmail.com", Role.WORKER, Boolean.TRUE );
        testUser2 = new User("username","password","Ewelina", "Tołwińska",
                "ewetolw@gmail.com", Role.WORKER, Boolean.TRUE );
    }

    @After
    public void restoreDefault(){
        if(userRepository.findByUsername("username")!=null) {
            userRepository.delete(userRepository.findByUsername("username"));
        }
        testUser2 = new User("username","password","Ewelina", "Tołwińska",
                "ewetolw@gmail.com", Role.WORKER, Boolean.TRUE );;
    }

    @Test
    public void saveUserToDbTest(){
        userRepository.save(testUser);
        User userFromDB = userRepository.findByUsername("username");
        Assert.assertEquals(testUser,userFromDB);
    }

    @Test
    public void findAllUsersServiceTest(){
        userRepository.save(testUser);
        testUser2.setUsername("eloelo");
        testUser2.setEmail("eloelo@gmail.com");
        userRepository.save(testUser2);
        List<User> usersList = userService.getUsers();

        Assert.assertEquals(usersList.get(usersList.size()-2),testUser);
        Assert.assertEquals(usersList.get(usersList.size()-1), testUser2);

        userRepository.delete(userRepository.findByUsername("eloelo"));
    }

//
//    @Test
//    public void addUserJSON(){
//        String json = "{\"username\":\"newuser\",\"password\":\"password\",\"lastName\":\"tolwinska\",\"firstName\":\"ewelina\",\"email\":\"newuser@email\",\"role\":\"WORKER\",\"activated\":true}";
//        userService.addUser(json);
//        User user = userRepository.findByUsername("newuser");
//        Assert.assertNotNull(user);
//        if(user!=null) {
//            userRepository.delete(user);
//        }
//    }



}
