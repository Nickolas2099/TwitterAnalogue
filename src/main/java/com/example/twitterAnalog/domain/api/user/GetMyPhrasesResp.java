package com.example.twitterAnalog.domain.api.user;

import com.example.twitterAnalog.domain.api.common.PhraseResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMyPhrasesResp {
    private List<PhraseResp> phrases;
}
