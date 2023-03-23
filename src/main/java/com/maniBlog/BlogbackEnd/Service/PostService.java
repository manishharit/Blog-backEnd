package com.maniBlog.BlogbackEnd.Service;

import com.maniBlog.BlogbackEnd.PayLoad.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts(int pageNo, int pageSize);

    PostDto getPostById(Long id);

    PostDto updatePostById(PostDto postDto,Long id);

    void deletePostById(Long id);
}
