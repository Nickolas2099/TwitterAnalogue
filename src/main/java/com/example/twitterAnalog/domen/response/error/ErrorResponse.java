package com.example.twitterAnalog.domen.response.error;

import com.example.twitterAnalog.domen.response.Response;
import com.example.twitterAnalog.domen.response.error.Error;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse implements Response {
    private Error error;
}
