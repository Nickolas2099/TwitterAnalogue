package com.example.twitterAnalog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WhoseComment {

    private long commentUserId;
    private long phraseUserId;
}
