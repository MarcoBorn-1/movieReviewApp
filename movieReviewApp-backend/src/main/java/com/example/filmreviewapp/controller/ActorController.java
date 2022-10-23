package com.example.filmreviewapp.controller;

import com.example.filmreviewapp.entity.Actor;
import com.example.filmreviewapp.service.ActorManager;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/actors")
public class ActorController {
    private final ActorManager actorManager;

    @GetMapping("/get/all")
    public List<Actor> getAll(){
        return (List<Actor>) actorManager.findAll();
    }

    @GetMapping(value = "/get/id/{actorID}")
    public Optional<Actor> getById(@PathVariable("actorID") Long actorID) {return actorManager.findById(actorID); }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public Actor add(@RequestBody Actor actor){
        return actorManager.save(actor);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/update")
    public Actor update(@RequestBody Actor actor) {
        return actorManager.save(actor);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/del/{actorID}")
    public void delete(@PathVariable Long actorID) {
        actorManager.deleteById(actorID);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{actorID}/addMovie/{movieID}")
    public boolean addMovie(@PathVariable Long actorID, @PathVariable Long movieID) {
        return actorManager.addMovie(actorID, movieID);
    }


}
