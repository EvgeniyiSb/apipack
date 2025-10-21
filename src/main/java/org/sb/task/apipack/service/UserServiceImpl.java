package org.sb.task.apipack.service;

import org.sb.task.apipack.model.User;
import org.sb.task.apipack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        User userSaved = userRepository.save(user);

        return userSaved;
    }

    @Override
    public User read(int id) {
        Optional<User> userOptional = userRepository.findById(id);

        return userOptional.orElse(null);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(int id, User user) {
        if(!userRepository.existsById(id)){
            return null;
        }

        user.setId(id);
        userRepository.save(user);

        return user;
    }

    @Override
    public int delete(int id) {
        if (!userRepository.existsById(id)){
            return -1;
        }

        userRepository.deleteById(id);

        return id;
    }
}
