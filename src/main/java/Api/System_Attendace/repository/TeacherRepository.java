package Api.System_Attendace.repository;

import Api.System_Attendace.model.Teacher;
import Api.System_Attendace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Optional<Teacher> findByUser(User user);
    Optional<Teacher> findByUserEmail(String email);
}
