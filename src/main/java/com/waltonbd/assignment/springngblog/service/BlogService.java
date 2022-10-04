package com.waltonbd.assignment.springngblog.service;

import com.waltonbd.assignment.springngblog.dto.BlogCommentRequest;
import com.waltonbd.assignment.springngblog.dto.PostCollectionRequest;
import com.waltonbd.assignment.springngblog.dto.PostDto;
import com.waltonbd.assignment.springngblog.dto.PostUpdateRequest;
import com.waltonbd.assignment.springngblog.model.BlogComments;
import com.waltonbd.assignment.springngblog.model.Post;
import com.waltonbd.assignment.springngblog.model.User;
import com.waltonbd.assignment.springngblog.repository.BlogCommentsRepository;
import com.waltonbd.assignment.springngblog.repository.PostRepository;
import com.waltonbd.assignment.springngblog.repository.UserRepository;
import com.waltonbd.assignment.springngblog.security.JwtProvider;
import com.waltonbd.assignment.springngblog.security.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {
    @Autowired
    private PostService postService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserRepository userRepo;

    @Autowired
    PostRepository postRepo;

    @Autowired
    UserService userService;


    @Autowired
    BlogCommentsRepository blogCommentsRepo;


    public String createNewPost(PostDto request) {
        String response;
        if (jwtProvider.validateToken(request.getAuthToken())) {
            User user = userRepo.findByUserName(request.getUsername()).get();
            if (user != null && "active".equals(user.getStatus())) {
                postService.createPost(request);
                response = "Successfully create new post.";
            } else {
                response = "Invalid user.";
            }
        } else {
            response = "Invelid Auth Token";
        }
        return response;
    }

    public List<Post> getAllPost(PostCollectionRequest request) {
        List<Post> postList = new ArrayList<>();
        if (jwtProvider.validateToken(request.authToken)) {
            postList = postRepo.showAllActivePost("Active").stream().collect(Collectors.toList());
            postList.addAll(postRepo.showAllOwnPost(request.userName, "submit"));
            postList.stream().sorted((post1, post2) -> post1.getUpdatedOn().compareTo(post2.getUpdatedOn())).collect(Collectors.toList());
        } else {
            //TODO Prepare response
        }
        return postList;
    }

//    private boolean validateAdmin(String userName){
//        return "admin".equalsIgnoreCase(userRepo.findByUserName(userName).get().getRoles());
//    }

    public List<Post> getAllSubmittedPost(String status) {
        return postRepo.showAllActivePost(status);
    }

    public String updatePostStatus(PostUpdateRequest request) {
        String response;

        if (jwtProvider.validateToken(request.adminAuthToken)) {
            if (userService.validateAdmin(request.adminUser)) {
                try {
                    updatePost(request.postId);
                    response = "Successfully update post.";
                } catch (Throwable throwable) {
                    response = "Failed to update post!";
                }
            } else {
                response = "Not a Admin Request!";
            }
        } else {
            response = "Auth Token mismatch!";
        }

        return response;
    }

    public String deletePost(PostUpdateRequest request) {
        String response;

        if (jwtProvider.validateToken(request.adminAuthToken)) {
            if (userService.validateAdmin(request.adminUser) || validateOwner(request.userInfo.userName, request.postId)) {
                try {
                    deletePost(request.postId);
                    response = "Successfully delete post.";
                } catch (Throwable throwable) {
                    response = "Failed to delete post!";
                }
            } else {
                response = "Not a Admin Request!";
            }
        } else {
            response = "Auth Token mismatch!";
        }

        return response;
    }

    private void updatePost(Long postId) {
        Post post = postRepo.findById(postId).get();
        post.setStatus("approved");
        postRepo.save(post);
    }

    private void deletePost(Long postId) {
        postRepo.deleteById(postId);
    }

    private boolean validateOwner(String userName, Long postId) {
        return userName.equalsIgnoreCase(postRepo.findById(postId).get().getUsername());
    }

    public void saveComment(BlogCommentRequest request) {
        BlogComments blogComments = new BlogComments();
        blogComments.setPost_id((long) request.post_id);
        blogComments.setUser_id((long) request.user_id);
        blogComments.setComment(request.comment);
        blogCommentsRepo.save(blogComments);
    }
}
