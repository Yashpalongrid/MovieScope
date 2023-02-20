package com.onboarding.moviescope.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User extends DataAudit {

    //==fields==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true)
    @Size(max = 20)
    private String username;

    @NotBlank
    @Column(unique = true)
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 200)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "user")
    private Set<Watchlist> watchlist;


    //    @Autowired
//    private Movie movie;
//    @ElementCollection(targetClass = Movie.class)
//    @JoinTable()
//    private Set<Long> watchlist;

    //watchlist element collection?

}
