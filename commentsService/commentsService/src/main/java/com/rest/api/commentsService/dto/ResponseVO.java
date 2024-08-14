package com.rest.api.commentsService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for version control
    private Object response;
    private Exception exception;
}
