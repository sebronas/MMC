package com.example.mmc.Model;

import javax.persistence.*;
import java.util.List;

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
