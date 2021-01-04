package com.springmvc.homepage.service;

import com.springmvc.homepage.POJO.User;
import com.springmvc.homepage.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

//    {
//        User user1 = getUser("onnamani");
//        System.out.println(user1.getFirstname());
//        System.out.println(user1.getUsername());
//        System.out.println(user1.getPassword());
//    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public Integer createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

        user.setSalt(encodedSalt);
        user.setPassword(hashedPassword);

        return userMapper.insertUser(new User(null,
                user.getUsername(),
                user.getSalt(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname()
        ));
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}
