package ba.telegroup.apps.faculty.repository;

import ba.telegroup.apps.faculty.model.Faculty;
import ba.telegroup.apps.faculty.repository.repositoryCustom.FacultyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer>, FacultyRepositoryCustom {
}
