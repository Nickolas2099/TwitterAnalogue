package com.example.twitterAnalog.domain.api.communication.reaction;

import com.example.twitterAnalog.domain.constant.RegExp;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentPhraseReq {

    @DecimalMin(value = "1", message = "Значение phraseId должно быть больше 0")
    private long phraseId;

    @NotBlank(message = "text должен быть заполнен")
    @Pattern(regexp = RegExp.phrase, message = "Некорректный text")
    private String text;

}
