package ba.telegroup.apps.faculty.model.modelCustom;

import ba.telegroup.apps.faculty.model.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@SqlResultSetMapping(
//        name = "SubjectProfessorMapping",
//        classes = @ConstructorResult(
//                targetClass = SubjectProfessor.class,
//                columns = {
//                        @ColumnResult(name = "name"),
//                        @ColumnResult(name = "ects"),
//                        @ColumnResult(name = "first_name"),
//                        @ColumnResult(name = "last_name")
//                }
//        )
//)
//@MappedSuperclass
public class SubjectProfessor extends Subject {
    private String firstName;
    private String lastName;

    public SubjectProfessor() {
    }

    public SubjectProfessor(String name, Integer ects, String firstName, String lastName) {
        setName(name);
        setEcts(ects);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    @Override
    public Integer getId() {
        return super.getId();
    }

    @JsonIgnore
    @Override
    public Integer getIdLinked() {
        return super.getIdLinked();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + getName() + ", " + getEcts() + ")";
    }
}
