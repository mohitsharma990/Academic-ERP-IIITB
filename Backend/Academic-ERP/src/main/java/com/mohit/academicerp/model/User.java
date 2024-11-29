package com.mohit.academicerp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "erp_admin")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    @Column(columnDefinition = "varchar(255) default 'ROLE_ERP_ADMIN'")
    @Generated(GenerationTime.INSERT)
    private String role;

}