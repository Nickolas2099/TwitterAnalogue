package com.example.twitterAnalog.service.impl;

import com.example.twitterAnalog.domen.response.Error;
import com.example.twitterAnalog.domen.response.ErrorResponse;
import com.example.twitterAnalog.domen.response.Response;
import com.example.twitterAnalog.service.PhraseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PhraseServiceImpl implements PhraseService {

    @Override
    public ResponseEntity<Response> test() {
        return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder().code("Validation_ERROR")
                .message("ошибка валидации").build()).build(), HttpStatus.BAD_REQUEST);
    }
}
