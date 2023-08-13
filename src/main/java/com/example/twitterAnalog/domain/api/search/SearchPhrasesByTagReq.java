package com.example.twitterAnalog.domain.api.search;

import com.example.twitterAnalog.domain.constant.Sort;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchPhrasesByTagReq {

    @DecimalMin(value = "1", message = "Значение tagId должно быть больше 0")
    private long tagId;

    @NotNull(message = "sort должен быть заполнен")
    private Sort sort;
}
