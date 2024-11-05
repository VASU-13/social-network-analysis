package com.vasu.sna.service;

import com.vasu.sna.entity.Friendship;
import com.vasu.sna.entity.User;
import com.vasu.sna.exception.UserFriendshipAlreadyExistsException;
import com.vasu.sna.exception.UserNotFoundException;
import com.vasu.sna.repository.FriendshipRepository;
import com.vasu.sna.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImpl {

    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeUser(Integer userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            userRepository.delete(user.get());
        }
        else {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Friendship addFriendship(Integer userId1, Integer userId2) throws UserNotFoundException, UserFriendshipAlreadyExistsException {
        Optional<User> user1 = userRepository.findById(userId1);
        Optional<User> user2 = userRepository.findById(userId2);
        if(user1.isPresent() && user2.isPresent()){
            Optional<Friendship> friendshipExists = friendshipRepository.findByUser1UserIdAndUser2UserId(userId1, userId2);
            if(friendshipExists.isPresent()){
                throw new UserFriendshipAlreadyExistsException("User Friendship Already Exists");
            }
            else {
                Friendship friendship = Friendship.builder().user1(user1.get())
                        .user2(user2.get()).build();
                return friendshipRepository.save(friendship);
            }
        }
        else {
            throw new UserNotFoundException("User Not Found");
        }
    }

//    public void removeFriendship(Integer userId1, Integer userId2){
//        Optional<Friendship> friendship = friendshipRepository.findByUsers(userId1, userId2);
//        friendship.ifPresent(value -> friendshipRepository.delete(value));
//    }

    public List<Friendship> listOfFriends(Integer userId){
        return friendshipRepository.findByUser1UserId(userId);
    }
}
