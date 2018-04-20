package ba.telegroup.apps.faculty.controller;

import ba.telegroup.apps.faculty.model.Subject;
import ba.telegroup.apps.faculty.model.modelCustom.SubjectProfessor;
import ba.telegroup.apps.faculty.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/subject")
@RestController
public class SubjectController {

    private final
    SubjectRepository subjectRepository;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Subject getById(@PathVariable Integer id) {
        return subjectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found [" + id + "]"));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Boolean update(@RequestBody Subject subject) {
        try {
            subjectRepository.saveAndFlush(subject);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Subject insert(@RequestBody Subject subject) {
        try {
            return subjectRepository.saveAndFlush(subject);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getAlByNameContains/{nekiText}", method = RequestMethod.GET)
    public List<Subject> getAllByEcts(@PathVariable("nekiText") String text) {
        return subjectRepository.getAllByNameContainsOrderByEctsDesc(text);
    }

    @RequestMapping(value = "/getAllExtended", method = RequestMethod.GET)
    public List getAllExtended() {
        return subjectRepository.getAllExtended();
    }

    @RequestMapping(value = "/findAllByIdIsIn", method = RequestMethod.GET)
    public List<Subject> findAllByIdIsIn() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        return subjectRepository.findAllByIdIsIn(ids);
    }

    @RequestMapping(value = "/returnSubjectsByProfessor/{professorId}", method = RequestMethod.GET)
    public List<SubjectProfessor> returnSubjectsByProfessor(@PathVariable Integer professorId) {
        return subjectRepository.returnSubjectsByProfessor(professorId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable Integer id) {
        try {
            subjectRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
