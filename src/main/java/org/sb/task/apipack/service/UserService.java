package org.sb.task.apipack.service;

import org.sb.task.apipack.model.User;

import java.util.List;

public interface UserService {
    /**
     * Добавить нового пользователя
     * @param user Пользователь
     * @return Пользователь
     */
    User create(User user);

    /**
     * Получить пользователя по идентификатору
     * @param id Идентификатор пользователя
     * @return Пользователь
     */
    User read(int id);

    /**
     * Получить список всех пользователей
     * @return Список пользователей
     */
    List<User> readAll();

    /**
     * Обновить данные пользователя
     * @param user Пользователь
     * @return Пользователь
     */
    User update(User user);

    /**
     * Удалить пользователя
     * @param id Идентификатор пользователя
     * @return идентификатор удалённого пользователя
     */
    int delete(int id);
}
