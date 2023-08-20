package com.example.twitterAnalog.service.communication;

import com.example.twitterAnalog.dao.common.CommonDao;
import com.example.twitterAnalog.dao.communication.ReactionDao;
import com.example.twitterAnalog.domain.response.Response;
import com.example.twitterAnalog.domain.response.SuccessResponse;
import com.example.twitterAnalog.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final ValidationUtils validationUtils;
    private final CommonDao commonDao;
    private final ReactionDao reactionDao;

    @Override
    public ResponseEntity<Response> deleteLikePhrase(String accessToken, long phraseId) {
        validationUtils.validationDecimalMin("phraseId", phraseId, 1);
        long userId = commonDao.getUserIdByToken(accessToken);
        reactionDao.deleteLikePhrase(userId, phraseId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> likePhrase(String accessToken, long phraseId) {
        validationUtils.validationDecimalMin("phraseId", phraseId, 1);
        long userId = commonDao.getUserIdByToken(accessToken);
        reactionDao.likePhrase(userId, phraseId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }
}
