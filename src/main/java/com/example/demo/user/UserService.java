package com.example.demo.user;

import com.example.demo.DataSource.DataSoruceConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
//import org.springframework.secur

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Service
public class UserService {

    private  final UserRepository userRepository;

//    PasswordEncoder passwordEncoder;
    DataSoruceConfig dataSoruceConfig;

    @Autowired
    private Environment env;

    @Autowired
    public UserService(UserRepository userRepository, DataSoruceConfig dataSoruceConfig){
        this.userRepository = userRepository;
//        this.passwordEncoder = new BCryptPasswordEncoder();
        this.dataSoruceConfig = dataSoruceConfig;
    }

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }


    public User authenticateUserByName(String username, String password) {
        String encodedPassword = DigestUtils.sha256Hex(password);
        System.out.println(encodedPassword);
        System.out.println(password);
        return userRepository.authenticateUserByName(username, encodedPassword);
    }

    public Map<String, Object> insertNewUser(User user){
        boolean hasUserName = userRepository.findById(user.getUsername()).isPresent();
        int emailCount = userRepository.getEmailCount(user.getEmail());

        Map<String, Object> json = new HashMap<String, Object>();
        List<User> data = new ArrayList<>();
        if(hasUserName && emailCount != 0){
            json.put("status", false);
            json.put("message","Duplicate UserName and Email");
        }
        else if(hasUserName){
            json.put("status", false);
            json.put("message","UserName is already taken");
        }
        else if(emailCount != 0){
            json.put("status", false);
            json.put("message","User already exists with given Email");

        }
        else {
            String encodedPassword =  DigestUtils.sha256Hex(user.getPassword());;
            user.setPassword(encodedPassword);
            json.put("status",true);
            json.put("message","Successfully Registered");
            data.add(userRepository.save(user));


        }

        json.put("data",data);
        return  json;


    }

    public void initalizeDateBase() throws SQLException, IOException {

      Connection con = dataSoruceConfig.getConnecton();


      String filePath = env.getProperty("fileName");
      System.out.println(filePath);
      Reader reader = dataSoruceConfig.parseFile(filePath);

      ScriptRunner sr = new ScriptRunner(con);



      sr.runScript(reader);


    }

    public User getUserByName(String username){
        Optional<User> user = userRepository.findById(username);
        return user.orElse(null);
    }




}
