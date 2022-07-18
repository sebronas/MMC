package com.example.mmc.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accompanist")
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

    public String getAccompanist() {
        return accompanist;
    }
}
