package com.example.twitterAnalog.util;

import com.example.twitterAnalog.domain.constant.Code;
import com.example.twitterAnalog.domain.response.exception.CommonException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationUtils {

    private final Validator validator;

    public <T> void validationRequest(T req) {
        Set<ConstraintViolation<T>> result = validator.validate(req);
        if(!result.isEmpty()) {
            String resultValidations = result.stream()
                    .map(ConstraintViolation::getMessage)
                    .reduce((s1, s2) -> s1 + ". " + s2).orElse("");
            log.error("Переданный в запросе json не валиден, ошибка валидации: {}", resultValidations);
            throw CommonException.builder().code(Code.REQUEST_VALIDATION_ERROR).userMessage(resultValidations)
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
    }

    public void validationDecimalMin(String fieldName, long fieldValue, long constraint) {
        if (fieldValue < constraint)
            throw CommonException.builder().code(Code.REQUEST_VALIDATION_ERROR)
                    .techMessage(fieldName + "должно быть больше или равно " + constraint).httpStatus(HttpStatus.BAD_REQUEST).build();
    }

    public void validationDecimalMin(String fieldName, int fieldValue, int constraint) {
        if (fieldValue < constraint)
            throw CommonException.builder().code(Code.REQUEST_VALIDATION_ERROR)
                    .techMessage(fieldName + "должно быть больше или равно " + constraint).httpStatus(HttpStatus.BAD_REQUEST).build();
    }
}
