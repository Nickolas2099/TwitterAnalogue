package com.example.twitterAnalog.controller.communication;

import com.example.twitterAnalog.domain.api.communication.reaction.CommentPhraseReq;
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

    @DeleteMapping("/deleteCommentPhrase/{commentId}")
    public ResponseEntity<Response> deleteCommentPhrase(@RequestHeader String accessToken,
                                                        @PathVariable("commentId") long commentId) {
        log.info("START endpoint deleteCommentPhrase accessToken: {}, commentId: {}", accessToken, commentId);
        ResponseEntity<Response> resp = reactionService.deleteCommentPhrase(accessToken, commentId);
        log.info("END endpoint deleteCommentPhrase resp: {}", resp);
        return resp;
    }

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

    @GetMapping("/commentPhrase")
    public ResponseEntity<Response> commentPhrase(@RequestHeader String accessToken, @RequestBody final CommentPhraseReq req) {

        log.info("START endpoint commentPhrase accessToken: {}, req: {}", accessToken, req);
        ResponseEntity<Response> resp = reactionService.commentPhrase(accessToken, req);
        log.info("END endpoint commentPhrase resp: {}", resp);
        return resp;
    }

}
