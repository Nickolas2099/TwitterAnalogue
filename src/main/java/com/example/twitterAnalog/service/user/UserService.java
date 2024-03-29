package com.example.twitterAnalog.service.user;

import com.example.twitterAnalog.domain.api.user.LoginReq;
import com.example.twitterAnalog.domain.api.user.PublicPhraseReq;
import com.example.twitterAnalog.domain.api.user.RegistrationReq;
import com.example.twitterAnalog.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {

//    ResponseEntity<Response> test();
    ResponseEntity<Response> registration(RegistrationReq req);
    ResponseEntity<Response> login(LoginReq req);
    ResponseEntity<Response> publicPhrase(PublicPhraseReq req, String accessToken);
    ResponseEntity<Response> getMyPhrases(String accessToken);
}
