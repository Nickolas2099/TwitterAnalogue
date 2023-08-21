package com.example.twitterAnalog.service.common;

import com.example.twitterAnalog.dao.common.CommonDao;
import com.example.twitterAnalog.domain.api.common.PhraseResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{

    private final CommonDao commonDao;

    @Override
    public void phraseEnrichment(List<PhraseResp> phrases) {
        for (PhraseResp phraseResp : phrases) {
            phraseResp.setTags(commonDao.getTagsByPhraseId(phraseResp.getPhraseId()));
            phraseResp.setCountLikes(commonDao.getCountLikes(phraseResp.getPhraseId()));
            phraseResp.setComments(commonDao.getCommentsByPhraseId(phraseResp.getPhraseId()));
        }
    }
}
