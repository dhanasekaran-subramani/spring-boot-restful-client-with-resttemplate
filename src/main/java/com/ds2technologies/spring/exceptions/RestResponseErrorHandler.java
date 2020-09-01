package com.ds2technologies.spring.exceptions;

import com.ds2technologies.spring.model.ErrorMessageDetail;
import com.ds2technologies.spring.model.RestResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

@Slf4j
public class RestResponseErrorHandler implements ResponseErrorHandler {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
    }
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().is4xxClientError() || clientHttpResponse.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        RuntimeException exception;
        try {
            InputStream is = clientHttpResponse.getBody();
            String body = IOUtils.toString(is, Charset.forName("UTF-8"));
            log.error("RestResponseErrorHandler - handleError - RestResponse Body - {}", body);
            RestResponse restResponse = objectMapper.readValue(body, new TypeReference<RestResponse>() {
            });
            ErrorMessageDetail errorMessageDetail = new ErrorMessageDetail();
            List<ErrorMessageDetail> errorMessageDetails = restResponse.getErrorMessageDetails();
            if (CollectionUtils.isNotEmpty(errorMessageDetails)) {
                errorMessageDetail = errorMessageDetails.get(0);
            }
            exception = new FunctionalException(errorMessageDetail);
        } catch (IOException e) {
            log.trace(e.getMessage(), e);
            String errorCode = String.format("T-REST-%03d", clientHttpResponse.getRawStatusCode());
            String errorMessage = String.format("Unable to parse response : %03d %s", clientHttpResponse.getRawStatusCode(),
                    clientHttpResponse.getStatusText());
            exception = new TechnicalException(errorCode, errorMessage, e);
        }
        throw exception;
    }

    }

