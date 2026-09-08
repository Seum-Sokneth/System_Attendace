package Api.System_Attendace.repository;

import Api.System_Attendace.dto.Response.AttendanceReportResponse;
import Api.System_Attendace.enums.AttendanceStatus;
import Api.System_Attendace.model.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {


    void deleteByStudentId(Long id);
    List<Attendance> findBySessionClassEntityTeacherId(Long teacherId);

    List<Attendance> findByStudentId(Long studentId);
    List<Attendance> findByStudentUserId(Long userId);
    @Query("""
SELECT new Api.System_Attendace.dto.Response.AttendanceReportResponse(
    a.session.sessionDate,
    SUM(CASE WHEN a.status='PRESENT' THEN 1 ELSE 0 END),
    SUM(CASE WHEN a.status='ABSENT' THEN 1 ELSE 0 END),
    SUM(CASE WHEN a.status='LATE' THEN 1 ELSE 0 END),
    SUM(CASE WHEN a.status='EXCUSE' THEN 1 ELSE 0 END),
    COUNT(a.id),
    (SUM(CASE WHEN a.status='PRESENT' THEN 1 ELSE 0 END)*100.0/COUNT(a.id))
)
FROM Attendance a
WHERE a.session.classEntity.id=:classId
AND a.session.classEntity.teacher.id=:teacherId
AND a.session.sessionDate BETWEEN :fromDate AND :toDate
GROUP BY a.session.sessionDate
ORDER BY a.session.sessionDate
""")
    List<AttendanceReportResponse> getAttendanceReport(
            @Param("classId") Long classId,
            @Param("teacherId") Long teacherId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate
    );


    @Query("""
        SELECT a
        FROM Attendance a
        WHERE a.session.classEntity.id = :classId
          AND a.session.sessionDate BETWEEN :fromDate AND :toDate
        ORDER BY a.session.sessionDate
    """)
    List<Attendance> findAttendanceHistory(
            @Param("classId") Long classId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate
    );
    long countBySessionClassEntityTeacherId(Long teacherId);
    long countBySessionClassEntityTeacherIdAndStatus(Long teacherId,  AttendanceStatus status);

}