package com.example.mmc.DAO;

import com.example.mmc.Model.TeachersEntity;
import com.example.mmc.Utility.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeacherDAO {
    public ObservableList<TeachersEntity> getAll() {

        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(TeachersEntity.class);

        Root<TeachersEntity> root = query.from(TeachersEntity.class);
        List<TeachersEntity> teachersList = session.createQuery(query.select(root.get("teacher"))).getResultList();
        System.out.println(teachersList.toString());
        session.close();

        return FXCollections.observableArrayList(teachersList);
    }
}
