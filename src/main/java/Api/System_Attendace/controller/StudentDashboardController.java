package Api.System_Attendace.controller;

import Api.System_Attendace.dto.Response.StudentResponse;
import Api.System_Attendace.model.Student;
import Api.System_Attendace.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentDashboardController {


    private final StudentRepository studentRepository;


    @GetMapping("/dashboard")
    public StudentResponse dashboard(Authentication authentication){
        String email = authentication.getName();
        Student student =
                studentRepository
                        .findByUserEmail(email)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Student not found")
                        );
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .studentCode(student.getStudentCode())
                .phoneNumber(student.getPhoneNumber())
                .createdAt(student.getCreatedAt())
                .dateOfBirth(student.getDateOfBirth())
                .build();

    }

}