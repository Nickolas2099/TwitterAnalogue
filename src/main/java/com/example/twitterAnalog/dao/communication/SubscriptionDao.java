package com.example.twitterAnalog.dao.communication;

import com.example.twitterAnalog.domain.api.common.PhraseResp;
import com.example.twitterAnalog.domain.api.common.UserResp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubscriptionDao {
    List<UserResp> getMySubscribers(long userId);
    List<UserResp> getMyPublishers(long userId);
    void subscription(long subUserId, long pubUserId);
    void unsubscription(long subuserId, long pubUserId);
    List<PhraseResp> getMyPublishersPhrases(long userId, int from, int limit);
}
