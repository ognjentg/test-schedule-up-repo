package ba.telegroup.apps.faculty.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@SuppressWarnings("ALL")
public class LecturePK implements Serializable {
    private Integer studyProgramId;
    private Integer professorId;
    private Integer subjectId;

    @Column(name = "study_program_id", nullable = false)
    @Id
    public Integer getStudyProgramId() {
        return studyProgramId;
    }

    public void setStudyProgramId(Integer studyProgramId) {
        this.studyProgramId = studyProgramId;
    }

    @Column(name = "professor_id", nullable = false)
    @Id
    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    @Column(name = "subject_id", nullable = false)
    @Id
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LecturePK lecturePK = (LecturePK) o;

        if (studyProgramId != null ? !studyProgramId.equals(lecturePK.studyProgramId) : lecturePK.studyProgramId != null)
            return false;
        if (professorId != null ? !professorId.equals(lecturePK.professorId) : lecturePK.professorId != null)
            return false;
        return subjectId != null ? subjectId.equals(lecturePK.subjectId) : lecturePK.subjectId == null;
    }

    @Override
    public int hashCode() {
        int result = studyProgramId != null ? studyProgramId.hashCode() : 0;
        result = 31 * result + (professorId != null ? professorId.hashCode() : 0);
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        return result;
    }
}
