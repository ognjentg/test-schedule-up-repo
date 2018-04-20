package ba.telegroup.apps.faculty.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Subject {
    private Integer id;
    private String name;
    private Integer ects;
    private Integer idLinked;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ects", nullable = false)
    public Integer getEcts() {
        return ects;
    }

    public void setEcts(Integer ects) {
        this.ects = ects;
    }

    @Basic
    @Column(name = "id_linked")
    public Integer getIdLinked() {
        return idLinked;
    }

    public void setIdLinked(Integer idLinked) {
        this.idLinked = idLinked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (id != null ? !id.equals(subject.id) : subject.id != null) return false;
        if (name != null ? !name.equals(subject.name) : subject.name != null) return false;
        if (ects != null ? !ects.equals(subject.ects) : subject.ects != null) return false;
        return idLinked != null ? idLinked.equals(subject.idLinked) : subject.idLinked == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ects != null ? ects.hashCode() : 0);
        result = 31 * result + (idLinked != null ? idLinked.hashCode() : 0);
        return result;
    }
}
