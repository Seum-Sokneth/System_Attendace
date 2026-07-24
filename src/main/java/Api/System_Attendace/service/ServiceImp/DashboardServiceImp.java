package Api.System_Attendace.service.ServiceImp;

import Api.System_Attendace.dto.Response.TeacherDashboardResponse;
import Api.System_Attendace.enums.AttendanceStatus;
import Api.System_Attendace.repository.AttendanceRepository;
import Api.System_Attendace.repository.ClassEntityRepository;
import Api.System_Attendace.repository.EnrollRepository;
import Api.System_Attendace.service.Service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DashboardServiceImp implements DashboardService {

    private final ClassEntityRepository classRepository;
    private final EnrollRepository enrollRepository;
    private final AttendanceRepository attendanceRepository;
    @Override
    public TeacherDashboardResponse getDashboard(Long teacherId) {

        TeacherDashboardResponse response = new TeacherDashboardResponse();

        long totalAttendance =
                attendanceRepository.countBySessionClassEntityTeacherId(teacherId);

        long totalPresent =
                attendanceRepository.countBySessionClassEntityTeacherIdAndStatus(
                        teacherId, AttendanceStatus.PRESENT);

        long totalAbsent =
                attendanceRepository.countBySessionClassEntityTeacherIdAndStatus(
                        teacherId, AttendanceStatus.ABSENT);

        response.setTotalClasses(
                classRepository.countByTeacherId(teacherId)
        );

        response.setTotalStudents(
                enrollRepository.countByClassEntityTeacherId(teacherId)
        );

        response.setTotalAttendance(totalAttendance);
        response.setTotalPresent(totalPresent);
        response.setTotalAbsent(totalAbsent);

        if (totalAttendance > 0) {
            response.setPresentRate((double) totalPresent * 100 / totalAttendance);
            response.setAbsentRate((double) totalAbsent * 100 / totalAttendance);
        } else {
            response.setPresentRate(0);
            response.setAbsentRate(0);
        }

        return response;
    }
}