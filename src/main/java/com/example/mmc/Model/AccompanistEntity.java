package com.example.mmc.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "accompanist")
@Data
public class AccompanistEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AccompanistID")
    private int accompanistId;
    @Basic
    @Column(name = "Accompanist")
    private String accompanist;

    @OneToMany
    @JoinColumn(name = "StudentsID")
    private List<StudentsEntity> studentsID;
}
