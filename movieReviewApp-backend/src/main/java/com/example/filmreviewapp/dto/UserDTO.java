package com.example.filmreviewapp.dto;

import com.example.filmreviewapp.entity.AppUser;
import com.example.filmreviewapp.entity.Role;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class UserDTO {
    private Long key;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;

    public UserDTO(AppUser user) {
        this.key = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole().toString();
    }
}
