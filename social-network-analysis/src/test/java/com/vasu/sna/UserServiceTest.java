package com.vasu.sna;

import com.vasu.sna.entity.User;
import com.vasu.sna.exception.UserNotFoundException;
import com.vasu.sna.repository.UserRepository;
import com.vasu.sna.service.UserService;
import com.vasu.sna.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setName("test");
        when(userRepository.save(user)).thenReturn(user);
        User createdUser = userService.addUser(user);
        assertEquals("test",createdUser.getName());
    }

    @Test
    public void deleteUserFailure() throws UserNotFoundException {
        Exception exception = assertThrows(UserNotFoundException.class,()->{
                userService.removeUser(2);
        });
        assertEquals("User Not Found",exception.getMessage());
    }

}
