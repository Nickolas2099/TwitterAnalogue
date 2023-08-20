package com.example.twitterAnalog.dao.communication;

import org.springframework.stereotype.Service;

@Service
public interface ReactionDao {
    void likePhrase(long userId, long phraseId);
    void deleteLikePhrase(Long userId, long phraseId);
}
