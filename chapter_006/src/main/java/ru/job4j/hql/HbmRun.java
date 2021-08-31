package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.math.BigDecimal;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate one = new Candidate("Ivan", 12, new BigDecimal(30000));
            Candidate two = new Candidate("Pavel", 15, new BigDecimal(35000));
            Candidate three = new Candidate("Petr", 11, new BigDecimal(37000));

            session.save(one);
            session.save(two);
            session.save(three);

            System.out.println("----- Select ALL: -----");
            session.createQuery("from Candidate").list()
                    .forEach(System.out::println);

            System.out.println("----- Select by id = 2 -----");
            Query query = session.createQuery("from Candidate where id =: paramId");
            query.setParameter("paramId", 2);
            System.out.println(query.uniqueResult());

            System.out.println("----- Select by name Petr -----");
            Query query1 = session.createQuery("from Candidate where name =: paramName");
            query1.setParameter("paramName", "Petr");
            query1.list().forEach(System.out::println);

            System.out.println("----- Update experience for Ivan from 12 to 13 -----");
            Query query2 = session.createQuery(
                    "update Candidate set experience =: paramExperience where id = 1");
            query2.setParameter("paramExperience", 13);
            query2.executeUpdate();
            System.out.println(session.get(Candidate.class, 1));

            System.out.println("----- Delete Petr -----");
            Query query3 = session.createQuery("delete from Candidate where id =: paramId");
            query3.setParameter("paramId", 3);
            query3.executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
