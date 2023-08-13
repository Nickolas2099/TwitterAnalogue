package com.example.twitterAnalog.service.impl;

import com.example.twitterAnalog.dao.CommonDao;
import com.example.twitterAnalog.dao.SearchDao;
import com.example.twitterAnalog.domain.api.search.SearchPhrasesByTagReq;
import com.example.twitterAnalog.domain.api.search.SearchPhrasesByTagResp;
import com.example.twitterAnalog.domain.api.search.SearchTagReq;
import com.example.twitterAnalog.domain.api.search.SearchTagResp;
import com.example.twitterAnalog.domain.api.common.TagResp;
import com.example.twitterAnalog.domain.api.common.PhraseResp;
import com.example.twitterAnalog.domain.response.Response;
import com.example.twitterAnalog.domain.response.SuccessResponse;
import com.example.twitterAnalog.service.SearchService;
import com.example.twitterAnalog.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchDao searchDao;
    private final ValidationUtils validation;
    private final CommonDao commonDao;

    @Override
    public ResponseEntity<Response> searchPhrasesByTag(SearchPhrasesByTagReq req, String accessToken) {
        validation.validationRequest(req);

        commonDao.getUserIdByToken(accessToken);
        List<PhraseResp> phrases = searchDao.searchPhrasesByTag(req);
        for (PhraseResp phraseResp : phrases) {
            List<TagResp> tags = commonDao.getTagsByPhraseId(phraseResp.getPhraseId());
            phraseResp.setTags(tags);
        }
        return new ResponseEntity<>(SuccessResponse.builder().data(SearchPhrasesByTagResp.builder()
                .phrases(phrases).build()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> searchTags(SearchTagReq req, String accessToken) {

        validation.validationRequest(req);
        commonDao.getUserIdByToken(accessToken);

        List<TagResp> tagRespList = searchDao.searchTags(req.getPartTag());
        return new ResponseEntity<>(SuccessResponse.builder().data(SearchTagResp.builder().tags(tagRespList).build())
                .build(), HttpStatus.OK);
    }
}
