package com.example.filmreviewapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class LoginDTO {
    String email;
    String password;
}
