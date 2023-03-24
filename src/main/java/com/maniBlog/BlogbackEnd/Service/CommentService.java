package com.maniBlog.BlogbackEnd.Service;

import com.maniBlog.BlogbackEnd.PayLoad.CommentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateCommentById(Long postId, CommentDto commentDto, Long commentId);

    void deleteCommentById(Long postId, Long commentId);
}
