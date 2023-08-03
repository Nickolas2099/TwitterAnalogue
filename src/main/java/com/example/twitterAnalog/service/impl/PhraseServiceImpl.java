package com.example.twitterAnalog.service.impl;

import com.example.twitterAnalog.domen.api.RegistrationReq;
import com.example.twitterAnalog.domen.constant.Code;
import com.example.twitterAnalog.domen.response.SuccessResponse;
import com.example.twitterAnalog.domen.response.error.Error;
import com.example.twitterAnalog.domen.response.error.ErrorResponse;
import com.example.twitterAnalog.domen.response.Response;
import com.example.twitterAnalog.domen.response.exception.CommonException;
import com.example.twitterAnalog.service.PhraseService;
import com.example.twitterAnalog.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhraseServiceImpl implements PhraseService {

    private final ValidationUtils validationUtils;

    @Override
    public ResponseEntity<Response> registration(RegistrationReq req) {
        validationUtils.validationRequest(req);
        return new ResponseEntity<Response>((SuccessResponse.builder().data("...!").build()), HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<Response> test() {
//        throw CommonException.builder().code(Code.TEST).message("Test").httpStatus(HttpStatus.BAD_REQUEST).build();
//    }

}
