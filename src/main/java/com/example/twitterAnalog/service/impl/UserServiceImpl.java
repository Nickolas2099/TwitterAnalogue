package com.example.twitterAnalog.service.impl;

import com.example.twitterAnalog.config.MapperConfig;
import com.example.twitterAnalog.dao.CommonDao;
import com.example.twitterAnalog.dao.UserDao;
import com.example.twitterAnalog.domain.api.common.PhraseResp;
import com.example.twitterAnalog.domain.api.common.TagResp;
import com.example.twitterAnalog.domain.api.user.*;
import com.example.twitterAnalog.domain.constant.Code;
import com.example.twitterAnalog.domain.dto.User;
import com.example.twitterAnalog.domain.entity.Phrase;
import com.example.twitterAnalog.domain.response.SuccessResponse;
import com.example.twitterAnalog.domain.response.Response;
import com.example.twitterAnalog.domain.response.exception.CommonException;
import com.example.twitterAnalog.service.PhraseService;
import com.example.twitterAnalog.util.EncryptUtils;
import com.example.twitterAnalog.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements PhraseService {

    private final ValidationUtils validationUtils;
    private final UserDao userDao;
    private final CommonDao commonDao;
    private final EncryptUtils encryptUtils;
    private final MapperConfig mapper;

    @Override
    public ResponseEntity<Response> getMyPhrases(String accessToken) {
        long userId = commonDao.getUserIdByToken(accessToken);
        List<Phrase> phraseList = userDao.getPhrasesByUserId(userId);

        List<PhraseResp> phraseRespList = new ArrayList<>();
        for (Phrase phrase : phraseList) {
            List<TagResp> tags = commonDao.getTagsByPhraseId(phrase.getId());
            PhraseResp phraseResp = mapper.getMapper().map(phrase, PhraseResp.class);
            phraseResp.setTags(tags);
            phraseRespList.add(phraseResp);
        }
        return new ResponseEntity<>(SuccessResponse.builder().data(GetMyPhrasesResp.builder().phrases(phraseRespList)
                .build()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> registration(RegistrationReq req) {
        validationUtils.validationRequest(req);

        if(userDao.isExistsNickname(req.getAuthorization().getNickname())) {
            throw CommonException.builder().code(Code.NICKNAME_BUSY).userMessage("Этот ник уже занят, придумайте другой")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }

        String accessToken = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorization().getPassword());
        userDao.insertNewUser(User.builder().nickname(req.getAuthorization().getNickname()).encryptPassword(encryptPassword)
                .accessToken(accessToken).build());
        return new ResponseEntity<>(SuccessResponse.builder().data(RegistrationResp.builder()
                .accessToken(accessToken).build()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> login(LoginReq req) {
        validationUtils.validationRequest(req);

        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorization().getPassword());
        String accessToken = userDao.getAccessToken(User.builder().nickname(req.getAuthorization().getNickname())
                .encryptPassword(encryptPassword).build());
        return new ResponseEntity<>(SuccessResponse.builder().data(LoginResp.builder().accessToken(accessToken)
                .build()).build(), HttpStatus.OK);
    }


    public ResponseEntity<Response> publicPhrase(@RequestHeader PublicPhraseReq req, @RequestBody String accessToken) {
        validationUtils.validationRequest(req);

        long userId = commonDao.getUserIdByToken(accessToken);
        long phraseId = userDao.addPhrase(userId, req.getText());
        log.info("userId: {}, phraseId: {}", userId, phraseId);

        for(String tag : req.getTags()) {
            userDao.addTag(tag);
            userDao.addPhraseTag(phraseId, tag);
        }

        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }
}
