package com.example.mmc.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
public class TeacherEntity {

    @Id
    @Column(name = "TeacherID")
    private int teacherId;

    @Basic
    @Column(name = "Teacher")
    private String teacher;

    @OneToMany
    @JoinColumn(name = "StudentsID")
    private List<StudentEntity> studentsID;

    public String getTeacher() {
        return teacher;
    }
}
