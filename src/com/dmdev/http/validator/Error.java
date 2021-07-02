package com.dmdev.http.validator;

import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
    String errorCode;
    String errorMessage;
}
