package org.sb.task.apipack.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private int age;

    @Column(name = "created_at", insertable = false)
    private String createdAt;

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public User setId(int id) {
        this.id = id;

        return this;
    }

    public User setName(String name) {
        this.name = name;

        return this;
    }

    public User setEmail(String email) {
        this.email = email;

        return this;
    }

    public User setAge(int age) {
        this.age = age;

        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
