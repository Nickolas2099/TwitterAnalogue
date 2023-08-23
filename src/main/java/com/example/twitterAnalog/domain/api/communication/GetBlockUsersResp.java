package com.example.twitterAnalog.domain.api.communication;

import com.example.twitterAnalog.domain.api.common.UserResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBlockUsersResp {

    List<UserResp> blockUsers;
}
