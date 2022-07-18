package com.example.mmc.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teachers")
@Data
public class TeachersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TeacherID")
    private int teacherId;
    @Basic
    @Column(name = "Teacher")
    private String teacher;

    @OneToMany
    @JoinColumn(name = "StudentsID")
    private List<StudentsEntity> studentsID;
}
