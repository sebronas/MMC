package com.example.mmc.Utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** Utility class to obtain Hibernate session
 *  @author Kristaps Sebris, Elena Bebrisa, Georgijs Kadolciks
 *  @version 19th July 2022
 */

public class HibernateUtil {

    /** Opens a Hibernate session
     * @return Returns an open Session object
     */

    public static Session getSession() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory.openSession();
    }
}
