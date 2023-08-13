package com.example.twitterAnalog.domain.api.search;

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
public class SearchPhrasesByTagResp {

    private List<PhraseResp> phrases;
}
