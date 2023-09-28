package com.kolev.stock.app.myapp.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected boolean success;
    protected Map<String, Object> data;

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    public static class ResponseBuilder {
        private LocalDateTime timeStamp;
        private int statusCode;
        private HttpStatus status;
        private String reason;
        private String message;
        private String developerMessage;
        private boolean success;
        private Map<String, Object> data = new HashMap<>();

        public ResponseBuilder timeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public ResponseBuilder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ResponseBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ResponseBuilder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public ResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ResponseBuilder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ResponseBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public ResponseBuilder data(String key, Object value) {
            this.data.put(key, value);
            return this;
        }

        public Response build() {
            return new Response(
                    this.timeStamp,
                    this.statusCode,
                    this.status,
                    this.reason,
                    this.message,
                    this.developerMessage,
                    this.success,
                    this.data
            );
        }
    }
}