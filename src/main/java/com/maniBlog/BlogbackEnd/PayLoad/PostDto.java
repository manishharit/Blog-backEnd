package com.maniBlog.BlogbackEnd.PayLoad;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class PostDto {
    private Long id;
    @NotEmpty
    @Size(min = 10,message = "Post title should have at least 10 characters")
    private String title;
    @NotEmpty
    @Size(min = 30,message = "Post description should have at least 30 characters")
    private String description;
    @NotEmpty
    private String  content;
    private Set<CommentDto> comments;

    private Long categoryId;
}
