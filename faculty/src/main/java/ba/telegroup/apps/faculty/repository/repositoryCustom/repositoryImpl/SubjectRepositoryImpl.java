package ba.telegroup.apps.faculty.repository.repositoryCustom.repositoryImpl;

import ba.telegroup.apps.faculty.model.modelCustom.SubjectLectureProfessor;
import ba.telegroup.apps.faculty.repository.repositoryCustom.SubjectRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class SubjectRepositoryImpl implements SubjectRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    private static final String SQL_GEL_ALL_EXTENDED = "SELECT s.*, p.first_name, p.last_name, l.class_number\n" +
            "FROM subject s\n" +
            "JOIN lecture l on s.id = l.subject_id\n" +
            "JOIN professor p on l.professor_id = p.id";

    @Override
    public List<SubjectLectureProfessor> getAllExtended() {
        return entityManager.createNativeQuery(SQL_GEL_ALL_EXTENDED, "SubjectLectureProfessorMapping").getResultList();
    }

}
