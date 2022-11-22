package br.com.lucas.myblog.Services;

import br.com.lucas.myblog.Exceptions.CannotFindResourceException;
import br.com.lucas.myblog.Exceptions.InvalidReferenceIdException;
import br.com.lucas.myblog.Exceptions.DuplicatedDataException;
import br.com.lucas.myblog.Models.User;
import br.com.lucas.myblog.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    @Transactional
    public User saveUser(User user) {
        boolean emailExist = userRepository.existsByEmail(user.getEmail());
        if (emailExist)
            throw new DuplicatedDataException("There is already a user with that email!");

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        boolean userExist = userRepository.existsById(user.getId());
        if (!userExist)
            throw new CannotFindResourceException("User not found!");

        boolean emailAlreadyInUse = userRepository.findByEmail(user.getEmail()).stream().anyMatch(userFound -> !Objects.equals(user.getId(), userFound.getId()));
        if (emailAlreadyInUse)
            throw new DuplicatedDataException("There is already a user with that email!");

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        boolean userExist = userRepository.existsById(userId);
        if (!userExist)
            throw new CannotFindResourceException("User not found!");

        userRepository.deleteById(userId);
    }

    @Transactional
    public List<User> getAllUsers(String email) {
        if (!email.isEmpty())
            return userRepository.findAllByEmail(email);
        return userRepository.findAll();
    }

    @Transactional
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CannotFindResourceException("Could not find this user!"));
    }

    @Transactional
    public User getRelatedUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new InvalidReferenceIdException("User not found!"));
    }
}
