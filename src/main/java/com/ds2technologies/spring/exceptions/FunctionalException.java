package com.ds2technologies.spring.exceptions;

import com.ds2technologies.spring.model.ErrorMessageDetail;

public class FunctionalException extends RuntimeException {
    public FunctionalException(ErrorMessageDetail errorMessageDetail) {
    }
}
