package com.example.twitterAnalog.service.common;

import com.example.twitterAnalog.domain.api.common.PhraseResp;

import java.util.List;

public interface CommonService {

//    void checkBlockByPhraseId(long userId, long phraseId);

//    void checkBlockByUserId(long userId, long checkBlockUserId);

    void phraseEnrichment(List<PhraseResp> phrases);
}
