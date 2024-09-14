package exercise.controller;

import exercise.dto.CommentDTO;
import exercise.dto.PostDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping(path = "")
    public List<PostDTO> index() {

        List<Post> posts = postRepository.findAll();

        var result = posts.stream().map(it -> postToDto(it, commentToDto(commentRepository.findByPostId(it.getId())))).toList();


        return result;
    }


    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return postToDto(post, commentToDto(commentRepository.findByPostId(post.getId())));
    }

    private PostDTO postToDto(Post post, List<CommentDTO> comments) {
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setComments(comments);
        return dto;
    }

    private List<CommentDTO> commentToDto(List<Comment> comment) {
        return comment.stream().map(it -> commentToDto(it)).toList();

    }

    private CommentDTO commentToDto(Comment comment) {
        var dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        return dto;

    }
}
// END
