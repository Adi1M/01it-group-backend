package com.itgroup.dto;

import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class CommentRequest {
    private String content;
    private double rating;
}
