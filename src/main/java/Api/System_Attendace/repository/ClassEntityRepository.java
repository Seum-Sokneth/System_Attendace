package Api.System_Attendace.repository;

import Api.System_Attendace.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassEntityRepository extends JpaRepository<ClassEntity,Long> {
    long countByTeacherId(Long teacherId);


}
