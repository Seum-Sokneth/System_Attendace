package Api.System_Attendace.repository;

import Api.System_Attendace.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepository extends JpaRepository<Enrollment,Long> {

    void deleteByStudentId(Long studentId);
    long countByClassEntityId(Long classId);
    boolean existsByClassEntityId(Long id);
    long countByClassEntityTeacherId(Long teacherId);
}
