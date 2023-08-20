package com.example.twitterAnalog.controller.communication;

import com.example.twitterAnalog.domain.response.Response;
import com.example.twitterAnalog.service.communication.ReactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping("phrase-service-public/communication/reaction")
public class ReactionController {
    private final ReactionService reactionService;

    @GetMapping("/deleteLikePhrase/{phraseId}")
    public ResponseEntity<Response> deleteLikePhrase(@RequestHeader String accessToken, @PathVariable("phraseId") long phraseId) {

        log.info("START endpoint deleteLikePhrase accessToken: {}, phraseId: {}", accessToken, phraseId);
        ResponseEntity<Response> resp = reactionService.deleteLikePhrase(accessToken, phraseId);
        log.info("END endpoint deleteLikePhrase response: {}", resp);
        return resp;
    }

    @GetMapping("/likePhrase/{phraseId}")
    public ResponseEntity<Response> likePhrase(@RequestHeader String accessToken, @PathVariable("phraseId") long phraseId) {

        log.info("START endpoint likePhrase accessToken: {}, phraseId: {}", accessToken, phraseId);
        ResponseEntity<Response> resp = reactionService.likePhrase(accessToken, phraseId);
        log.info("END endpoint likePhrase response: {}", resp);
        return resp;
    }
}
