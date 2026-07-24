package Api.System_Attendace.service.Service;

import Api.System_Attendace.dto.Request.TeacherRequest;
import Api.System_Attendace.dto.Response.TeacherResponse;
import java.util.List;

public interface TeacherService {
    TeacherResponse create(TeacherRequest teacherRequest);
    List<TeacherResponse> findAll();
    TeacherResponse findById(Long id);
    TeacherResponse update(TeacherRequest teacherRequest ,Long id);
    void delete(Long id);

    TeacherResponse getProfile(String email);

}
