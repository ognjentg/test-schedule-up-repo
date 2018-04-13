package ba.telegroup.apps.faculty.controller;

import ba.telegroup.apps.faculty.model.Subject;
import ba.telegroup.apps.faculty.model.modelCustom.SubjectLectureProfessor;
import ba.telegroup.apps.faculty.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/subject")
@RestController
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

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
    public Boolean insert(@RequestBody Subject subject) {
        try {
            subjectRepository.saveAndFlush(subject);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/getAlByNameContains/{nekiText}", method = RequestMethod.GET)
    public List<Subject> getAllByEcts(@PathVariable("nekiText") String text) {
        return subjectRepository.getAllByNameContainsOrderByEctsDesc(text);
    }

    @RequestMapping(value = "/getAllExtended", method = RequestMethod.GET)
    public List<SubjectLectureProfessor> getAllExtended() {
        return subjectRepository.getAllExtended();
    }

}
