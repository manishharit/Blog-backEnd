package com.maniBlog.BlogbackEnd.Controller;

import com.maniBlog.BlogbackEnd.PayLoad.CommentDto;
import com.maniBlog.BlogbackEnd.Service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    //-------------> Create Comments<----------------------------------
    @PostMapping
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postId") Long postId,
            @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    //-------------> Get All Comments By PostId<----------------------------------
    @GetMapping
    public List<CommentDto> getCommentsByPostId(@PathVariable(name = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    //-------------> Get All Comments By Id<----------------------------------
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId,commentId));
    }

    //-------------> Update Comment By Id<----------------------------------
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(
            @PathVariable(name = "postId") Long postId,
            @Valid @RequestBody CommentDto commentDto,
            @PathVariable(name = "commentId") Long commentId){
        return ResponseEntity.ok(commentService.updateCommentById(postId,commentDto,commentId));
    }

    //-------------> Delete Comments By Id<----------------------------------
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteCommentById(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId){
        commentService.deleteCommentById(postId,commentId);
        return ResponseEntity.ok("Comment has been deleted successfully");
    }
}
