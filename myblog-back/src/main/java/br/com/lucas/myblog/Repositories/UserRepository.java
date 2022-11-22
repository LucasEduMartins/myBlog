package br.com.lucas.myblog.Repositories;

import br.com.lucas.myblog.Models.Post;
import br.com.lucas.myblog.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByEmail(String email);
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
