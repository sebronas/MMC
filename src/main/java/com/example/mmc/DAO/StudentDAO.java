package com.example.mmc.DAO;

import com.example.mmc.Model.StudentsEntity;
import com.example.mmc.Utility.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentDAO implements DAOInterface<StudentsEntity> {
    @Override
    public int addData(StudentsEntity data) {
        return 0;
    }

    @Override
    public int delData(StudentsEntity data) {
        return 0;
    }

    @Override
    public int updateData(StudentsEntity data) {
        return 0;
    }

    @Override
    public ObservableList<StudentsEntity> getAll() {

        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(StudentsEntity.class);

        Root<StudentsEntity> root = query.from(StudentsEntity.class);

        List<StudentsEntity> studentsList = session.createQuery(query.select(root.get("student"))).getResultList();
        System.out.println(studentsList.toString());
        session.close();

        return FXCollections.observableArrayList(studentsList);
    }
}