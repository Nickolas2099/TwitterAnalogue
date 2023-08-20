package com.example.twitterAnalog.service.communication;

import com.example.twitterAnalog.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface ReactionService {
    ResponseEntity<Response> deleteLikePhrase(String accessToken, long phraseId);
    ResponseEntity<Response> likePhrase(String accessToken, long phraseId);

}
