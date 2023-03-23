package com.maniBlog.BlogbackEnd.Service;

import com.maniBlog.BlogbackEnd.PayLoad.PostDto;
import com.maniBlog.BlogbackEnd.PayLoad.PostResponse;



public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePostById(PostDto postDto,Long id);

    void deletePostById(Long id);
}
