package ba.telegroup.apps.faculty.repository;

import ba.telegroup.apps.faculty.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
