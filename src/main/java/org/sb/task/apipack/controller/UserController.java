package org.sb.task.apipack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.sb.task.apipack.model.User;
import org.sb.task.apipack.service.UserService;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Контроллер работы с пользователями
 */
@RestController
@RequestMapping("/users")
@Tag(name = "User API", description = "Микросервис работы с пользователями")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Добавить нового пользователя
     * @param user Добавляемый пользователь
     * @return Добавленный пользователь
     */
    @PostMapping(value = "/add")
    @Operation(summary = "Добавить нового пользователя", description = "Создание нового пользователя")
    public ResponseEntity<?> create(
            @Parameter(description = "Пользователь", required = true)
            @RequestBody User user){
        User userCreated = userService.create(user);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", userCreated.getId());

        return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
    }

    /**
     * Получить пользователя по идентификатору пользователя
     * @param id Идентификатор пользователя
     * @return Пользователь
     */
    @GetMapping("/get/{id}")
    @Operation(summary = "Получить пользователя по идентификатору пользователя", description = "Получение пользоватея по идентификатору")
    public ResponseEntity<User> read(
            @Parameter(description = "Идентификатор пользователя", required = true)
            @PathVariable(name = "id") int id) throws IOException, InterruptedException {
        final User user = userService.read(id);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(getUserWithHATEOAS(user), HttpStatus.OK);
    }

    /**
     * Получить список всех пользователей
     * @return Список пользователей
     */
    @GetMapping(value = "/get/all")
    @Operation(summary = "Получить список всех пользователей", description = "Получение списка всех пользователей")
    public ResponseEntity<List<User>> findAll() throws IOException, InterruptedException {
        List<User> userList = userService.readAll();
        List<User> userListTemp = new ArrayList<>();

        if (userList.isEmpty() || userList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (User user: userList){
            user.add(linkTo(methodOn(UserController.class).read(user.getId())).withSelfRel());
            userListTemp.add(user);
        }

//        GatewayHandler test = new GatewayHandler();


        return new ResponseEntity<>(userListTemp, HttpStatus.OK);
    }

    /**
     * Обновить данные пользователя
     * @param id Идентификатор пользователя
     * @param user Изменяемый пользователь
     * @return Изменённый пользователь
     */
    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Обновить данные пользователя", description = "Обновлние данных пользователя")
    public ResponseEntity<?> update(
            @Parameter(description = "Идентификатор пользователя", required = true)
            @PathVariable(name = "id") int id,
            @Parameter(description = "Изменяемый пользователь", required = true)
            @RequestBody User user) throws IOException, InterruptedException {

        User userUpdated = userService.update(id, user);

        if (userUpdated == null){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        return new ResponseEntity<>(getUserWithHATEOAS(userUpdated), HttpStatus.OK);
    }

    /**
     * Удалить пользователя по идентификатору пользователя
     * @param id Идентификатор удаляемого пользователя
     * @return (Идентификатор удалённого пользователя, HTTP-статус операции)
     */
    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Удалить пользователя по идентификатору пользователя", description = "Удаление пользователя")
    public ResponseEntity<?> delete(
            @Parameter(description = "Идентификатор пользователя", required = true)
            @PathVariable(name = "id") int id){
        int idDeleted = userService.delete(id);

        if(idDeleted == -1){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", idDeleted);

        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    /**
     * Добавить HATEOAS-ссылки
     * @param user Пользователь
     * @return Пользователь с ссылками HATEOAS
     */
    private User getUserWithHATEOAS(User user) throws IOException, InterruptedException {
        Link self = linkTo(methodOn(UserController.class).read(user.getId())).withSelfRel();
        Link all = linkTo(methodOn(UserController.class).findAll()).withRel("all");

        return user.add(self).add(all);
    }
}
