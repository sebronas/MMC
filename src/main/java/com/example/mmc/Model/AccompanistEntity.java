package com.example.mmc.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "accompanist")
public class AccompanistEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AccompanistID")
    private int accompanistId;
    @Basic
    @Column(name = "Accompanist")
    private String accompanist;

    public int getAccompanistId() {
        return accompanistId;
    }

    public void setAccompanistId(int accompanistId) {
        this.accompanistId = accompanistId;
    }

    public String getAccompanist() {
        return accompanist;
    }

    public void setAccompanist(String accompanist) {
        this.accompanist = accompanist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccompanistEntity that = (AccompanistEntity) o;
        return accompanistId == that.accompanistId && Objects.equals(accompanist, that.accompanist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accompanistId, accompanist);
    }
}
