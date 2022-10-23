package com.example.filmreviewapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class RegistrationDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
}
