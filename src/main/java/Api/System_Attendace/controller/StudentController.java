package Api.System_Attendace.controller;


import Api.System_Attendace.dto.Request.StudentRequest;
import Api.System_Attendace.dto.Response.StudentResponse;
import Api.System_Attendace.model.Teacher;
import Api.System_Attendace.repository.TeacherRepository;
import Api.System_Attendace.service.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final TeacherRepository teacherRepository;

    @PostMapping
    public ResponseEntity<StudentResponse> create(
            @RequestBody StudentRequest request
    ){
        return ResponseEntity.ok(
                studentService.create(request)
        );
    }


    @GetMapping
    public ResponseEntity<List<StudentResponse>> findAll(
            Authentication authentication
    ){

        String email = authentication.getName();


        Teacher teacher =
                teacherRepository
                        .findByUserEmail(email)
                        .orElseThrow();


        return ResponseEntity.ok(
                studentService.findAll(
                        teacher.getId()
                )
        );
    }
    @GetMapping("/profile")
    public ResponseEntity<StudentResponse> getMyProfile(
            Authentication authentication
    ){

        String email = authentication.getName();

        return ResponseEntity.ok(
                studentService.getProfileByEmail(email)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                studentService.findById(id)
        );
    }


    @GetMapping("/class/{classId}")
    public ResponseEntity<List<StudentResponse>> findStudentsByClass(
            @PathVariable Long classId
    ) {
        return ResponseEntity.ok(
                studentService.findStudentsByClass(classId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(
            @PathVariable Long id,
            @RequestBody StudentRequest request
    ){
        return ResponseEntity.ok(
                studentService.update(id, request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id
    ){
        studentService.delete(id);

        return ResponseEntity.ok(
                "Student deleted successfully"
        );
    }

}