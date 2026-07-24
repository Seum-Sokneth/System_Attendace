package Api.System_Attendace.service.Service;

import Api.System_Attendace.dto.Request.AttendanceRequest;
import Api.System_Attendace.dto.Response.AttendanceReportResponse;
import Api.System_Attendace.dto.Response.AttendanceResponse;

import java.util.List;

public interface AttendanceService {
    AttendanceResponse create(AttendanceRequest request);

    AttendanceResponse update(AttendanceRequest request, Long id);

    AttendanceResponse getById(Long id);

    List<AttendanceResponse> getAll();
    List<AttendanceResponse> getByStudent(Long studentId);
    List<AttendanceReportResponse> getAttendanceReport(
            Long classId,
            String fromDate,
            String toDate
    );
    void delete(Long id);
}
