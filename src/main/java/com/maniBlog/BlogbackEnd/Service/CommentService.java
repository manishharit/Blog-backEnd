package com.maniBlog.BlogbackEnd.Service;

import com.maniBlog.BlogbackEnd.PayLoad.CommentDto;


public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);

}
