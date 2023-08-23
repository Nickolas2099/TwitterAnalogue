package com.example.twitterAnalog.dao.common;

import com.example.twitterAnalog.domain.api.common.TagResp;
import com.example.twitterAnalog.domain.api.communication.reaction.CommentResp;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommonDao {

    List<TagResp> getTagsByPhraseId(long phraseId);
    long getUserIdByToken(String accessToken);
    long getCountLikes(long phraseId);
    void testSchedulerLock(String instanceName);
    List<CommentResp> getCommentsByPhraseId(long phraseId);
    boolean isBlocked(long userId, long checkBlockUserId);
    long getUserIdByPhraseId(long phraseId);
}
