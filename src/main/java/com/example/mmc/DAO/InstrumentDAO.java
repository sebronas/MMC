package com.example.mmc.DAO;

import com.example.mmc.Model.InstrumentsEntity;
import com.example.mmc.Utility.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class InstrumentDAO{

    public ObservableList<InstrumentsEntity> getAll() {

        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(InstrumentsEntity.class);

        Root<InstrumentsEntity> root = query.from(InstrumentsEntity.class);

        List<InstrumentsEntity> instrumentsList = session.createQuery(query.select(root.get("instrument"))).getResultList();
        System.out.println(instrumentsList.toString());
        session.close();

        return FXCollections.observableArrayList(instrumentsList);
    }
}
