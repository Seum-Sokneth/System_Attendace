package Api.System_Attendace.service.ServiceImp;


import Api.System_Attendace.dto.Request.AttendanceSessionRequest;
import Api.System_Attendace.dto.Response.AttendanceSessionResponse;
import Api.System_Attendace.exception.ExceptionNotFound;
import Api.System_Attendace.model.AttendanceSession;
import Api.System_Attendace.model.ClassEntity;
import Api.System_Attendace.repository.AttendanceSessionRepository;
import Api.System_Attendace.repository.ClassEntityRepository;
import Api.System_Attendace.service.Service.AttendanceSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AttendanceSessionServiceImpl implements AttendanceSessionService {
    private final AttendanceSessionRepository attendanceSessionRepository;
    private final ClassEntityRepository classEntityRepository;
    @Override
    public AttendanceSessionResponse create(AttendanceSessionRequest request) {
        ClassEntity classEntity =
                classEntityRepository.findById(request.getClassId())
                        .orElseThrow(() ->
                                new ExceptionNotFound(
                                        "Class not found"
                                ));

        AttendanceSession session = new AttendanceSession();
        session.setClassEntity(classEntity);
        session.setSessionDate(request.getSessionDate());
        session.setStartAt(request.getStartAt());
        AttendanceSession save = attendanceSessionRepository.save(session);
        return mapToResponse(save);

    }

    @Override
    public AttendanceSessionResponse update(AttendanceSessionRequest request, Long id) {
        AttendanceSession session = attendanceSessionRepository.findById(id)
                        .orElseThrow(() ->
                                new ExceptionNotFound("Attendance Session not found"));
        ClassEntity classEntity = classEntityRepository.findById(request.getClassId())
                        .orElseThrow(() ->
                                new ExceptionNotFound("Class not found"));
        session.setClassEntity(classEntity);
        session.setSessionDate(request.getSessionDate());
        session.setStartAt(request.getStartAt());
        AttendanceSession update = attendanceSessionRepository.save(session);
        return mapToResponse(update);
    }

    @Override
    public AttendanceSessionResponse getById(Long id) {
        AttendanceSession session = attendanceSessionRepository.findById(id)
                        .orElseThrow(() ->
                                new ExceptionNotFound(
                                        "Attendance Session not found"
                                ));
        return mapToResponse(session);
    }

    @Override
    public List<AttendanceSessionResponse> getAll() {
        return attendanceSessionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        AttendanceSession session =
                attendanceSessionRepository.findById(id)
                        .orElseThrow(() ->
                                new ExceptionNotFound("Attendance Session not found"));
        attendanceSessionRepository.delete(session);

    }


    private AttendanceSessionResponse mapToResponse(AttendanceSession session){
        AttendanceSessionResponse response = new AttendanceSessionResponse();

        response.setId(session.getId());
        response.setClassId(session.getClassEntity().getId());
        response.setClassName(session.getClassEntity().getClassTitle());
        response.setSessionDate(session.getSessionDate());
        response.setStartAt(session.getStartAt());
        response.setCreatedAt(session.getCreatedAt());
        response.setUpdatedAt(session.getUpdatedAt());
        return response;

    }


}