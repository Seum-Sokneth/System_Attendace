package Api.System_Attendace.controller;


import Api.System_Attendace.dto.Request.AttendanceRequest;
import Api.System_Attendace.dto.Response.AttendanceReportResponse;
import Api.System_Attendace.dto.Response.AttendanceResponse;

import Api.System_Attendace.service.Service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;
    @PostMapping
    public ResponseEntity<AttendanceResponse> create(@RequestBody AttendanceRequest request){
        return new ResponseEntity<>(
                attendanceService.create(request),
                HttpStatus.CREATED
        );
    }
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
            @RequestParam String fromDate,
            @RequestParam String toDate) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceReport(
                        classId,
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
    public ResponseEntity<List<AttendanceResponse>> getAll(){
        return ResponseEntity.ok(
                attendanceService.getAll()
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