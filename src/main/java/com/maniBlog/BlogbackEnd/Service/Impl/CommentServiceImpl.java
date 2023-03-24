package com.maniBlog.BlogbackEnd.Service.Impl;

import com.maniBlog.BlogbackEnd.Entity.Comment;
import com.maniBlog.BlogbackEnd.Entity.Post;
import com.maniBlog.BlogbackEnd.Execption.BlogApiExecption;
import com.maniBlog.BlogbackEnd.Execption.ResourceNotFoundExecption;
import com.maniBlog.BlogbackEnd.PayLoad.CommentDto;
import com.maniBlog.BlogbackEnd.Repository.CommentRepository;
import com.maniBlog.BlogbackEnd.Repository.PostRepository;
import com.maniBlog.BlogbackEnd.Service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundExecption("Post","id",postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundExecption("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiExecption(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        return  mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(Long postId, CommentDto commentDto, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundExecption("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundExecption("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiExecption(HttpStatus.BAD_REQUEST,"Comment does not belong to Post");
        }

        comment.setBody(commentDto.getBody());
        commentRepository.save(comment);
        return mapToDto(comment);
    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundExecption("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundExecption("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiExecption(HttpStatus.BAD_REQUEST,"Comment does not belong to Post");
        }
        commentRepository.deleteById(commentId);
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
