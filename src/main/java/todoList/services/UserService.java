package todoList.services;

import todoList.models.User;

import java.util.Optional;

public interface UserService {
    User getUser(Long id);
    User createOrUpdateUser(User user);
    Optional<User> getUserByUsernameAndPassword(String username, String password);
    Optional<User> findByUserName(String username);
}
