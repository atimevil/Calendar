package com.sparta.calendar.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
// id, nickname, username, password, privilege, day
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 4, max = 10)
    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String username;

    @Length(min = 8, max = 15)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> comments;

    public User(String nickname, String username, String password, UserRoleEnum role) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
