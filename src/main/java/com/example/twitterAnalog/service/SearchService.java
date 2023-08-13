package com.example.twitterAnalog.service;


import com.example.twitterAnalog.domain.api.search.SearchPhrasesByTagReq;
import com.example.twitterAnalog.domain.api.search.SearchTagReq;
import com.example.twitterAnalog.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface SearchService {

    ResponseEntity<Response> searchTags(SearchTagReq req, String accessToken);
    ResponseEntity<Response> searchPhrasesByTag(SearchPhrasesByTagReq req, String accessToken);
}
