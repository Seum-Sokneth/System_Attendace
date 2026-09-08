package Api.System_Attendace.service.Service;

import Api.System_Attendace.dto.Request.ClassEntityRequest;
import Api.System_Attendace.dto.Response.ClassEntityResponse;

import java.util.List;

public interface ClassEntityService {
    ClassEntityResponse create(ClassEntityRequest classEntityRequest);
    ClassEntityResponse findById(Long id);
    List<ClassEntityResponse> findAll(Long teacherId);
    ClassEntityResponse update(ClassEntityRequest classEntityRequest,Long id);
    void delete(Long id);
}
