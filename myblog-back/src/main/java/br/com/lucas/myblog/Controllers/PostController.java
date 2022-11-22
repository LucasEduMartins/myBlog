
package br.com.lucas.myblog.Controllers;

import br.com.lucas.myblog.Models.Post;
import br.com.lucas.myblog.Services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam(value = "title", defaultValue = "") String title){
        return  new ResponseEntity<>(postService.getAllPosts(title), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId){
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody() Post post){
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId") Long postId, @Valid @RequestBody() Post post){
        post.setId(postId);
        return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}