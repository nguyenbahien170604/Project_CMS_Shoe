package com.CMS_Project.controller;

import com.CMS_Project.dto.request.BlogRequest;
import com.CMS_Project.dto.response.ApiResponse;
import com.CMS_Project.dto.response.BlogResponse;
import com.CMS_Project.service.BlogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BlogController {
    BlogService blogService;

    @PostMapping("/add")
    ApiResponse <BlogResponse> createBlogs(@RequestBody BlogRequest blogRequest){
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.create(blogRequest))
                .build();
    }

    @PutMapping("/{blogId}")
    ApiResponse<BlogResponse> updateBlogs(@PathVariable Integer blogId, @RequestBody BlogRequest blogRequest){
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.update(blogId,blogRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<BlogResponse>> getAllBlogs() {
        return ApiResponse.<List<BlogResponse>>builder()
                .result(blogService.getAll())
                .build();
    }

    @DeleteMapping("/{blogId}")
    ApiResponse<Void> deleteBlogs(@PathVariable Integer blogId) {
        blogService.delete(blogId);
        return ApiResponse.<Void>builder().build();
    }
}
