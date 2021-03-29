package com.derecepten.restapi.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Created by sergioh on 03/22/2021
 **/
public class ApiErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;

    protected ApiErrorResponse() {
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static final class ApiErrorResponseBuilder {
        private int status;
        private LocalDateTime timestamp;
        private String error;

        protected ApiErrorResponseBuilder() {
        }

        public static ApiErrorResponseBuilder aApiErrorResponse() {
            return new ApiErrorResponseBuilder();
        }

        public ApiErrorResponseBuilder withStatus(int status) {
            this.status = status;
            return this;
        }

        public ApiErrorResponseBuilder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ApiErrorResponseBuilder withError(String error) {
            this.error = error;
            return this;
        }

        public ApiErrorResponse build() {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.setStatus(status);
            apiErrorResponse.setTimestamp(timestamp);
            apiErrorResponse.setError(error);
            return apiErrorResponse;
        }
    }
}
