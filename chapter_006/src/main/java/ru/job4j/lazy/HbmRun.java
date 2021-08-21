package ru.job4j.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sf = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            List<Mark> marks = session.createQuery(
                    "select distinct mark from Mark mark join fetch mark.models"
            ).list();
            session.getTransaction().commit();
            session.close();
            marks.stream()
                    .map(Mark::getModels)
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
