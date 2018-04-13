package ba.telegroup.apps.faculty.model;

import javax.persistence.*;

@Entity
@IdClass(LecturePK.class)
public class Lecture {
    private Integer studyProgramId;
    private Integer professorId;
    private Integer subjectId;
    private Integer classNumber;

    @Id
    @Column(name = "study_program_id", nullable = false)
    public Integer getStudyProgramId() {
        return studyProgramId;
    }

    public void setStudyProgramId(Integer studyProgramId) {
        this.studyProgramId = studyProgramId;
    }

    @Id
    @Column(name = "professor_id", nullable = false)
    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    @Id
    @Column(name = "subject_id", nullable = false)
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "class_number", nullable = false)
    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecture lecture = (Lecture) o;

        if (studyProgramId != null ? !studyProgramId.equals(lecture.studyProgramId) : lecture.studyProgramId != null)
            return false;
        if (professorId != null ? !professorId.equals(lecture.professorId) : lecture.professorId != null) return false;
        if (subjectId != null ? !subjectId.equals(lecture.subjectId) : lecture.subjectId != null) return false;
        if (classNumber != null ? !classNumber.equals(lecture.classNumber) : lecture.classNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studyProgramId != null ? studyProgramId.hashCode() : 0;
        result = 31 * result + (professorId != null ? professorId.hashCode() : 0);
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        result = 31 * result + (classNumber != null ? classNumber.hashCode() : 0);
        return result;
    }
}
