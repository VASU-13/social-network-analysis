package com.vasu.sna.controller;

import com.vasu.sna.entity.Friendship;
import com.vasu.sna.entity.User;
import com.vasu.sna.exception.UserFriendshipAlreadyExistsException;
import com.vasu.sna.exception.UserNotFoundException;
import com.vasu.sna.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = userService.addUser(user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        try {
            userService.removeUser(userId);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found With Id: " + userId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{userId1}/friends/{userId2}")
    public ResponseEntity<?> addFriendship(@PathVariable Integer userId1, @PathVariable Integer userId2){
        ResponseEntity<?> responseEntity = null;
        try {
            Friendship friendship = userService.addFriendship(userId1, userId2);
            responseEntity = new ResponseEntity<>(friendship, HttpStatus.OK);
        }
        catch(UserNotFoundException e){
            return new ResponseEntity<>("Users Not Found: ",HttpStatus.NOT_FOUND);
        }
        catch(UserFriendshipAlreadyExistsException e){
            return new ResponseEntity<>("User Friendship Already Exists: ",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

//    @DeleteMapping("/{userId1}/friends/{userId2}")
//    public ResponseEntity<Void> removeFriendship(@PathVariable Integer userId1, @PathVariable Integer userId2){
//        userService.removeFriendship(userId1, userId2);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<Friendship>> listFriends(@PathVariable Integer userId){
        List<Friendship> friends = userService.listOfFriends(userId);
        return ResponseEntity.ok(friends);
    }


}
