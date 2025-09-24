package com.saigewoods.clientmanagement.controller;

import com.saigewoods.clientmanagement.model.Client;
import com.saigewoods.clientmanagement.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientRepository repo;


    public ClientController(ClientRepository repo) {
        this.repo = repo;
    }


    @GetMapping
    public List<Client> getAll() {
        return repo.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        Optional<Client> c = repo.findById(id);
        return c.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Client saved = repo.save(client);
        return ResponseEntity.status(201).body(saved);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        return repo.findById(id).map(existing -> {
            existing.setFirstName(client.getFirstName());
            existing.setLastName(client.getLastName());
            existing.setEmail(client.getEmail());
            existing.setBirthday(client.getBirthday());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}