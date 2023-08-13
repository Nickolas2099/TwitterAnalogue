package com.example.twitterAnalog.dao;

import com.example.twitterAnalog.domain.api.common.TagResp;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommonDao {

    List<TagResp> getTagsByPhraseId(long phraseId);
    long getUserIdByToken(String accessToken);}
