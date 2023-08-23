package com.example.twitterAnalog.service.communication;

import com.example.twitterAnalog.dao.common.CommonDao;
import com.example.twitterAnalog.dao.communication.ReactionDao;
import com.example.twitterAnalog.dao.communication.SubscriptionDao;
import com.example.twitterAnalog.domain.api.communication.GetBlockUsersResp;
import com.example.twitterAnalog.domain.api.communication.reaction.CommentPhraseReq;
import com.example.twitterAnalog.domain.constant.Code;
import com.example.twitterAnalog.domain.dto.WhoseComment;
import com.example.twitterAnalog.domain.response.Response;
import com.example.twitterAnalog.domain.response.SuccessResponse;
import com.example.twitterAnalog.domain.response.error.Error;
import com.example.twitterAnalog.domain.response.error.ErrorResponse;
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
    private final SubscriptionDao subscriptionDao;

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

    @Override
    public ResponseEntity<Response> commentPhrase(String accessToken, CommentPhraseReq req) {
        validationUtils.validationRequest(req);
        long userId = commonDao.getUserIdByToken(accessToken);
        reactionDao.commentPhrase(userId, req);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> deleteCommentPhrase(String accessToken, long commentId) {
        validationUtils.validationDecimalMin("commentId", commentId, 1);
        long userId = commonDao.getUserIdByToken(accessToken);

        WhoseComment whoseComment = reactionDao.whoseComment(commentId);
        log.info("userId: {}, whoseComment: {}", userId, whoseComment);

        if(whoseComment.getCommentUserId() == userId || whoseComment.getPhraseUserId() == userId) {
            reactionDao.deleteComment(commentId);
            return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder().code(Code.NOT_YOUR_COMMENT)
                    .userMessage("Это не ваш комментарий и не комментарий у вашей фразы").build()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> getBlockUsers(String accessToken) {
        long userId = commonDao.getUserIdByToken(accessToken);
        return new ResponseEntity<>(SuccessResponse.builder()
                .data(GetBlockUsersResp.builder().blockUsers(reactionDao.getBlockUsers(userId)).build()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> blockUser(String accessToken, long blockUserId) {
        validationUtils.validationDecimalMin("blockUserId", blockUserId, 1);
        long userId = commonDao.getUserIdByToken(accessToken);

        if(userId == blockUserId) {
            return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder().code(Code.NOT_BLOCK_YOURSELF)
                    .userMessage("Нельзя заблокировать самого себя").build()).build(), HttpStatus.BAD_REQUEST);
        }

        reactionDao.blockUser(userId, blockUserId);
        subscriptionDao.unsubscription(blockUserId, userId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> unblockUser(String accessToken, long blockUserId) {
        validationUtils.validationDecimalMin("blockUserId", blockUserId, 0);
        long userId = commonDao.getUserIdByToken(accessToken);
        reactionDao.unblockUser(userId, blockUserId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }
}
