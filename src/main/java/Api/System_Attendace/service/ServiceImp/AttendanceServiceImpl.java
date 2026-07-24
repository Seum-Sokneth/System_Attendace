package Api.System_Attendace.service.ServiceImp;


import Api.System_Attendace.dto.Request.AttendanceRequest;
import Api.System_Attendace.dto.Response.AttendanceReportResponse;
import Api.System_Attendace.dto.Response.AttendanceResponse;
import Api.System_Attendace.exception.ExceptionNotFound;
import Api.System_Attendace.model.Attendance;
import Api.System_Attendace.model.AttendanceSession;
import Api.System_Attendace.model.Student;
import Api.System_Attendace.repository.AttendanceRepository;
import Api.System_Attendace.repository.AttendanceSessionRepository;
import Api.System_Attendace.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements Api.System_Attendace.service.Service.AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final AttendanceSessionRepository sessionRepository;
    private final StudentRepository studentRepository;
    @Override
    public AttendanceResponse create(AttendanceRequest request) {
        // Check request data before findById()
        if(request.getSessionId() == null){
            throw new RuntimeException("Session ID is required");
        }

        if(request.getStudentId() == null){
            throw new RuntimeException("Student ID is required");
        }
        AttendanceSession session = sessionRepository.findById(request.getSessionId())
                .orElseThrow(() ->
                        new ExceptionNotFound("Attendance Session not found"));
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() ->
                        new ExceptionNotFound("Student not found"));

        Attendance attendance = new Attendance();
        attendance.setSession(session);
        attendance.setStudent(student);
        attendance.setStatus(request.getStatus());
        attendance.setRemark(request.getRemark());
        Attendance save = attendanceRepository.save(attendance);

        return mapToResponse(save);
    }
    @Override
    public AttendanceResponse update(AttendanceRequest request, Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ExceptionNotFound("Attendance not found"));
        AttendanceSession session = sessionRepository.findById(request.getSessionId())
                .orElseThrow(() ->
                        new ExceptionNotFound("Session not found"));
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() ->
                        new ExceptionNotFound("Student not found"));

        attendance.setSession(session);
        attendance.setStudent(student);
        attendance.setStatus(request.getStatus());
        attendance.setRemark(request.getRemark());
        Attendance update = attendanceRepository.save(attendance);
        return mapToResponse(update);
    }

    @Override
    public AttendanceResponse getById(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ExceptionNotFound("Attendance not found"));
        return mapToResponse(attendance);
    }

    @Override
    public List<AttendanceResponse> getAll() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    @Override
    public List<AttendanceResponse> getByStudent(Long studentId) {
        List<Attendance> attendances =
                attendanceRepository.findByStudentId(studentId);

        return attendances.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AttendanceReportResponse> getAttendanceReport(
            Long classId,
            String fromDate,
            String toDate) {

        LocalDate start = LocalDate.parse(fromDate);
        LocalDate end = LocalDate.parse(toDate);

        return attendanceRepository.getAttendanceReport(
                classId,
                start,
                end
        );
    }

    @Override
    public void delete(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ExceptionNotFound("Attendance not found"));
        attendanceRepository.delete(attendance);

    }
    private AttendanceResponse mapToResponse(Attendance attendance){
        AttendanceResponse response = new AttendanceResponse();
        response.setId(attendance.getId());
        response.setSessionId(attendance.getSession().getId());
        response.setStudentId(attendance.getStudent().getId());
        response.setStudentName(attendance.getStudent().getName());
        response.setStatus(attendance.getStatus());
        response.setRemark(attendance.getRemark());
        response.setCreatedAt(attendance.getCreatedAt());
        response.setUpdatedAt(attendance.getUpdatedAt()
        );
        return response;

    }

}