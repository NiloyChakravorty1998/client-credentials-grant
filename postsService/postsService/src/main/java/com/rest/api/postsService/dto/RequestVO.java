package com.rest.api.postsService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class RequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String userId;
    private String id;
}
