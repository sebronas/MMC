package com.example.mmc.model;

import javax.persistence.*;
import java.util.List;

/** Entity class to hold instruments' data
 *  @author Kristaps Sebris, Elena Bebrisa, Georgijs Kadolciks
 *  @version 19th July 2022
 */

@Entity
@Table(name = "instruments")
public class InstrumentEntity {

    @Id
    @Column(name = "InstrumentID")
    private int instrumentId;

    @Basic
    @Column(name = "Instrument")
    private String instrument;

    @OneToMany
    @JoinColumn(name = "StudentsID")
    private List<StudentEntity> studentsID;

    public String getInstrument() { return instrument; }
}
