package ba.telegroup.apps.faculty.controller;

import ba.telegroup.apps.faculty.model.Subject;
import ba.telegroup.apps.faculty.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/subject")
@RestController
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

}
