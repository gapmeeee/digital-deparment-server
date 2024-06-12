package com.example.demo.moduls;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "lectures")
@Data
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numberOfLectures")
    private int numberOfLectures;

    @Column(name = "name")
    private String name;

    @Column(name = "comment")
    private String comment;

    private LocalDateTime dateOfCreated;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private Course course;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

}