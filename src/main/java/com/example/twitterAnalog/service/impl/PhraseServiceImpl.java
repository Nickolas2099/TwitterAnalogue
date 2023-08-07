package com.example.twitterAnalog.service.impl;

import com.example.twitterAnalog.dao.Dao;
import com.example.twitterAnalog.domain.api.LoginReq;
import com.example.twitterAnalog.domain.api.LoginResp;
import com.example.twitterAnalog.domain.api.RegistrationReq;
import com.example.twitterAnalog.domain.api.RegistrationResp;
import com.example.twitterAnalog.domain.constant.Code;
import com.example.twitterAnalog.domain.dto.User;
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

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhraseServiceImpl implements PhraseService {

    private final ValidationUtils validationUtils;
    private final Dao dao;
    private final EncryptUtils encryptUtils;

    @Override
    public ResponseEntity<Response> registration(RegistrationReq req) {
        validationUtils.validationRequest(req);

        if(dao.isExistsNickname(req.getAuthorization().getNickname())) {
            throw CommonException.builder().code(Code.NICKNAME_BUSY).message("Этот ник уже занят, придумайте другой")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }

        String accessToken = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorization().getPassword());
        dao.insertNewUser(User.builder().nickname(req.getAuthorization().getNickname()).encryptPassword(encryptPassword)
                .accessToken(accessToken).build());
        return new ResponseEntity<>(SuccessResponse.builder().data(RegistrationResp.builder()
                .accessToken(accessToken).build()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> login(LoginReq req) {
        validationUtils.validationRequest(req);

        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorization().getPassword());
        String accessToken = dao.getAccessToken(User.builder().nickname(req.getAuthorization().getNickname())
                .encryptPassword(encryptPassword).build());
        return new ResponseEntity<>(SuccessResponse.builder().data(LoginResp.builder().accessToken(accessToken)
                .build()).build(), HttpStatus.OK);
    }


}
