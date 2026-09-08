package Api.System_Attendace.repository;

import Api.System_Attendace.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface StudentRepository extends JpaRepository<Student,Long> {


    @Query("""
    SELECT e.student
    FROM Enrollment e
    WHERE e.classEntity.teacher.id = :teacherId
    """)
    List<Student> findStudentsByTeacherId(
            @Param("teacherId") Long teacherId
    );


    @Query("""
    SELECT e.student
    FROM Enrollment e
    WHERE e.classEntity.id = :classId
    """)
    List<Student> findStudentsByClassId(
            @Param("classId") Long classId
    );


    Optional<Student> findByUserEmail(String email);

}