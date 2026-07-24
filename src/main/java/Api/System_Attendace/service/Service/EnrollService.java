package Api.System_Attendace.service.Service;

import Api.System_Attendace.dto.Request.EnrollRequest;
import Api.System_Attendace.dto.Response.EnrollResponse;

import java.util.List;

public interface EnrollService {
    EnrollResponse create(EnrollRequest enrollRequest);
    List<EnrollResponse> findAll();
    EnrollResponse  findById(Long id);
    EnrollResponse update(EnrollRequest enrollRequest ,Long id);
    void delete(Long id);
}

