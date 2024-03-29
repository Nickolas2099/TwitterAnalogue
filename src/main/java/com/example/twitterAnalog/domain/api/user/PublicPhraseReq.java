package com.example.twitterAnalog.domain.api.user;

import com.example.twitterAnalog.domain.constant.RegExp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicPhraseReq {

//    @NotNull(message = "authorization должен быть заполнен")
//    private Authorization authorization;

    @NotBlank(message = "text должен быть заполненым")
    @Pattern(regexp = RegExp.phrase, message = "Некорректный text")
    private String text;

    @Size(max = 5, message = "Количество тегов не должно превышать 5")
    private List<
            @NotBlank(message = "tag должен быть заполнен")
            @Pattern(regexp = RegExp.tag, message = "Некорректный tag")
                    String > tags;
}
