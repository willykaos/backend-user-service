package com.ditech.backend.repository;

import com.ditech.backend.model.UserDitech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDitech, Long> {
}
