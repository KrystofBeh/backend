package org.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.backend.Classes.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}