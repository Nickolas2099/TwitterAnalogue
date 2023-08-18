package com.example.twitterAnalog.service.communication;

import com.example.twitterAnalog.dao.common.CommonDao;
import com.example.twitterAnalog.dao.communication.SubscriptionDao;
import com.example.twitterAnalog.domain.api.common.CommonPhrasesResp;
import com.example.twitterAnalog.domain.api.common.PhraseResp;
import com.example.twitterAnalog.domain.api.common.TagResp;
import com.example.twitterAnalog.domain.api.communication.GetMyPublishersPhrasesResp;
import com.example.twitterAnalog.domain.api.communication.subscribe.GetMyPublishersResp;
import com.example.twitterAnalog.domain.api.communication.subscribe.SubscriptionReq;
import com.example.twitterAnalog.domain.api.communication.subscribe.UnsubscriptionReq;
import com.example.twitterAnalog.domain.constant.Code;
import com.example.twitterAnalog.domain.response.Response;
import com.example.twitterAnalog.domain.response.SuccessResponse;
import com.example.twitterAnalog.domain.response.exception.CommonException;
import com.example.twitterAnalog.controller.communication.common.CommonService;
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
public class SubscriptionServiceImpl implements SubscriptionService {


    private final ValidationUtils validationUtils;
    private final SubscriptionDao subscriptionDao;
    private final CommonDao commonDao;
    private final CommonService commonService;


    @Override
    public ResponseEntity<Response> getMyPublishersPhrases(String accessToken, int from, int limit) {
        validationUtils.validationDecimalMin("from", from, 0);
        validationUtils.validationDecimalMin("limit", limit, 1);

        long userId = commonDao.getUserIdByToken(accessToken);
        log.info("userId: {}", userId);

        List<PhraseResp> phrases = subscriptionDao.getMyPublishersPhrases(userId, from, limit);
//        commonService.phraseEnrichment(phrases);
        for(PhraseResp phraseResp : phrases) {
            List<TagResp> tags = commonDao.getTagsByPhraseId(phraseResp.getPhraseId());
            phraseResp.setTags(tags);
        }

        return new ResponseEntity<>(SuccessResponse.builder().data(
                GetMyPublishersPhrasesResp.builder().phrases(phrases).build()).build(), HttpStatus.OK);
//        return new ResponseEntity<>(SuccessResponse.builder().data(CommonPhrasesResp.builder().phrases(phrases).build())
//                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getMySubscribers(String accessToken) {
        long userId = commonDao.getUserIdByToken(accessToken);
        log.info("userId: {}", userId);

        return new ResponseEntity<>(SuccessResponse.builder().data(
                GetMyPublishersResp.builder().publishers(subscriptionDao.getMyPublishers(userId)).build()).build(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getMyPublishers(String accessToken) {
        long userId = commonDao.getUserIdByToken(accessToken);
        log.info("userId: {}", userId);

        return new ResponseEntity<>(SuccessResponse.builder().data(
                GetMyPublishersResp.builder().publishers(subscriptionDao.getMyPublishers(userId)).build()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> unsubscription(UnsubscriptionReq req, String accessToken) {
        validationUtils.validationRequest(req);
        long subUserId = commonDao.getUserIdByToken(accessToken);

        long pubUserId = req.getPubUserId();
        log.info("subUserId: {}, pubUserId: {}", subUserId, pubUserId);

        subscriptionDao.unsubscription(subUserId, pubUserId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> subscription(SubscriptionReq req, String accessToken) {
        validationUtils.validationRequest(req);
        long subUserId = commonDao.getUserIdByToken(accessToken);

        long pubUserId = req.getPubUserId();
        log.info("subUserId: {}, subscriptionUserId: {}", subUserId, pubUserId);

        if(subUserId == pubUserId) {
            throw CommonException.builder().code(Code.SUBSCRIPTION_LOGIC_ERROR).userMessage("Вы не можете подписаться на самого себя")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
        subscriptionDao.subscription(subUserId, pubUserId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }
}
