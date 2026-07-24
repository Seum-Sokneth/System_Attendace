package Api.System_Attendace.repository;

import Api.System_Attendace.model.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Long> {

    void deleteByClassEntity_Id(Long classId);

}
