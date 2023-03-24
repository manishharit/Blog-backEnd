package com.maniBlog.BlogbackEnd.Service.Impl;

import com.maniBlog.BlogbackEnd.Entity.Comment;
import com.maniBlog.BlogbackEnd.Entity.Post;
import com.maniBlog.BlogbackEnd.Execption.ResourceNotFoundExecption;
import com.maniBlog.BlogbackEnd.PayLoad.CommentDto;
import com.maniBlog.BlogbackEnd.Repository.CommentRepository;
import com.maniBlog.BlogbackEnd.Repository.PostRepository;
import com.maniBlog.BlogbackEnd.Service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundExecption("Post","id",postId));

        comment.setPost(post);

        return mapToDto(commentRepository.save(comment));
    }




    private CommentDto mapToDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody()).build();
    }

    private Comment mapToEntity(CommentDto commentDto){
        return Comment.builder()
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .body(commentDto.getBody()).build();
    }
}
