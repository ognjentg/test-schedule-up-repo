package ba.telegroup.apps.faculty.controller;

import ba.telegroup.apps.faculty.model.Faculty;
import ba.telegroup.apps.faculty.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/faculty")
@RestController
public class FacultyController {

    private final
    FacultyRepository facultyRepository;

    @Autowired
    public FacultyController(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @RequestMapping(value = "/returnSumOfSalary/{facultyId}", method = RequestMethod.GET)
    public Double returnSumOfSalary(@PathVariable Integer facultyId) {
        return facultyRepository.returnSumOfSalary(facultyId);
    }


    /* NEW TODO check naming -> refactor */
    /**
     * Get all Facultys
     *
     * @return all facultys
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    /**
     * Get faculty by id
     *
     * @param id faculty id
     * @return faculty
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Faculty getById(@PathVariable Integer id) {
        return facultyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found [" + id + "]"));
    }

    /**
     * Update faculty
     *
     * @param faculty faculty for update
     * @return true if faculty is updated, false if faculty is not updated
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Boolean update(@RequestBody Faculty faculty) {
        try {
            facultyRepository.saveAndFlush(faculty);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Insert new faculty
     *
     * @param faculty new faculty for insert
     * @return inserted faculty
     */
    @RequestMapping(method = RequestMethod.POST)
    public Faculty insert(@RequestBody Faculty faculty) {
        try {
            return facultyRepository.saveAndFlush(faculty);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable Integer id) {
        try {
            facultyRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
