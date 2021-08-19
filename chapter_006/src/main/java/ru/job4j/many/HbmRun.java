package ru.job4j.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sf = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        try (Session session = sf.openSession()) {
            session.beginTransaction();
            CarMark mark = new CarMark("BMW");
            CarModel model1 = new CarModel("X1");
            CarModel model2 = new CarModel("X5");
            CarModel model3 = new CarModel("X6");
            CarModel model4 = new CarModel("M6");
            CarModel model5 = new CarModel("M8");
            mark.addModel(model1, model2, model3, model4, model5);
            session.save(mark);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
