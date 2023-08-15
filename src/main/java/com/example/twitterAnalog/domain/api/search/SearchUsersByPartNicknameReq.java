package com.example.twitterAnalog.domain.api.search;

import com.example.twitterAnalog.domain.constant.RegExp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchUsersByPartNicknameReq {

    @NotBlank(message = "partNickname не должен быть пустым")
    @Pattern(regexp = RegExp.partNickname, message = "Некорректный partNickname")
    private String partNickname;
}
