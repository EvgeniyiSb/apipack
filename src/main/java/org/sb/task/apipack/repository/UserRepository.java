package org.sb.task.apipack.repository;

import org.sb.task.apipack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
