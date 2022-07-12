package com.example.mmc.DAO;

import com.example.mmc.Model.AccompanistEntity;
import com.example.mmc.Utility.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AccompanistDAO {
    public ObservableList<AccompanistEntity> getAll() {

        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(AccompanistEntity.class);

        Root<AccompanistEntity> root = query.from(AccompanistEntity.class);

        List<AccompanistEntity> accompanistList = session.createQuery(query.select(root.get("accompanist"))).getResultList();
        System.out.println(accompanistList.toString());
        session.close();

        return FXCollections.observableArrayList(accompanistList);
    }
}
