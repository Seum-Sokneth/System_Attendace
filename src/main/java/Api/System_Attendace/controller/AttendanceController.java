package Api.System_Attendace.controller;


import Api.System_Attendace.dto.Request.AttendanceRequest;
import Api.System_Attendace.dto.Response.AttendanceReportResponse;
import Api.System_Attendace.dto.Response.AttendanceResponse;

import Api.System_Attendace.model.Teacher;
import Api.System_Attendace.repository.TeacherRepository;
import Api.System_Attendace.service.Service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final TeacherRepository teacherRepository;
    @PostMapping
    public ResponseEntity<AttendanceResponse> create(@RequestBody AttendanceRequest request){
        return new ResponseEntity<>(
                attendanceService.create(request),
                HttpStatus.CREATED
        );
    }
    @Operation(summary = "Hello " , description = "Find student id")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponse>> getByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                attendanceService.getByStudent(studentId)
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponse> update(@RequestBody AttendanceRequest request, @PathVariable Long id){
        return ResponseEntity.ok(
                attendanceService.update(request,id)
        );

    }
    @GetMapping("/report")
    public ResponseEntity<List<AttendanceReportResponse>> getAttendanceReport(
            @RequestParam Long classId,
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate,
            Authentication authentication
    ) {

        String email = authentication.getName();

        Teacher teacher = teacherRepository
                .findByUserEmail(email)
                .orElseThrow();

        return ResponseEntity.ok(
                attendanceService.getAttendanceReport(
                        classId,
                        teacher.getId(),
                        fromDate,
                        toDate
                )
        );
    }
    @GetMapping("/history")
    public ResponseEntity<List<AttendanceReportResponse>> history(
            @RequestParam Long classId,
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate,
            Authentication authentication
    ) {

        String email = authentication.getName();

        Teacher teacher = teacherRepository
                .findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        return ResponseEntity.ok(
                attendanceService.getAttendanceReport(
                        classId,
                        teacher.getId(),
                        fromDate,
                        toDate
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(
                attendanceService.getById(id)
        );

    }
    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAll(Authentication authentication) {

        String email = authentication.getName();

        Teacher teacher = teacherRepository
                .findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        return ResponseEntity.ok(
                attendanceService.getAllByTeacher(teacher.getId())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        attendanceService.delete(id);
        return ResponseEntity.ok(
                "Attendance deleted successfully"
        );

    }

}