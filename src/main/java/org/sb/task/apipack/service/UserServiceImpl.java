package org.sb.task.apipack.service;

import org.sb.task.apipack.model.User;
import org.sb.task.apipack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {

        return null;
    }

    @Override
    public User read(int id) {
        return null;
    }

    @Override
    public List<User> readAll() {
        return List.of();
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
