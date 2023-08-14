package com.example.twitterAnalog.domain.api.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhraseResp {

    private long phraseId;
    //private long userId;
    //private String nickname;
    private String text;
    private String timeInsert;
    private List<TagResp> tags;

}
