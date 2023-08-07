package com.example.twitterAnalog.service;

import com.example.twitterAnalog.domain.api.LoginReq;
import com.example.twitterAnalog.domain.api.RegistrationReq;
import com.example.twitterAnalog.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface PhraseService {

//    ResponseEntity<Response> test();
    ResponseEntity<Response> registration(RegistrationReq req);
    ResponseEntity<Response> login(LoginReq req);
}
