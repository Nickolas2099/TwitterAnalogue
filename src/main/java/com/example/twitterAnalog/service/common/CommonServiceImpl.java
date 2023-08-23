package com.example.twitterAnalog.service.common;

import com.example.twitterAnalog.dao.common.CommonDao;
import com.example.twitterAnalog.domain.api.common.PhraseResp;
import com.example.twitterAnalog.domain.constant.Code;
import com.example.twitterAnalog.domain.response.exception.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{

    private final CommonDao commonDao;

    @Override
    public void checkBlockByPhraseId(long userId, long phraseId) {
        long checkBlockUserId = commonDao.getUserIdByPhraseId(phraseId);
        checkBlock(userId, checkBlockUserId);
    }

    @Override
    public void checkBlockByUserId(long userId, long checkBlockUserId) {
        checkBlock(userId, checkBlockUserId);
    }

    private void checkBlock(long userId, long checkBlockUserId) {
        if(commonDao.isBlocked(userId, checkBlockUserId)) {
            throw CommonException.builder().code(Code.BLOCKED).userMessage("Вы заблокированы этим пользователем")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public void phraseEnrichment(List<PhraseResp> phrases) {
        for (PhraseResp phraseResp : phrases) {
            phraseResp.setTags(commonDao.getTagsByPhraseId(phraseResp.getPhraseId()));
            phraseResp.setCountLikes(commonDao.getCountLikes(phraseResp.getPhraseId()));
            phraseResp.setComments(commonDao.getCommentsByPhraseId(phraseResp.getPhraseId()));
        }
    }
}
