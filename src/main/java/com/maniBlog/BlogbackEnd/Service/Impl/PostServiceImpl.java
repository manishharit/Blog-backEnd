package com.maniBlog.BlogbackEnd.Service.Impl;

import com.maniBlog.BlogbackEnd.Entity.Category;
import com.maniBlog.BlogbackEnd.Entity.Post;
import com.maniBlog.BlogbackEnd.Execption.BlogApiExecption;
import com.maniBlog.BlogbackEnd.Execption.ResourceNotFoundExecption;
import com.maniBlog.BlogbackEnd.PayLoad.PostDto;
import com.maniBlog.BlogbackEnd.PayLoad.PostResponse;
import com.maniBlog.BlogbackEnd.Repository.CategoryRepository;
import com.maniBlog.BlogbackEnd.Repository.PostRepository;
import com.maniBlog.BlogbackEnd.Service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private ModelMapper mapper;

    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(
                ()-> new ResourceNotFoundExecption("Category","id",postDto.getCategoryId()));

        Post post = mapToEntity(postDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);
        return mapToDto(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();

        List<PostDto> postContent = postList.stream().map(this::mapToDto).collect(Collectors.toList());
        return PostResponse.builder()
                .content(postContent)
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .last(posts.isLast()).build();
    }

    @Override
    public PostDto getPostById(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExecption("Post","id",id));
    return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto,Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExecption("Post","id",id));

        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(
                ()->new ResourceNotFoundExecption("Category","id",id)
        );

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);
        return  mapToDto(postRepository.save(post));
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExecption("Post","id",id));
        postRepository.deleteById(id);
    }

    private PostDto mapToDto(Post post){
       return mapper.map(post,PostDto.class);
//         PostDto postDto = new PostDto();
//                postDto.setId(post.getId());
//                postDto.setTitle(post.getTitle());
//                postDto.setDescription(post.getDescription());
//                postDto.setContent(post.getContent());
//        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
       return mapper.map(postDto,Post.class);
//        Post post = new Post();
//                postDto.setTitle(postDto.getTitle());
//                postDto.setDescription(postDto.getDescription());
//                postDto.setContent(postDto.getContent());
//                postDto.setComments(postDto.getComments());
//                return post;
    }
}
