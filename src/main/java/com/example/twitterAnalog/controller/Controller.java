package com.example.twitterAnalog.controller;

import com.example.twitterAnalog.domen.api.RegistrationReq;
import com.example.twitterAnalog.domen.response.Response;
import com.example.twitterAnalog.service.impl.PhraseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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

}
