package com.example.twitterAnalog.service;

import com.example.twitterAnalog.domen.api.RegistrationReq;
import com.example.twitterAnalog.domen.response.Response;
import org.springframework.http.ResponseEntity;

public interface PhraseService {

//    ResponseEntity<Response> test();
    ResponseEntity<Response> registration(RegistrationReq req);
}
