package ba.telegroup.apps.faculty.repository.repositoryCustom;

import ba.telegroup.apps.faculty.model.modelCustom.SubjectProfessor;

import java.util.List;

public interface SubjectRepositoryCustom {

    List getAllExtended();

    List<SubjectProfessor> returnSubjectsByProfessor(Integer professorId);

}
