package com.example.twitterAnalog.controller;

import com.example.twitterAnalog.domain.api.search.SearchUsersByPartNicknameReq;
import com.example.twitterAnalog.domain.api.search.SearchPhrasesByPartWordReq;
import com.example.twitterAnalog.domain.api.search.SearchPhrasesByTagReq;
import com.example.twitterAnalog.domain.api.search.SearchTagReq;
import com.example.twitterAnalog.domain.response.Response;
import com.example.twitterAnalog.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "phrase-service-public/search")
public class SearchController {

    private final SearchService searchService;

    @PostMapping("/searchPhrasesByPartWord")
    public ResponseEntity<Response> searchPhrasesByPartWord(@RequestHeader String accessToken, @RequestBody final SearchPhrasesByPartWordReq req) {
        log.info("START endpoint searchPhrasesByPartWord, accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = searchService.searchPhrasesByPartWord(req, accessToken);
        log.info("END endpoint searchPhrasesByPartWord, response: {}", resp);
        return resp;
    }


    @PostMapping("/searchPhrasesByTag")
    public ResponseEntity<Response> searchPhrasesbyTag(@RequestHeader String accessToken, @RequestBody final SearchPhrasesByTagReq req) {
        log.info("START endpoint searchPhrasesByTag, accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = searchService.searchPhrasesByTag(req, accessToken);
        log.info("END endpoint searchPhrasesByTag, response: {}", resp);
        return resp;
    }

    @PostMapping("/searchTags")
    public ResponseEntity<Response> searchTags (@RequestHeader String accessToken, @RequestBody final SearchTagReq req) {
        log.info("START endpoint searchTags, accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = searchService.searchTags(req, accessToken);
        log.info("END endpoint searchTags, response: {}", resp);
        return resp;
    }

    @PostMapping("/searchUsersByPartNickname")
    public ResponseEntity<Response> searchUsersByPartNickname(@RequestHeader String accessToken,
                                                              @RequestBody final SearchUsersByPartNicknameReq req) {
        log.info("START endpoint searchUsersByPartNickname, accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = searchService.searchUsersByPartNickname(req, accessToken);
        log.info("END endpoint searchUsersByPartNickname, response: {}", resp);
        return resp;
    }
}
