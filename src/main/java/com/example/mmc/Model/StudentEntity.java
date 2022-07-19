package com.example.mmc.Model;

import javax.persistence.*;

/** Entity class to hold students' data
 *  @author Kristaps Sebris, Elena Bebrisa, Georgijs Kadolciks
 *  @version 19th July 2022
 */

@Entity
@Table(name = "students")
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
