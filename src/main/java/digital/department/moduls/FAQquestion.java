package digital.department.moduls;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="FAQquestion")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FAQquestion {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="question")
    private String questions;

    @Column(name="answer", length = 1000)
    private String answers;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "faq_id")
    private FAQ faq;

}
