package com.example.domain.entity.code;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "code_group")
public class CodeGroup {
    @Id
    @Column(nullable = false, name = "code_group")
    String codeGroup;

    @Column(nullable = false, name = "code_group_name", length = 10)
    String codeGroupName;

    @Column(nullable = false, name = "description", length = 63)
    String description;

}
