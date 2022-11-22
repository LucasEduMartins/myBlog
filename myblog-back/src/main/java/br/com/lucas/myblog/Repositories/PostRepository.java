package br.com.lucas.myblog.Repositories;

import br.com.lucas.myblog.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitle(String title);
    Optional<Post> findByTitle(String title);

    boolean existsByTitle(String title);
}
