package ba.telegroup.apps.faculty.controller;

import ba.telegroup.apps.faculty.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/faculty")
@RestController
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @RequestMapping(value = "/returnSumOfSalary/{facultyId}", method = RequestMethod.GET)
    public Double returnSumOfSalary(@PathVariable Integer facultyId) {
        return facultyRepository.returnSumOfSalary(facultyId);
    }

}
