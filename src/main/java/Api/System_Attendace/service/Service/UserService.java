package Api.System_Attendace.service.Service;

import Api.System_Attendace.dto.Request.UserRequest;
import Api.System_Attendace.dto.Response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest request);

    List<UserResponse> findAll();

    UserResponse findById(Long id);

    UserResponse update(Long id, UserRequest request);

    void delete(Long id);
}