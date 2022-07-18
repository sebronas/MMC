package com.example.mmc.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Data
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
    @ManyToOne
    @JoinColumn(name = "InstrumentID")
    private InstrumentsEntity instrumentId;
    @ManyToOne
    @JoinColumn(name = "TeacherID")
    private TeachersEntity teachers;
    @ManyToOne
    @JoinColumn(name = "AccompanistID")
    private AccompanistEntity accompanistId;
}
