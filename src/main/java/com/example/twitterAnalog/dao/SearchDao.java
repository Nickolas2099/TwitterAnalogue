package com.example.twitterAnalog.dao;

import com.example.twitterAnalog.domain.api.common.PhraseResp;
import com.example.twitterAnalog.domain.api.common.TagResp;
import com.example.twitterAnalog.domain.api.search.SearchPhrasesByPartWordReq;
import com.example.twitterAnalog.domain.api.search.SearchPhrasesByTagReq;

import java.util.List;

public interface SearchDao {

    List<TagResp> searchTags(String partTag);

    List<PhraseResp> searchPhrasesByTag(SearchPhrasesByTagReq req);
    List<PhraseResp> searchPhrasesByPartWord(SearchPhrasesByPartWordReq req);
}
