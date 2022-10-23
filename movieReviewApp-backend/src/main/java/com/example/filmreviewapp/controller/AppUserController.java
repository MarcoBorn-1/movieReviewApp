package com.example.filmreviewapp.controller;

import com.example.filmreviewapp.dto.UserDTO;
import com.example.filmreviewapp.entity.Actor;
import com.example.filmreviewapp.entity.AppUser;
import com.example.filmreviewapp.repository.AppUserRepository;
import com.example.filmreviewapp.service.AppUserManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/users")
public class AppUserController {
    private final AppUserManager appUserManager;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/all")
    public List<UserDTO> getAll() {
        return appUserManager.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/get/{userID}")
    public Optional<AppUser> getById(@PathVariable Long userID) {
        return appUserManager.findById(userID);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public AppUser add(@RequestBody AppUser appUser){
        return appUserManager.save(appUser);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/update")
    public AppUser update(@RequestBody AppUser appUser) {
        return appUserManager.save(appUser);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/del/{userID}")
    public void delete(@PathVariable Long userID) {
        appUserManager.deleteById(userID);
    }
}
