package com.example.twitterAnalog.domain.api.communication.reaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResp {

    private long userId;
    private String nickname;
    private long commentId;
    private String text;
    private String timeInsert;

}
