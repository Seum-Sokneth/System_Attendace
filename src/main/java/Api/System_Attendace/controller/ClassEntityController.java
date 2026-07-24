package Api.System_Attendace.controller;

import Api.System_Attendace.dto.Request.ClassEntityRequest;
import Api.System_Attendace.dto.Response.ClassEntityResponse;
import Api.System_Attendace.service.Service.ClassEntityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/class")
@AllArgsConstructor
public class ClassEntityController {

    private final ClassEntityService classEntityService;

    @PostMapping
    public ResponseEntity<ClassEntityResponse> create(@Valid @RequestBody ClassEntityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(classEntityService.create(request));
    }
    @GetMapping("/test")
    public String test(){
        return "Class Controller Working";
    }

    @GetMapping
    public ResponseEntity<List<ClassEntityResponse>> findAll() {
        return ResponseEntity.ok(classEntityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassEntityResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(classEntityService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassEntityResponse> update(
            @Valid @RequestBody ClassEntityRequest request,
            @PathVariable Long id) {

        return ResponseEntity.ok(classEntityService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classEntityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}