package org.sb.task.apipack.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private String email;
    private String name;

    @Column(name = "created_at")
    private String createdAt;

    public User setAge(int age) {
        this.age = age;

        return this;
    }

    public User setEmail(String email) {
        this.email = email;

        return this;
    }

    public User setName(String name) {
        this.name = name;

        return this;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
