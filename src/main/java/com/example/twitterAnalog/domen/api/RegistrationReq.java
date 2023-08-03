package com.example.twitterAnalog.domen.api;


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
public class RegistrationReq {
    @NotBlank(message = "nickname должен быть заполнен")
    @Pattern(regexp = "^[a-zA-Z0-9а-яА-Я. _-]{4,15}$", message = "Некорректный nickname")
    private String nickname;

    @NotBlank(message = "password должен быть заполнен")
    @Pattern(regexp = "^[a-zA-Z0-9а-яА-Я.,:; _?!+=/'\\\\\"*(){}//[//]//-]{8,100}$", message = "Некорректный password")
    private String password;
}
