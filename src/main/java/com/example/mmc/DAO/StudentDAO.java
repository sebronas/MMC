package com.example.mmc.DAO;

import com.example.mmc.Model.StudentEntity;
import com.example.mmc.Utility.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentDAO {

    public ObservableList<StudentEntity> getAll() {

        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(StudentEntity.class);

        Root<StudentEntity> root = query.from(StudentEntity.class);

        List<StudentEntity> studentsList = session.createQuery(query).getResultList();
        System.out.println(studentsList.toString());

        System.out.println(studentsList.get(3).getTeacherId().getTeacher());

        session.close();

        return FXCollections.observableArrayList(studentsList);
    }

    public ObservableList<StudentEntity> getGrades() {

        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(StudentEntity.class);

        Root<StudentEntity> root = query.from(StudentEntity.class);

        List<StudentEntity> gradeList = session.createQuery(query.select(root.get("grade"))).getResultList();

        System.out.println(gradeList.toString());
        session.close();

        return FXCollections.observableArrayList(gradeList);
    }
}