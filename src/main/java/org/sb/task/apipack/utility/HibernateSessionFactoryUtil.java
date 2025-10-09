package org.sb.task.apipack.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sb.task.apipack.model.User;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(User.class);
//                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//                sessionFactory = configuration.buildSessionFactory(builder.build());
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e){
                System.out.println(e);
            }
        }

        return sessionFactory;
    }
}
