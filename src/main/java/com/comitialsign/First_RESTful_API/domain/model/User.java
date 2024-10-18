package com.comitialsign.First_RESTful_API.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 3, message = "Name must be 3 characters.")
    @NotBlank(message = "name can't be blank")
    @Column(nullable = false)
    private String name;

    @Email(message = "Email invalid.")
    @NotBlank(message = "Email can't be blank.")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Min(value = 18, message = "Age should not be less than 18.")
    @Max(value = 100, message = "Age should not be greater than 100.")
    private int age;


}
