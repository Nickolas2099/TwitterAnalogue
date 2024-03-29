package com.example.twitterAnalog.service.communication;

import com.example.twitterAnalog.domain.api.communication.reaction.CommentPhraseReq;
import com.example.twitterAnalog.domain.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ReactionService {
    ResponseEntity<Response> deleteLikePhrase(String accessToken, long phraseId);
    ResponseEntity<Response> likePhrase(String accessToken, long phraseId);
    ResponseEntity<Response> commentPhrase(String accessToken, CommentPhraseReq req);
    ResponseEntity<Response> deleteCommentPhrase(String accessToken, long commentId);
    ResponseEntity<Response> getBlockUsers(String accessToken);
    ResponseEntity<Response> blockUser(String accessToken, long blockUserId);
    ResponseEntity<Response> unblockUser(String accessToken, long blockUserId);

}
