package com.ds2technologies.spring.exceptions;

import java.io.IOException;

public class TechnicalException extends RuntimeException {
    public TechnicalException(String errorCode, String errorMessage, IOException e) {
    }
}
