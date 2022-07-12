package com.example.mmc.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "instruments")
public class InstrumentsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "InstrumentID")
    private int instrumentId;
    @Basic
    @Column(name = "Instrument")
    private String instrument;

    public int getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstrumentsEntity that = (InstrumentsEntity) o;
        return instrumentId == that.instrumentId && Objects.equals(instrument, that.instrument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instrumentId, instrument);
    }
}
