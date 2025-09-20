package com.example.domain.repository.code;


import com.example.domain.entity.code.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, String> {

    public Optional<Code> findByCode(String code);
}
