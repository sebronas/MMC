package com.example.mmc.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Data
public class StudentEntity {

    @Id
    @Column(name = "StudentsID")
    private int studentsId;

    @Basic
    @Column(name = "Student")
    private String student;

    @Basic
    @Column(name = "Grade")
    private String grade;

    @ManyToOne
    @JoinColumn(name = "InstrumentID")
    private InstrumentEntity instrumentId;

    @ManyToOne
    @JoinColumn(name = "TeacherID")
    private TeacherEntity teacherId;

    @ManyToOne
    @JoinColumn(name = "AccompanistID")
    private AccompanistEntity accompanistId;

    public String getStudent() { return student; }

    public String getGrade() { return grade; }

    public InstrumentEntity getInstrumentId() { return instrumentId; }

    public TeacherEntity getTeacherId() { return teacherId; }

    public AccompanistEntity getAccompanistId() { return accompanistId; }
}