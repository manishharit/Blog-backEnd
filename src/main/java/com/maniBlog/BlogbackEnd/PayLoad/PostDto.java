package com.maniBlog.BlogbackEnd.PayLoad;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Schema(
        description = "POST DTO Model Class"
)
@Data
@NoArgsConstructor
public class PostDto {
    private Long id;
    @Schema(description = "BLOG POST Title field")
    @NotEmpty
    @Size(min = 10,message = "Post title should have at least 10 characters")
    private String title;
    @Schema(description = "BLOG POST Description field")
    @NotEmpty
    @Size(min = 30,message = "Post description should have at least 30 characters")
    private String description;
    @Schema(description = "BLOG POST Content field")
    @NotEmpty
    private String  content;
    @Schema(description = "BLOG POST Comments")
    private Set<CommentDto> comments;

    @Schema(description = "BLOG POST Category-Id field")
    private Long categoryId;
}
