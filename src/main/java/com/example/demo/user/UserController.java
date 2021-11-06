package com.example.demo.user;



import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {



    private final UserService userService;
    @Autowired
    private Environment env;

    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/start")
    public @ResponseBody
    Map<String, Object> initalizeDateBase() throws IOException {

        Map<String, Object> json = new HashMap<String, Object>();
        try {
            userService.initalizeDateBase();

            json.put("status", true);
            json.put("message","Successfully Exectued DataBase");
        } catch (SQLException e) {
            e.printStackTrace();
            json.put("status", false);
            json.put("message","Unable to Instantiate DataBase");
        }

//        System.out.println(r);
        return json;
    }

    @GetMapping("/get-all-users")
    public List<User> authenticateUser(){
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public Map<String, Object> authenticateUser(@RequestBody  User user){
        Map<String, Object> json = new HashMap<String, Object>();

         User res= userService.authenticateUserByName(user.getUsername(),user.getPassword());
         List<User> data = new ArrayList<>();

         System.out.println(res);

         if(res != null){
             json.put("status", true);
             json.put("message","Successfully Logged In");
             data.add(res);
         }
         else{
             json.put("status",false);
             json.put("message","Invalid User Name or Password");

         }
        json.put("data",data);
        return json;
    }

    @PostMapping("/addUser")
    public Map<String, Object> addNewUser(@RequestBody User user){
        System.out.println(user);
        return userService.insertNewUser(user);
    }


}
