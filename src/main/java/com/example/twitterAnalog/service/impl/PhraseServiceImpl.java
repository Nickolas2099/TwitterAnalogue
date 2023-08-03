package com.example.twitterAnalog.service.impl;

import com.example.twitterAnalog.domen.constant.Code;
import com.example.twitterAnalog.domen.response.error.Error;
import com.example.twitterAnalog.domen.response.error.ErrorResponse;
import com.example.twitterAnalog.domen.response.Response;
import com.example.twitterAnalog.domen.response.exception.CommonException;
import com.example.twitterAnalog.service.PhraseService;
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

    @Override
    public ResponseEntity<Response> test() {
        throw CommonException.builder().code(Code.TEST).message("Test").httpStatus(HttpStatus.BAD_REQUEST).build();
    }
}
