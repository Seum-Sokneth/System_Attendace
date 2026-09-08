package Api.System_Attendace.service.ServiceImp;

import Api.System_Attendace.dto.Request.ClassEntityRequest;
import Api.System_Attendace.dto.Response.ClassEntityResponse;
import Api.System_Attendace.exception.ExceptionNotFound;
import Api.System_Attendace.model.ClassEntity;
import Api.System_Attendace.model.Teacher;
import Api.System_Attendace.repository.ClassEntityRepository;
import Api.System_Attendace.repository.EnrollRepository;
import Api.System_Attendace.repository.TeacherRepository;
import Api.System_Attendace.service.Service.ClassEntityService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassEntityServiceImp implements ClassEntityService {
    private final ClassEntityRepository classEntityRepository;
    private final TeacherRepository teacherRepository;
    private final EnrollRepository enrollRepository;

    @Override
    public ClassEntityResponse create(ClassEntityRequest request) {
        ClassEntity classEntity = new ClassEntity();
        mapRequestToEntity(request, classEntity);
        ClassEntity savedClass = classEntityRepository.save(classEntity);
        return mapToResponse(savedClass);
    }

    @Override
    public ClassEntityResponse findById(Long id) {
        ClassEntity classEntity = classEntityRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Class Not Found"));
        return mapToResponse(classEntity);
    }
    @Override
    public List<ClassEntityResponse> findAll(Long teacherId) {

        return classEntityRepository
                .findByTeacherId(teacherId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public ClassEntityResponse update(ClassEntityRequest request, Long id) {
        ClassEntity classEntity = classEntityRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Class Not Found"));

        mapRequestToEntity(request, classEntity);
        ClassEntity updatedClass = classEntityRepository.save(classEntity);
        return mapToResponse(updatedClass);
    }

    @Override
    public void delete(Long id) {
        ClassEntity classEntity = classEntityRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Class Not Found"));
        if (enrollRepository.existsByClassEntityId(id)) {
            throw new RuntimeException("Cannot delete class because students are enrolled.");
        }

        classEntityRepository.delete(classEntity);
    }
    /**
     * Map Request DTO to Entity
     */
    private void mapRequestToEntity(ClassEntityRequest request, ClassEntity entity) {
        entity.setClassCode(request.getClassCode());
        entity.setClassTitle(request.getClassTitle());
        entity.setSchedule(request.getSchedule());
        entity.setClassRoom(request.getClassRoom());
        entity.setDescription(request.getDescription());
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ExceptionNotFound("Teacher Not Found"));

        entity.setTeacher(teacher);
    }

    /**
     * Map Entity to Response DTO
     */
    private ClassEntityResponse mapToResponse(ClassEntity classEntity) {
        ClassEntityResponse response = new ClassEntityResponse();
        response.setId(classEntity.getId());
        response.setClassCode(classEntity.getClassCode());
        response.setClassRoom(classEntity.getClassRoom());
        response.setClassTitle(classEntity.getClassTitle());
        response.setSchedule(classEntity.getSchedule());
        response.setDescription(classEntity.getDescription());
        if (classEntity.getTeacher() != null) {
            response.setTeacherId(classEntity.getTeacher().getId());
            response.setTeacherName(classEntity.getTeacher().getName());
        }
        // ⭐ បន្ថែមបន្ទាត់នេះ
        response.setStudentCount(
                (int) enrollRepository.countByClassEntityId(classEntity.getId())
        );

        return response;
    }
}