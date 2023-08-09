package com.example.twitterAnalog.dao;

import com.example.twitterAnalog.domain.dto.User;
import com.example.twitterAnalog.domain.entity.Phrase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Dao {

    boolean isExistsNickname(String nickname);
    void insertNewUser(User user);
    String getAccessToken(User user);
    long getUserIdByToken(String accessToken);
    void addTag(String tag);
    void addPhraseTag(long phraseId, String tag);
    long addPhrase(long userId, String text);
    List<Phrase> getPhrasesByUserId(long userId);
    List<String> getTagsByPhraseId(long phraseId);
}
