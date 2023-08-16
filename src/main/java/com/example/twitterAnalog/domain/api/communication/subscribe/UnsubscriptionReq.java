package com.example.twitterAnalog.domain.api.communication.subscribe;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnsubscriptionReq {

    @DecimalMin(value = "1", message = "Значение pubUserId должно быть больше 0")
    private long pubUserId;
}
