package com.example.mmc.Model;

import javax.persistence.*;
import java.util.List;

/** Entity class to hold accompanists' data
 *  @author Kristaps Sebris, Elena Bebrisa, Georgijs Kadolciks
 *  @version 19th July 2022
 */

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
