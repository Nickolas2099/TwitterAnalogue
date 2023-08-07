package com.example.twitterAnalog.dao;

import com.example.twitterAnalog.domain.dto.User;
import org.springframework.stereotype.Service;

@Service
public interface Dao {

    boolean isExistsNickname(String nickname);

    void insertNewUser(User user);

    String getAccessToken(User user);
}
