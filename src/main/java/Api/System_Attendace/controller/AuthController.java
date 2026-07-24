package Api.System_Attendace.controller;

import Api.System_Attendace.dto.Request.LoginRequest;
import Api.System_Attendace.dto.Request.RegisterRequest;
import Api.System_Attendace.dto.Response.LoginResponse;
import Api.System_Attendace.enums.Role;
import Api.System_Attendace.model.Teacher;
import Api.System_Attendace.model.User;
import Api.System_Attendace.repository.TeacherRepository;
import Api.System_Attendace.repository.UserRepository;
import Api.System_Attendace.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {


    private final UserRepository userRepository;

    private final TeacherRepository teacherRepository;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;



    // LOGIN FOR TEACHER AND STUDENT
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {


        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);


        if(user == null){

            return ResponseEntity
                    .badRequest()
                    .body("Email not found");
        }


        // check encrypted password
        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )){

            return ResponseEntity
                    .badRequest()
                    .body("Password incorrect");
        }



        String token =
                jwtUtil.generateToken(
                        user.getEmail(),
                        user.getRole().name()
                );


        LoginResponse response =
                new LoginResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getRole().name(),
                        token
                );


        return ResponseEntity.ok(response);

    }




    // ONLY TEACHER CAN REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ){


        if(userRepository.existsByEmail(
                request.getEmail()
        )){

            return ResponseEntity
                    .badRequest()
                    .body("Email already exists");
        }



        // only create teacher account
        User user = new User();


        user.setEmail(
                request.getEmail()
        );


        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );


        user.setRole(Role.TEACHER);



        User savedUser =
                userRepository.save(user);



        // Create Teacher Profile

        Teacher teacher = new Teacher();


        teacher.setUser(savedUser);


        teacher.setName(
                request.getName()
        );


        teacher.setTeacherCode(
                "TC-" + savedUser.getId()
        );


        teacher.setDepartment("");

        teacher.setPhone("");



        teacherRepository.save(teacher);



        String token =
                jwtUtil.generateToken(
                        savedUser.getEmail(),
                        savedUser.getRole().name()
                );



        LoginResponse response =
                new LoginResponse(
                        savedUser.getId(),
                        savedUser.getEmail(),
                        savedUser.getRole().name(),
                        token
                );



        return ResponseEntity.ok(response);

    }

}