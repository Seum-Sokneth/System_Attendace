package Api.System_Attendace.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "classes")
public class ClassEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();
    // One class has many students

    // One class has one teacher
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "class_code", nullable = false, unique = true)
    private String classCode;

    @Column(name = "class_title", nullable = false)
    private String classTitle;

    @Column(nullable = false)
    private String schedule;

    @Column(name = "class_room", nullable = false)
    private String classRoom;

    @Column(length = 500)
    private String description;

}