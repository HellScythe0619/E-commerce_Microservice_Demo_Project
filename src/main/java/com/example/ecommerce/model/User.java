package com.example.ecommerce.model;

import javax.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
} 