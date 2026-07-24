package Api.System_Attendace.service.ServiceImp;

import Api.System_Attendace.dto.Request.UserRequest;
import Api.System_Attendace.dto.Response.UserResponse;
import Api.System_Attendace.exception.ExceptionNotFound;
import Api.System_Attendace.model.User;
import Api.System_Attendace.repository.UserRepository;
import Api.System_Attendace.service.Service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserResponse create(UserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        User saveUser = userRepository.save(user);
        return mapToResponse(saveUser);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new ExceptionNotFound("User not found")
                );

        return mapToResponse(user);
    }
    @Override
    public UserResponse update(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new ExceptionNotFound("User not found")
                );
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        User updateUser = userRepository.save(user);

        return mapToResponse(updateUser);
    }
    @Override
    public void delete(Long id) {

        if(!userRepository.existsById(id)){
            throw new ExceptionNotFound("User not found");
        }
        userRepository.deleteById(id);
    }

    private UserResponse mapToResponse(User user){

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

}