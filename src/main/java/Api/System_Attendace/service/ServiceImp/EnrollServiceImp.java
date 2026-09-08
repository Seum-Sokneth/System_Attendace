package Api.System_Attendace.service.ServiceImp;


import Api.System_Attendace.dto.Request.EnrollRequest;
import Api.System_Attendace.dto.Response.EnrollResponse;
import Api.System_Attendace.exception.ExceptionNotFound;
import Api.System_Attendace.model.ClassEntity;
import Api.System_Attendace.model.Enrollment;
import Api.System_Attendace.model.Student;
import Api.System_Attendace.repository.ClassEntityRepository;
import Api.System_Attendace.repository.EnrollRepository;
import Api.System_Attendace.repository.StudentRepository;
import Api.System_Attendace.service.Service.EnrollService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@AllArgsConstructor
public class EnrollServiceImp implements EnrollService {
    private final EnrollRepository enrollRepository;
    private final StudentRepository studentRepository;
    private final ClassEntityRepository classEntityRepository;

    @Override
    public EnrollResponse create(EnrollRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(
                        () -> new ExceptionNotFound("Student not found")
                );
        ClassEntity classEntity = classEntityRepository.findById(request.getClassId())
                .orElseThrow(
                        () -> new ExceptionNotFound("Class not found")
                );

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setClassEntity(classEntity);
        Enrollment save = enrollRepository.save(enrollment);
        return mapToResponse(save);
    }

    @Override
    public List<EnrollResponse> findAll() {
        return enrollRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public EnrollResponse findById(Long id) {
        Enrollment enrollment = enrollRepository.findById(id)
                .orElseThrow(
                        () -> new ExceptionNotFound("Enrollment not found")
                );
        return mapToResponse(enrollment);
    }

    @Override
    public EnrollResponse update(EnrollRequest request, Long id) {
        Enrollment enrollment = enrollRepository.findById(id)
                .orElseThrow(
                        () -> new ExceptionNotFound("Enrollment not found")
                );
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(
                        () -> new ExceptionNotFound("Student not found")
                );
        ClassEntity classEntity = classEntityRepository.findById(request.getClassId())
                .orElseThrow(
                        () -> new ExceptionNotFound("Class not found")
                );
        enrollment.setStudent(student);
        enrollment.setClassEntity(classEntity);
        Enrollment update = enrollRepository.save(enrollment);
        return mapToResponse(update);
    }

    @Override
    public void delete(Long id) {
        Enrollment enrollment = enrollRepository.findById(id)
                .orElseThrow(
                        () -> new ExceptionNotFound("Enrollment not found")
                );
        enrollRepository.delete(enrollment);
    }
    private EnrollResponse mapToResponse(Enrollment enrollment){
        EnrollResponse response = new EnrollResponse();
        response.setId(enrollment.getId());
        response.setClassId(enrollment.getClassEntity().getId());
        response.setClassTitle(enrollment.getClassEntity().getClassTitle());
        response.setStudentId(enrollment.getStudent().getId());
        response.setGender(enrollment.getStudent().getGender());
        response.setStudentName(enrollment.getStudent().getName());
        response.setCreatedAt(enrollment.getCreatedAt());
        response.setUpdatedAt(enrollment.getUpdatedAt());
        return response;
    }
}