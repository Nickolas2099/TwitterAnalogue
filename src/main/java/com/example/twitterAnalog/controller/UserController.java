package com.example.twitterAnalog.controller;

import com.example.twitterAnalog.domain.api.user.LoginReq;
import com.example.twitterAnalog.domain.api.user.PublicPhraseReq;
import com.example.twitterAnalog.domain.api.user.RegistrationReq;
import com.example.twitterAnalog.domain.response.Response;
import com.example.twitterAnalog.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "phrase-service-public/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/hello")
    public String hello() {
        String hello = "Hello, phrase-service! Version: 1.0.1";
        log.info(hello);
        return hello;
    }

    @PostMapping("/registration")
    public ResponseEntity<Response> registration(@RequestBody final RegistrationReq req) {
        log.info("START endpoint registration, request: {}", req);
        ResponseEntity<Response> resp = userServiceImpl.registration(req);
        log.info("End endpoint registration, response: {}", resp);
        return resp;
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody final LoginReq req) {
        log.info("START endpoint login, request: {}", req);
        ResponseEntity<Response> resp = userServiceImpl.login(req);
        log.info("End endpoint login, response: {}", resp);
        return resp;
    }

    @PostMapping("/publicPhrase")
    public ResponseEntity<Response> publicPhrase(@RequestHeader final String accessToken, @RequestBody final PublicPhraseReq req) {

        log.info("START endpoint publicPhrase, request: {}", req);
        ResponseEntity<Response> resp = userServiceImpl.publicPhrase(req, accessToken);
        log.info("End endpoint publicPhrase, response: {}", resp);
        return resp;
    }

    @GetMapping("/getPhrases")
    public ResponseEntity<Response> getPhrases(@RequestHeader final String accessToken){

        log.info("START endpoint getPhrases, accessToken: {}", accessToken);
        ResponseEntity<Response> resp = userServiceImpl.getMyPhrases(accessToken);
        log.info("END endpoint getPhrases, accessToken: {}", accessToken);
        return resp;
    }
}
