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
@NoArgsConstructor
@AllArgsConstructor
public class SearchTagReq {

    @NotBlank(message = "partTag должен быть заполнен")
    @Pattern(regexp = RegExp.tag, message = "Некорректный partTag")
    private String partTag;
}
