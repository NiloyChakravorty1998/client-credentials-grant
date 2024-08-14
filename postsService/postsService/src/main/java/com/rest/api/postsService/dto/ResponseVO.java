package com.rest.api.postsService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Object response;
    private Exception exception;
}
