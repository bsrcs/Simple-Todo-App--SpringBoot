package todoList.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoList.models.User;
import todoList.repos.UserRepo;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User getUser(Long id) {
        try {
            if(userRepo.findById(id).isPresent()){
                return userRepo.findById(id).get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User createOrUpdateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserByUsernameAndPassword(String username, String password) {
        return userRepo.findByUsernameAndPassword(username,password);
    }
}
