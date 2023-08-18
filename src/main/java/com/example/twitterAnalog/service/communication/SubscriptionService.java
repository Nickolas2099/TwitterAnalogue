package com.example.twitterAnalog.service.communication;

import com.example.twitterAnalog.domain.api.common.UserResp;
import com.example.twitterAnalog.domain.api.communication.subscribe.SubscriptionReq;
import com.example.twitterAnalog.domain.api.communication.subscribe.UnsubscriptionReq;
import com.example.twitterAnalog.domain.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionService {

    ResponseEntity<Response> getMySubscribers(String accessToken);
    ResponseEntity<Response> getMyPublishers(String accessToken);
    ResponseEntity<Response> unsubscription(UnsubscriptionReq req, String accessToken);
    ResponseEntity<Response> subscription(SubscriptionReq req, String accessToken);
    ResponseEntity<Response> getMyPublishersPhrases(String accessToken, int from, int limit);

}
