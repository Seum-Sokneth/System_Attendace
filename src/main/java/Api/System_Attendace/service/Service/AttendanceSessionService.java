package Api.System_Attendace.service.Service;


import Api.System_Attendace.dto.Request.AttendanceSessionRequest;
import Api.System_Attendace.dto.Response.AttendanceSessionResponse;

import java.util.List;

public interface AttendanceSessionService {

    AttendanceSessionResponse create(AttendanceSessionRequest request);
    AttendanceSessionResponse update(AttendanceSessionRequest request, Long id);
    AttendanceSessionResponse getById(Long id);
    List<AttendanceSessionResponse> getAll();
    void delete(Long id);
}
