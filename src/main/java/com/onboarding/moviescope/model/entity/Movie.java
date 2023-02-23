package com.onboarding.moviescope.model.entity;

import com.onboarding.moviescope.model.constant.Genre;
import com.onboarding.moviescope.model.constant.Language;
import com.onboarding.moviescope.model.constant.StreamingPlatform;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Movie")
public class Movie extends DataAudit {

    //==fields==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 300)
    private String storyline;

    @Digits(integer = 10,fraction = 1)
    private float rating;

    @Digits(integer = 1000,fraction = 0)
    private int releaseYear;

    @NotBlank
    @Column(unique = true)
    private String imgSource;

    @ElementCollection(targetClass = Genre.class)
    @JoinTable(name = "Movie_Genre" , joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres;

    @ElementCollection
    @JoinTable(name="Movie_Cast", joinColumns = @JoinColumn(name = "id"))
    private Set<String> cast;

    @Enumerated(EnumType.STRING)
    private Language language;

    @ElementCollection(targetClass = StreamingPlatform.class)
    @JoinTable(name = "Movie_Streaming",joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<StreamingPlatform> streamingPlatforms;

    @OneToMany(mappedBy = "movie")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "movie")
    private Set<Watchlist> watchlist;


   //cast element collection, genre language streaming platform

}
