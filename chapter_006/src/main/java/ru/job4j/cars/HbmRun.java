package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sf = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Engine engine1 = new Engine("Engine 1");
            Engine engine2 = new Engine("Engine 2");
            Engine engine3 = new Engine("Engine 3");
            Driver driver1 = new Driver("Driver 1");
            Driver driver2 = new Driver("Driver 2");
            Driver driver3 = new Driver("Driver 3");
            Car car1 = new Car("Car 1", engine1);
            Car car2 = new Car("Car 2", engine2);
            Car car3 = new Car("Car 3", engine3);
            car1.addDriver(driver1);
            car2.addDriver(driver2);
            car3.addDriver(driver2, driver3);
            session.persist(car1);
            session.persist(car2);
            session.persist(car3);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
