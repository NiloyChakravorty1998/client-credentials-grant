package com.rest.api.postsService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CommentRequestVO implements Serializable {
        private static final long serialVersionUID = 1L; // Recommended for version control
        private String postId;
        private String id;
    }


