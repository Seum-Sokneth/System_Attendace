package Api.System_Attendace.service.Service;

import Api.System_Attendace.dto.Request.AttendanceRequest;
import Api.System_Attendace.dto.Response.AttendanceReportResponse;
import Api.System_Attendace.dto.Response.AttendanceResponse;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    AttendanceResponse create(AttendanceRequest request);

    AttendanceResponse update(AttendanceRequest request, Long id);

    AttendanceResponse getById(Long id);

    List<AttendanceResponse> getAllByTeacher(Long teacherId);
    List<AttendanceResponse> getByStudent(Long studentId);
    List<AttendanceReportResponse> getAttendanceReport(
            Long classId,
            Long teacherId,
            LocalDate fromDate,
            LocalDate toDate
    );
    void delete(Long id);
}
