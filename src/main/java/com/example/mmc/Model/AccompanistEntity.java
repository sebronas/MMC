package com.example.mmc.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accompanist")
@Data
public class AccompanistEntity {

    @Id
    @Column(name = "AccompanistID")
    private int accompanistId;

    @Basic
    @Column(name = "Accompanist")
    private String accompanist;

    @OneToMany
    @JoinColumn(name = "StudentsID")
    private List<StudentEntity> studentsID;
}
