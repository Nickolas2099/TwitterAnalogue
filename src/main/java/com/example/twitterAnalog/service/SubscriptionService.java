package com.example.twitterAnalog.service;

import com.example.twitterAnalog.domain.api.common.UserResp;
import com.example.twitterAnalog.domain.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionService {

    ResponseEntity<Response> getMySubscribers(String accessToken, int from, int limit);
    ResponseEntity<Response> getMyPublishers(String accessToken, int from, int limit);
    ResponseEntity<Response> unsubscription(UnsubscriptionReq req, String accessToken);
    ResponseEntity<Response> subscription(SubscriptionReq req, String accessToken);


}
