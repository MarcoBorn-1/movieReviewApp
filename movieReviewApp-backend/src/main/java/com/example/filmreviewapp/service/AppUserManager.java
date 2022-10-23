package com.example.filmreviewapp.service;

import com.example.filmreviewapp.dto.UserDTO;
import com.example.filmreviewapp.entity.AppUser;
import com.example.filmreviewapp.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AppUserManager implements UserDetailsService {
    private final AppUserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

    public ResponseEntity<String> registerUser(AppUser user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    public List<UserDTO> findAll() {
        List<AppUser> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (AppUser user : userList) {
            userDTOList.add(new UserDTO(user));
        }
        return userDTOList;
    }

    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    public AppUser save(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public void deleteById(Long userID) {
        userRepository.deleteById(userID);
    }
}
