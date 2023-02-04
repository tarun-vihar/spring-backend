package com.example.demo.services;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.dto.RelationDTO;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.entity.RelationPrimaryKey;
import com.example.demo.models.entity.Relations;
import com.example.demo.models.entity.User;
import com.example.demo.models.pojo.AuthenticationResponse;
import com.example.demo.models.pojo.LoginRequest;
import com.example.demo.models.pojo.RegisterRequest;
import com.example.demo.models.pojo.Response;
import com.example.demo.repository.RelationsRepository;
import com.example.demo.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
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

import javax.management.relation.Relation;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {

    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private RelationsRepository relationsRepository;



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


    public List<UserDTO> getAllUsers() {

        List<User> userList = userRepository.findAll();
        return parseUserResponse(userList);
    }

//    Q4
    public List<UserDTO> getUnpublisheUsers(){
        List<User> userList = userRepository.getUnpublishedUsers();

        return  parseUserResponse(userList);
    }

//    Q2
    public List<UserDTO> getUsersWithMaximumBlogsOnGivenDate(String date) {
        Date filterOn = toSqlDate(date);
        System.out.println(filterOn);

        List<User> userList = userRepository.getMostBlogsInADay(filterOn);
        return parseUserResponse(userList);

    }

    private java.sql.Date  toSqlDate(String startDate) {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
        java.util.Date utilDate;
        try {
          utilDate = sdf1.parse(startDate);


        } catch (ParseException e) {
            long millis=System.currentTimeMillis();
            utilDate = new java.sql.Date(millis);
        }
        java.sql.Date sqlStartDate = new java.sql.Date(utilDate.getTime());

        return sqlStartDate;
    }

    //    Q6
    public List<UserDTO> getPublishersOfNonNegativeBlogs() {
        List<User> userList = userRepository.usersWithNonNegativeCommentedBlogs();
        return  parseUserResponse(userList);
    }

//    Q5

    public List<UserDTO> finallAllNegativeCommnetedUsers() {

        List<User> userList =  userRepository.usersWithNegativeCommentedBlogs();
        return  parseUserResponse(userList);
    }

    public Response addFollowers(String[] follwers, String username) {

        Response response = new Response();
        User user = getUserByUserName(username);
        if(user != null){
            List<User> followerUser = userRepository.findAllById(Arrays.asList(follwers));
            for(User follower : followerUser){
                Relations relation = new Relations();
                relation.setUsername(user);
                relation.setFollowerId(follower);
//                RelationPrimaryKey relationPrimaryKey = new RelationPrimaryKey();
//                relationPrimaryKey.setFollowerId(follower);
//                relationPrimaryKey.setUsername(user);
//                relation.setRelationKey(relationPrimaryKey);

                relationsRepository.save(relation);

            }

        }else{
            throw  new UserNotFoundException("User Doesn't exist");
        }


        return response;
    }

    public UserDTO mapToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setUsername(user.getUsername());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setFollowingCount(user.getFollowing().size());
        userDTO.setFollwerCount(user.getFollowers().size());
        return userDTO;
    }

    public Response addFollowing(String[] following, String username) {
        Response response = new Response();
        User user = getUserByUserName(username);
        if(user != null){
            List<User> followingUsers = userRepository.findAllById(Arrays.asList(following));
            for(User followingUser : followingUsers){
                Relations relation = new Relations();
                relation.setUsername(followingUser);
                relation.setFollowerId(user);
//                RelationPrimaryKey relationPrimaryKey = new RelationPrimaryKey();
//                relationPrimaryKey.setFollowerId(user);
//                relationPrimaryKey.setUsername(followingUser);
//                relation.setRelationKey(relationPrimaryKey);
                relationsRepository.save(relation);

            }

        }else{
            throw  new UserNotFoundException("User Doesn't exist");
        }
        return response;
    }

    public List<UserDTO> findCommonFollowing(String username1, String username2) {
        User user1 = getUserByUserName(username1);
        User user2 = getUserByUserName(username2);

        if(user2 != null && user1 != null){
           List<User> userList =  userRepository.findCommonUsers(user1, user2);
           return  parseUserResponse(userList);
        }else {
            throw  new UserNotFoundException("Incorrect Username 1 or Username 2");
        }

    }

    List<UserDTO> parseUserResponse(List<User> userList){
        List<UserDTO>  userDTOList = userList.stream().map(user -> mapToUserDTO(user)).collect(Collectors.toList());
        return userDTOList;
    }
}
