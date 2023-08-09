package com.example.twitterAnalog.controller;

import com.example.twitterAnalog.domain.api.LoginReq;
import com.example.twitterAnalog.domain.api.PublicPhraseReq;
import com.example.twitterAnalog.domain.api.RegistrationReq;
import com.example.twitterAnalog.domain.response.Response;
import com.example.twitterAnalog.service.impl.PhraseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "phrase-service-public")
public class Controller {

    private final PhraseServiceImpl phraseService;

    @GetMapping("/hello")
    public String hello() {
        String hello = "Hello, phrase-service! Version: 1.0.1";
        log.info(hello);
        return hello;
    }

    @PostMapping("/registration")
    public ResponseEntity<Response> registration(@RequestBody final RegistrationReq req) {
        log.info("START endpoint registration, request: {}", req);
        ResponseEntity<Response> resp = phraseService.registration(req);
        log.info("End endpoint registration, response: {}", resp);
        return resp;
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody final LoginReq req) {
        log.info("START endpoint login, request: {}", req);
        ResponseEntity<Response> resp = phraseService.login(req);
        log.info("End endpoint login, response: {}", resp);
        return resp;
    }

    @PostMapping("/publicPhrase")
    public ResponseEntity<Response> publicPhrase(@RequestHeader final String accessToken, @RequestBody final PublicPhraseReq req) {

        log.info("START endpoint publicPhrase, request: {}", req);
        ResponseEntity<Response> resp = phraseService.publicPhrase(req, accessToken);
        log.info("End endpoint publicPhrase, response: {}", resp);
        return resp;
    }

    @GetMapping("/getPhrases")
    public ResponseEntity<Response> getPhrases(@RequestHeader final String accessToken){

        log.info("START endpoint getPhrases, accessToken: {}", accessToken);
        ResponseEntity<Response> resp = phraseService.getPhrases(accessToken);
        log.info("END endpoint getPhrases, accessToken: {}", accessToken);
        return resp;
    }
}
