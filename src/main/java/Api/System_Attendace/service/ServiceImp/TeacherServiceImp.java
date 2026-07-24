package Api.System_Attendace.service.ServiceImp;

import Api.System_Attendace.dto.Request.TeacherRequest;
import Api.System_Attendace.dto.Response.TeacherResponse;
import Api.System_Attendace.exception.ExceptionNotFound;
import Api.System_Attendace.model.Teacher;
import Api.System_Attendace.model.User;
import Api.System_Attendace.repository.TeacherRepository;
import Api.System_Attendace.repository.UserRepository;
import Api.System_Attendace.service.Service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TeacherServiceImp implements TeacherService {
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    // CREATE
    @Override
    public TeacherResponse create(TeacherRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ExceptionNotFound(
                                "User Not Found ID: " + request.getUserId()
                        )
                );

        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacher.setName(request.getName());
        teacher.setTeacherCode(request.getTeacherCode());
        teacher.setDepartment(request.getDepartment());
        teacher.setPhone(request.getPhone());

        Teacher savedTeacher = teacherRepository.save(teacher);

        return mapToResponse(savedTeacher);
    }
    @Override
    public TeacherResponse getProfile(String email) {

        System.out.println("Email = " + email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("User ID = " + user.getId());
        System.out.println("User Email = " + user.getEmail());

        Teacher teacher = teacherRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        System.out.println("Teacher ID = " + teacher.getId());
        System.out.println("Teacher Name = " + teacher.getName());

        return mapToResponse(teacher);
    }
    // FIND ALL
    @Override
    public List<TeacherResponse> findAll() {
        return teacherRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // FIND BY ID
    @Override
    public TeacherResponse findById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new ExceptionNotFound(
                                "Teacher Not Found ID: " + id
                        )
                );

        return mapToResponse(teacher);
    }
    // UPDATE
    @Override
    public TeacherResponse update(TeacherRequest request, Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new ExceptionNotFound("Teacher Not Found ID: " + id)
                );

        teacher.setName(request.getName());
        teacher.setTeacherCode(request.getTeacherCode());
        teacher.setDepartment(request.getDepartment());
        teacher.setPhone(request.getPhone());
        // Update User if userId is provided
        if(request.getUserId() != null){
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() ->
                            new ExceptionNotFound("User Not Found ID: " + request.getUserId())
                    );  teacher.setUser(user);
        }
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return mapToResponse(updatedTeacher);
    }
    // DELETE
    @Override
    public void delete(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new ExceptionNotFound("Teacher Not Found ID: " + id)
                );
        teacherRepository.delete(teacher);
    }



    private TeacherResponse mapToResponse(Teacher teacher) {

        TeacherResponse response = new TeacherResponse();

        response.setId(teacher.getId());
        response.setName(teacher.getName());
        response.setTeacherCode(teacher.getTeacherCode());
        response.setDepartment(teacher.getDepartment());
        response.setPhone(teacher.getPhone());

        if (teacher.getUser() != null) {

            response.setUserId(
                    teacher.getUser().getId()
            );

            response.setEmail(
                    teacher.getUser().getEmail()
            );
        }

        response.setCreatedAt(
                teacher.getCreatedAt()
        );

        response.setUpdatedAt(
                teacher.getUpdatedAt()
        );

        return response;
    }


}