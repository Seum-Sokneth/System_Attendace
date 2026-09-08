package Api.System_Attendace.service.Service;

import Api.System_Attendace.dto.Request.StudentRequest;
import Api.System_Attendace.dto.Response.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse create(StudentRequest request);

    StudentResponse update(Long id, StudentRequest request);

    StudentResponse findById(Long id);

    List<StudentResponse> findAll(Long teacherId);

    void delete(Long id);
    StudentResponse getProfileByEmail(String email);
    List<StudentResponse> findStudentsByClass(Long classId);

}