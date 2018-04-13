package ba.telegroup.apps.faculty.repository.repositoryCustom.repositoryImpl;

import ba.telegroup.apps.faculty.repository.repositoryCustom.FacultyRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

public class FacultyRepositoryImpl implements FacultyRepositoryCustom {

    @PersistenceContext
    private
    EntityManager entityManager;

    @Override
    public Double returnSumOfSalary(Integer facultyId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("returnSumOfSalary");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN).setParameter(1, facultyId);
        query.registerStoredProcedureParameter(2, Double.class, ParameterMode.OUT);
        if (query.execute())
            return (Double) query.getOutputParameterValue(2);
        return null;
    }

}
