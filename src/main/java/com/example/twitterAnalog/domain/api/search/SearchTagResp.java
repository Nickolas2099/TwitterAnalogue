package com.example.twitterAnalog.domain.api.search;

import com.example.twitterAnalog.domain.api.common.TagResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchTagResp {

    private List<TagResp> tags;
}
