package digital.department.moduls;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="course")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Course {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="description", columnDefinition = "text")
    private String description;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
//            mappedBy = "course")
//    private List<Image> images = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "course")
    private List<User> users = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "course")
    private List<Lecture> lectures = new ArrayList<>();

    private Long previewImageId;
    private LocalDateTime dateOfCreated;

    public void addUsersInList(User user){
        users.add(user);
    }
    public void addLecturesInList(Lecture lecture){
        lectures.add(lecture);
    }

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

//    public void addImageToProduct(Image image){
//        image.setProduct(this);
//        images.add(image);
//    }

}
