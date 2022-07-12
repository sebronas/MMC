package com.example.mmc.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "students")
public class StudentsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "StudentsID")
    private int studentsId;
    @Basic
    @Column(name = "Student")
    private String student;
    @Basic
    @Column(name = "Grade")
    private String grade;
    @Basic
    @Column(name = "InstrumentID")
    private Integer instrumentId;
    @Basic
    @Column(name = "TeacherID")
    private Integer teacherId;
    @Basic
    @Column(name = "AccompanistID")
    private Integer accompanistId;

    public int getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(int studentsId) {
        this.studentsId = studentsId;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Object getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Integer instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getAccompanistId() {
        return accompanistId;
    }

    public void setAccompanistId(Integer accompanistId) {
        this.accompanistId = accompanistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsEntity that = (StudentsEntity) o;
        return studentsId == that.studentsId && Objects.equals(student, that.student) && Objects.equals(grade, that.grade) && Objects.equals(instrumentId, that.instrumentId) && Objects.equals(teacherId, that.teacherId) && Objects.equals(accompanistId, that.accompanistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentsId, student, grade, instrumentId, teacherId, accompanistId);
    }
}
