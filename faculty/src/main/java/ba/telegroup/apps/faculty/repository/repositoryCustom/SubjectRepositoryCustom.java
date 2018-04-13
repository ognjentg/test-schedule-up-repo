package ba.telegroup.apps.faculty.repository.repositoryCustom;

import ba.telegroup.apps.faculty.model.Subject;
import ba.telegroup.apps.faculty.model.modelCustom.SubjectLectureProfessor;
import ba.telegroup.apps.faculty.model.modelCustom.SubjectProfessor;

import java.util.List;

public interface SubjectRepositoryCustom {

    public List<SubjectLectureProfessor> getAllExtended();

    public List<SubjectProfessor> returnSubjectsByProfessor(Integer progessorId);

}
