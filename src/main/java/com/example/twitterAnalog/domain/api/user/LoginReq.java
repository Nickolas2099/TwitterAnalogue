package com.example.twitterAnalog.domain.api.user;

import com.example.twitterAnalog.domain.api.user.Authorization;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {

    @NotNull(message = "authorization должен быть заполнен")
    private Authorization authorization;
}
