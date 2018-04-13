package ba.telegroup.apps.faculty.repository.repositoryCustom.repositoryImpl;

import ba.telegroup.apps.faculty.model.Subject;
import ba.telegroup.apps.faculty.model.modelCustom.SubjectLectureProfessor;
import ba.telegroup.apps.faculty.model.modelCustom.SubjectProfessor;
import ba.telegroup.apps.faculty.repository.repositoryCustom.SubjectRepositoryCustom;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Override
    public List<SubjectProfessor> returnSubjectsByProfessor(Integer professorId) {
        List<SubjectProfessor> retVal = new ArrayList<>();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("returnSubjectsByProfessor");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN).setParameter(1, professorId);
        if (query.execute()) {
            List<Object[]> results = query.getResultList();
            results.forEach((record) -> {
                SubjectProfessor professorSubject = new SubjectProfessor(String.valueOf(record[2]), Integer.parseInt(String.valueOf(record[3])), String.valueOf(record[0]), String.valueOf(record[1]));
                System.out.println(professorSubject);
                retVal.add(professorSubject);
            });
            return retVal;
        }
        return null;
    }

}
