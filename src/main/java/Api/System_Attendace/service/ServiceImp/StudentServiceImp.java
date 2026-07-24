package Api.System_Attendace.service.ServiceImp;

import Api.System_Attendace.dto.Request.StudentRequest;
import Api.System_Attendace.dto.Response.StudentResponse;
import Api.System_Attendace.enums.Role;
import Api.System_Attendace.model.Student;
import Api.System_Attendace.model.User;
import Api.System_Attendace.repository.AttendanceRepository;
import Api.System_Attendace.repository.EnrollRepository;
import Api.System_Attendace.repository.StudentRepository;
import Api.System_Attendace.repository.UserRepository;
import Api.System_Attendace.service.Service.StudentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StudentServiceImp implements StudentService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AttendanceRepository attendanceRepository;
    private final EnrollRepository enrollRepository;


    @Override
    public StudentResponse create(StudentRequest request) {

        // 1. Create User Account
        User user = new User();

        user.setEmail(request.getEmail());

        // default password for student
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(Role.STUDENT);


        User savedUser = userRepository.save(user);



        // 2. Create Student
        Student student = new Student();

        student.setName(request.getName());

        student.setStudentCode(request.getStudentCode());

        student.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        student.setPhoneNumber(request.getPhoneNumber());

        student.setDateOfBirth(request.getDateOfBirth());


        // Connect User -> Student
        student.setUser(savedUser);


        Student savedStudent = studentRepository.save(student);


        return mapToResponse(savedStudent);
    }
    @Override
    public StudentResponse getProfileByEmail(String email) {

        Student student = studentRepository
                .findByUserEmail(email)
                .orElseThrow(
                        () -> new RuntimeException("Student not found")
                );


        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .studentCode(student.getStudentCode())
                .phoneNumber(student.getPhoneNumber())
                .build();
    }

    public List<StudentResponse> findStudentsByClass(Long classId){
        List<Student> students = studentRepository.findStudentsByClassId(classId);

        System.out.println("========== DEBUG ==========");
        System.out.println("Class ID: " + classId);
        System.out.println("Students found: " + students.size());

        for (Student s : students) {
            System.out.println(s.getId() + " - " + s.getName());
        }

        return students.stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public StudentResponse findById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found")
                );

        return mapToResponse(student);
    }

    @Override
    public List<StudentResponse> findAll() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    @Override
    public StudentResponse update(Long id, StudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found")
                );


        student.setName(request.getName());
        student.setStudentCode(request.getStudentCode());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setDateOfBirth(request.getDateOfBirth());


        Student updatedStudent = studentRepository.save(student);


        return mapToResponse(updatedStudent);
    }




    @Override
    @Transactional
    public void delete(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found")
                );

        // Delete attendance first
        attendanceRepository.deleteByStudentId(id);

        // Delete enrollment first

        enrollRepository.deleteByStudentId(id);

        // Delete student
        studentRepository.delete(student);
    }




    private StudentResponse mapToResponse(Student student) {

        StudentResponse response = new StudentResponse();


        response.setId(student.getId());


        if(student.getUser() != null){
            response.setUserId(student.getUser().getId());
        }


        response.setName(student.getName());
        response.setStudentCode(student.getStudentCode());
        response.setEmail(student.getEmail());
        response.setPhoneNumber(student.getPhoneNumber());
        response.setDateOfBirth(student.getDateOfBirth());

        response.setCreatedAt(student.getCreatedAt());
        response.setUpdatedAt(student.getUpdatedAt());


        return response;
    }


}