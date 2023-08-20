package com.example.twitterAnalog.dao.common;

import com.example.twitterAnalog.domain.api.common.TagResp;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommonDao {

    List<TagResp> getTagsByPhraseId(long phraseId);
    long getUserIdByToken(String accessToken);
    long getCountLikes(long phraseId);
    void testSchedulerLock(String instanceName);
}
