package com.maniBlog.BlogbackEnd.PayLoad;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

    private  Long id;
    private String name;
    private String email;
    private String body;
}
