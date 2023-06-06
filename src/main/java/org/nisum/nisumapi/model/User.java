package org.nisum.nisumapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor 
@Table(name = "users")
public class User implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;

    @NonNull
    private String name;

    @Email(message = "El formato del email es incorrecto")
    @Size(min = 5, max = 100)
    @Column(length = 100)
    @NonNull
    private String email;

    @JsonIgnore
    @NonNull
    @Column(name = "password_hash")
    private String passwordHash;

    @NonNull
    private String token;

    @NonNull
    private LocalDateTime created=LocalDateTime.now();

    @NonNull
    private LocalDateTime modified=LocalDateTime.now();

    @NonNull
    @Column(name = "last_login")
    private LocalDateTime lastLogin = LocalDateTime.now();

    @Column(nullable = false)
    private boolean active = false;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="idPhone")
    private List<Phone> phones;

  
}
