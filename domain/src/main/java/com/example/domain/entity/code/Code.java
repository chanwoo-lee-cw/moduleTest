package com.example.domain.entity.code;


import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jetbrains.annotations.NotNull;

@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "code")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String code;

    @Column(unique = true, nullable = false, length = 10, name = "code_name")
    @NotNull
    String codeName;

    @Column(nullable = false, length = 63, name = "code_group")
    @NotNull
    String codeGroup;

}
