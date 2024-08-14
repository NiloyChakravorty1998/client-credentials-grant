package com.rest.api.postsService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String userId;
    private String id;
    private String title;
    private String body;
}
