package Api.System_Attendace.controller;

import Api.System_Attendace.dto.Request.TeacherRequest;
import Api.System_Attendace.dto.Response.TeacherResponse;
import Api.System_Attendace.service.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    @PostMapping
    public ResponseEntity<TeacherResponse> create(@Valid @RequestBody TeacherRequest request){
        return ResponseEntity.ok(
                teacherService.create(request)
        );
    }

    @GetMapping("/profile")
    public ResponseEntity<TeacherResponse> getProfile(
            @RequestParam String email) {

        TeacherResponse response = teacherService.getProfile(email);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> findAll(){return ResponseEntity.ok(
                teacherService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                teacherService.findById(id)
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> update(
            @Valid @RequestBody TeacherRequest request,
            @PathVariable Long id){
        return ResponseEntity.ok(
                teacherService.update(request,id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
