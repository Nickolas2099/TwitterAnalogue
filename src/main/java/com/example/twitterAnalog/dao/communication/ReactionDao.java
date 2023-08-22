package com.example.twitterAnalog.dao.communication;

import com.example.twitterAnalog.domain.api.communication.reaction.CommentPhraseReq;
import com.example.twitterAnalog.domain.dto.WhoseComment;
import org.springframework.stereotype.Service;

@Service
public interface ReactionDao {
    void likePhrase(long userId, long phraseId);
    void deleteLikePhrase(Long userId, long phraseId);
    void commentPhrase(long userId, CommentPhraseReq req);
    WhoseComment whoseComment(long commentId);
    void deleteComment(long commentId);
}
