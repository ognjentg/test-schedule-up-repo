package ba.telegroup.apps.faculty.repository.repositoryCustom.repositoryImpl;

import ba.telegroup.apps.faculty.repository.repositoryCustom.FacultyRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

public class FacultyRepositoryImpl implements FacultyRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Double returnSumOfSalary(Integer facultyId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("returnSumOfSalary");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN).setParameter(1, facultyId);
        query.registerStoredProcedureParameter(2, Double.class, ParameterMode.OUT);
//        The "false" returned by "query.execute()" means that the JDBC statement didn't read any rows.
//        This is expected, as stored procedures don't directly return ResultSets, they only return values for OUT parameters.
//        if (query.execute())
//            return (Double) query.getOutputParameterValue(2);
//        return null;
        return (Double) query.getOutputParameterValue(2);
    }

}
