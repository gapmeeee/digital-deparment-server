package digital.department.moduls;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private Course course;

    @JsonIgnore
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> files;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }



}