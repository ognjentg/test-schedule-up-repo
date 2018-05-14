package ba.telegroup.apps.gk.controller.genericController;

import ba.telegroup.apps.gk.common.exceptions.BadRequestException;
import ba.telegroup.apps.gk.common.exceptions.ForbiddenException;
import ba.telegroup.apps.gk.controller.genericLogger.GenericLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by drstjepanovic on 7/22/2017.
 */
public class GenericController<T, ID extends Serializable> extends GenericLogger<T> {

    @PersistenceContext
    private EntityManager entityManager;

    protected JpaRepository<T, ID> repo;

    public GenericController(JpaRepository<T, ID> repo) {
        this.repo = repo;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<T> getAll() throws ForbiddenException {
        return repo.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    T findById(@PathVariable("id") ID id) throws ForbiddenException {
        return repo.findOne(id);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    T insert(@RequestBody T object) throws BadRequestException, ForbiddenException {
        T ret = null;
        if ((ret = repo.saveAndFlush(object)) != null) {
            entityManager.refresh(ret);
            logCreateAction(object);
            return ret;
        }
        throw new BadRequestException("Bad request");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    String update(@PathVariable ID id, @RequestBody T object) throws BadRequestException,ForbiddenException {
        T oldObject = cloner.deepClone(repo.findOne(id));
        if (repo.saveAndFlush(object) != null) {
            logUpdateAction(object, oldObject);
            return "Success";
        }
        throw new BadRequestException("Bad request");
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@PathVariable ID id) throws BadRequestException,ForbiddenException {
        try {
            T object = repo.findOne(id);
            repo.delete(id);
            logDeleteAction(object);
            return "Success";
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException("Bad Request");
        }
    }

}


    /*
    @RequestMapping(method = RequestMethod.DELETE)

    public @ResponseBody
    String delete(@PathVariable ID id) {
        try {
            repo.delete(id);
            return "Success";
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }*/

//    @RequestMapping(method = RequestMethod.DELETE)
//    public @ResponseBody
//    String delete(@RequestBody ID object) {
//        try {
//            repo.delete(object);
//            return "Success";
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }