package br.com.lucas.myblog.Services;

import br.com.lucas.myblog.Exceptions.CannotFindResourceException;
import br.com.lucas.myblog.Exceptions.DuplicatedDataException;
import br.com.lucas.myblog.Models.Post;
import br.com.lucas.myblog.Repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostService {

    private PostRepository postRepository;

    @Transactional
    public Post savePost(Post post){
        boolean titleExist = postRepository.existsByTitle(post.getTitle());
        if(titleExist)
            throw new DuplicatedDataException("There is already a post with that title!");

        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Post post){
        boolean postExist = postRepository.existsById(post.getId());
        if(!postExist)
            throw new CannotFindResourceException("Post not found!");

        boolean titleExistInAnotherPost = postRepository.findByTitle(post.getTitle()).stream().anyMatch(postFound -> !post.equals(postFound));
        if(titleExistInAnotherPost)
            throw new DuplicatedDataException("There is already a post with that title!");

        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId){
        boolean postExist = postRepository.existsById(postId);
        if(!postExist)
            throw new CannotFindResourceException("Post not found!");

        postRepository.deleteById(postId);
    }

    @Transactional
    public List<Post> getAllPosts(String title){
        if (!title.isEmpty())
            return postRepository.findAllByTitle(title);
        return  postRepository.findAll();
    }

    @Transactional
    public Post getPostById(Long postId){
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(optionalPost.isEmpty())
            throw new CannotFindResourceException("Could not find this post!");
        return optionalPost.get();
    }
}
