package com.example.mmc.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "instruments")
@Data
public class InstrumentsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "InstrumentID")
    private int instrumentId;
    @Basic
    @Column(name = "Instrument")
    private String instrument;

    @OneToMany
    @JoinColumn(name = "StudentsID")
    private List<StudentsEntity> studentsID;

}
