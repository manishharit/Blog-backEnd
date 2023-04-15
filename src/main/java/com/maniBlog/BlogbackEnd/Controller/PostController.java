package com.maniBlog.BlogbackEnd.Controller;

import com.maniBlog.BlogbackEnd.PayLoad.PostDto;
import com.maniBlog.BlogbackEnd.PayLoad.PostResponse;
import com.maniBlog.BlogbackEnd.Service.PostService;
import com.maniBlog.BlogbackEnd.Utils.BlogConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "POST APIs Collection"
)
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    //------------> Create New Post <----------------
    @Operation(
            summary = "CREATE POST REST API ----",
            description = "CREATE POST REST API responsible for creating new post and saving it to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

//-------------> Get All Posts <----------------------------------
    @Operation(
            summary = "GET ALL POST  REST API ----",
            description = "GET ALL POST REST API responsible for fetching all posts from  database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = BlogConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = BlogConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = BlogConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = BlogConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    //-------------> Get All Posts By Id <----------------------------------
    @Operation(
            summary = "GET POST BY ID REST API ----",
            description = "GET POST BY ID REST API responsible for fetching single post from  database by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //-------------> Update Posts By Id <----------------------------------
    @Operation(
            summary = "UPDATE POST BY ID REST API ----",
            description = "UPDATE POST BY ID REST API responsible for updating posts onto  database by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto,@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.updatePostById(postDto,id));
    }

    //-------------> Delete Posts By Id <----------------------------------
    @Operation(
            summary = "DELETE POST BY ID REST API ----",
            description = "DELETE POST BY ID REST API responsible for deleting single post from  database by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") Long id){
        postService.deletePostById(id);
        return ResponseEntity.ok("Post has been deleted successfully");
    }

    //-------------> Get Posts By Category <----------------------------------
    @Operation(
            summary = "GET POST BY CATEGORY ID REST API ----",
            description = "GET POST BY CATEGORY ID REST API responsible for fetching list of post from  database by category Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable(value = "id") Long id){
        List<PostDto> postList = postService.getPostByCategory(id);
        return ResponseEntity.ok(postList);
    }

}
