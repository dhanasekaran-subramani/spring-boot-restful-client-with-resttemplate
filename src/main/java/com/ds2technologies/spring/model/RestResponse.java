package com.ds2technologies.spring.model;

import lombok.Data;

import java.util.List;

@Data
public class RestResponse {
    private List<ErrorMessageDetail> errorMessageDetails;
}
