package com.maniBlog.BlogbackEnd.PayLoad;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String  content;
    private Set<CommentDto> comments;
}
