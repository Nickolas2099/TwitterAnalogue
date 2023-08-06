package com.example.twitterAnalog.service.impl;

import com.example.twitterAnalog.dao.Dao;
import com.example.twitterAnalog.domen.api.RegistrationReq;
import com.example.twitterAnalog.domen.api.RegistrationResp;
import com.example.twitterAnalog.domen.constant.Code;
import com.example.twitterAnalog.domen.dto.User;
import com.example.twitterAnalog.domen.response.SuccessResponse;
import com.example.twitterAnalog.domen.response.Response;
import com.example.twitterAnalog.domen.response.exception.CommonException;
import com.example.twitterAnalog.service.PhraseService;
import com.example.twitterAnalog.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhraseServiceImpl implements PhraseService {

    private final ValidationUtils validationUtils;
    private final Dao dao;

    @Override
    public ResponseEntity<Response> registration(RegistrationReq req) {
        validationUtils.validationRequest(req);

        if(dao.isExistsNickname(req.getNickname())) {
            throw CommonException.builder().code(Code.NICKNAME_BUSY).message("Этот ник уже занят, придумайте другой")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }

        String accessToken = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
        String encryptPassword = DigestUtils.md5DigestAsHex(req.getPassword().getBytes());
        dao.insertNewUser(User.builder().nickname(req.getNickname()).encryptPassword(encryptPassword)
                .accessToken(accessToken).build());
        return new ResponseEntity<Response>(SuccessResponse.builder().data(RegistrationResp.builder()
                .accessToken(accessToken).build()).build(), HttpStatus.OK);
    }


}
