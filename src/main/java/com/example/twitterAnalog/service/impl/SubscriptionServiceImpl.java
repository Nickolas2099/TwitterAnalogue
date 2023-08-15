package com.example.twitterAnalog.service.impl;

import com.example.twitterAnalog.dao.CommonDao;
import com.example.twitterAnalog.dao.UserDao;
import com.example.twitterAnalog.dao.communication.SubscriptionDao;
import com.example.twitterAnalog.domain.api.common.PhraseResp;
import com.example.twitterAnalog.domain.api.common.UserResp;
import com.example.twitterAnalog.domain.response.Response;
import com.example.twitterAnalog.domain.response.SuccessResponse;
import com.example.twitterAnalog.service.SubscriptionService;
import com.example.twitterAnalog.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {


    private final ValidationUtils validationUtils;
    private final SubscriptionDao subscriptionDao;
    private final CommonDao commonDao;
    private final CommonService commonService;


    @Override
    public ResponseEntity<Response> getMySubscribers(String accessToken, int from, int limit) {
        validationUtils.validationDecimalMin("from", from, 0);
        validationUtils.validationDecimalMin("limit", limit, 1);

        long userId = commonDao.getUserIdByToken(accessToken);
        log.info("userId: {}", userId);

        List<PhraseResp> phrases = subscriptionDao.getMyPublishers(userId, from, limit);
        commonService.phraseEnrichment(phrases);

        return new ResponseEntity<>(SuccessResponse.builder().data(CommonPhrasesResp.builder().phrases(phrases).build())
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getMyPublishers(String accessToken, int from, int limit) {
        return null;
    }

    @Override
    public ResponseEntity<Response> unsubscription(UnsubscriptionReq req, String accessToken) {
        return null;
    }

    @Override
    public ResponseEntity<Response> subscription(SubscriptionReq req, String accessToken) {
        return null;
    }
}
