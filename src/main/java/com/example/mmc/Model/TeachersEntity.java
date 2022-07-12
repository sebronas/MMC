package com.example.mmc.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teachers")
public class TeachersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TeacherID")
    private int teacherId;
    @Basic
    @Column(name = "Teacher")
    private String teacher;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachersEntity that = (TeachersEntity) o;
        return teacherId == that.teacherId && Objects.equals(teacher, that.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacher);
    }
}
