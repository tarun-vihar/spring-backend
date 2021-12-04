package com.example.demo.services;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.entity.User;
import com.example.demo.models.pojo.AuthenticationResponse;
import com.example.demo.models.pojo.LoginRequest;
import com.example.demo.models.pojo.RegisterRequest;
import com.example.demo.models.pojo.Response;
import com.example.demo.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthService {

    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserRepository userRepository;



    @Autowired
    ModelMapper modelMapper;



    Response response;


    AuthService(){
        this.response = new Response();
    }

    public Response signup(RegisterRequest registerRequest) {

        System.out.println(registerRequest);

        boolean hasUserName = userRepository.findById(registerRequest.getUsername()).isPresent();
        boolean hasEmail = userRepository.findByEmail(registerRequest.getEmail()).isPresent();


        if(hasUserName && hasEmail ){
           response.setStatus(false);
           response.setMessage("Duplicate UserName and Email");

        }
        else if(hasUserName){
            response.setStatus(false);
            response.setMessage("UserName is already taken");
        }
        else if(hasEmail){
            response.setStatus(false);
            response.setMessage("User already exists with given Email");
        }
        else{
            List< RegisterRequest> data = new ArrayList<>();
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            String encodedPassword =  DigestUtils.sha256Hex(registerRequest.getPassword());
            user.setPassword(encodedPassword);
            user.setCreated(Instant.now());
            user.setFirstName(registerRequest.getFirstName());
            user.setLastName(registerRequest.getLastName());
            data.add(modelMapper.map(userRepository.save(user),RegisterRequest.class));
            response.setData(data);

            response.setMessage("Sucessfully registered");
            response.setStatus(true);
        }


        return response;



    }




    public Response login(LoginRequest loginRequest) {
           String username = loginRequest.getUsername();
           String encodedPassword = DigestUtils.sha256Hex(loginRequest.getPassword());
           User user = userRepository.authenticateUserByName(username, encodedPassword);

        if(user != null) {
            List< RegisterRequest> data = new ArrayList<>();
            response.setStatus(true);
            response.setMessage("Successfully Logged In");
            data.add(modelMapper.map(userRepository.save(user), RegisterRequest.class));
            response.setData(data);
        }
        else{
            response.setStatus(false);
            response.setMessage("Invalid Credential");

        }

        return  response;
    }

    public User getUserByUserName(String username){
        User user = userRepository.findById(username).orElse(null);
        return  user;
    }


    public Response getAllUsers() {
        Response response = new Response();
        response.setData(userRepository.findAll());
        return response;
    }

    public Response getUnpublisheUsers(){
        Response response = new Response();
         response.setData(userRepository.getUnpublishedUsers());
         return  response;
    }

    public Response getUsersWithMaximumBlogsOnGivenDate(String date) {
        Response response = new Response();
        return  response;
    }
}
