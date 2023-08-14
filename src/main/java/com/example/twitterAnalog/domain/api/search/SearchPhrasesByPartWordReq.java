package com.example.twitterAnalog.domain.api.search;

import com.example.twitterAnalog.domain.constant.RegExp;
import com.example.twitterAnalog.domain.constant.Sort;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchPhrasesByPartWordReq {

    @NotBlank(message = "partWord должен быть заполнен")
    @Pattern(regexp = RegExp.partWord, message = "Некорректный partWord")
    private String partWord;

    @NotNull(message = "sort должен быть заполнен")
    private Sort sort;
}
