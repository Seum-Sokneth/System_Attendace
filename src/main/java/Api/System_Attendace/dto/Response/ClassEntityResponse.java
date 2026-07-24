package Api.System_Attendace.dto.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ClassEntityResponse {

    private Long id;

    private Long teacherId;

    private String teacherName;

    private String classCode;

    private String classTitle;

    private String schedule;

    private String classRoom;

    private String description;
    private Integer studentCount;

}