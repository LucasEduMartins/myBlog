package br.com.lucas.myblog.Services;

import br.com.lucas.myblog.Exceptions.CannotFindResourceException;
import br.com.lucas.myblog.Exceptions.DuplicatedDataException;
import br.com.lucas.myblog.Models.User;
import br.com.lucas.myblog.Repositories.UserRepository;
import br.com.lucas.myblog.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userService;

    @Transactional
    public User saveUser(User user) {
        boolean emailExist = userService.existsByEmail(user.getEmail());
        if (emailExist)
            throw new DuplicatedDataException("There is already a user with that email!");

        return userService.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        boolean userExist = userService.existsById(user.getId());
        if (!userExist)
            throw new CannotFindResourceException("User not found!");

        boolean emailAlreadyInUse = userService.findByEmail(user.getEmail()).stream().anyMatch(userFound -> !Objects.equals(user.getId(), userFound.getId()));
        if (emailAlreadyInUse)
            throw new DuplicatedDataException("There is already a user with that email!");

        return userService.save(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        boolean userExist = userService.existsById(userId);
        if (!userExist)
            throw new CannotFindResourceException("User not found!");

        userService.deleteById(userId);
    }

    @Transactional
    public List<User> getAllUsers(String email) {
        if (!email.isEmpty())
            return userService.findAllByEmail(email);
        return userService.findAll();
    }

    @Transactional
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty())
            throw new CannotFindResourceException("Could not find this user!");
        return optionalUser.get();
    }
}
