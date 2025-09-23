package com.repository;

java
        package com.saigewoods.clientmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.saigewoods.clientmanagement.model.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
}







