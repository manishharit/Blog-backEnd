package com.maniBlog.BlogbackEnd.PayLoad;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

    private  Long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "email should not be empty")
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10,message = "Comment should be of minimum 10 characters")
    private String body;
}
