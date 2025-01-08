package org.example.abidijasser.repository;

import org.example.abidijasser.entity.Clinique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CliniqueRepository extends JpaRepository<Clinique, Long> {
}
