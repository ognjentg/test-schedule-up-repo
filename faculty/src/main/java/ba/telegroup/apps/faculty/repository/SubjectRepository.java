package ba.telegroup.apps.faculty.repository;

import ba.telegroup.apps.faculty.model.Subject;
import ba.telegroup.apps.faculty.repository.repositoryCustom.SubjectRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer>, SubjectRepositoryCustom {

    public List<Subject> getAllByNameContainsOrderByEctsDesc(String text);

}
