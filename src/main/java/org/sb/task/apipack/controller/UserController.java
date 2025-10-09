package org.sb.task.apipack.controller;

import org.sb.task.apipack.model.User;
import org.sb.task.apipack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @Autowired
//    public UserController(UserService userService){
//        this.userService = userService;
//    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") int id){
        User user = userService.read(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping(value = "/test")
    public ResponseEntity<?> createTest(){
        User user1 = new User();
        user1.setName("Борис")
                .setEmail("boris@emael.su")
                .setAge(30);

        try {

            userService.create(user1);
        } catch (Exception e){
            System.out.println(e);
        }

        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody User user){

        userService.create(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/users")
//    @RequestMapping(value = "/users")
    public ResponseEntity<List<User>> findAll(){
        List<User> userList1 = userService.readAll();



//        userService.create()

//        try {

            User user = new User();
            user.setName("name1");
            user.setEmail("email1");
            user.setAge(23);

            List<User> userList = new ArrayList<>();
            userList.add(user);
//        }catch (Exception e){
//            return e.toString();
//        }

        return new ResponseEntity<>(userList1, HttpStatus.OK);
    }
}
