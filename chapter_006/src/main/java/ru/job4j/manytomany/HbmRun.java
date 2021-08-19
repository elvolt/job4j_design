package ru.job4j.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata()
            .buildSessionFactory();

    public void add() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Author author1 = new Author("Author 1");
            Author author2 = new Author("Author 2");
            Author author3 = new Author("Author 3");
            Book book1 = new Book("book 1");
            Book book2 = new Book("book 2");
            Book book3 = new Book("book 3");
            Book book4 = new Book("book 4");
            author1.addBook(book1);
            author2.addBook(book2, book3);
            author3.addBook(book3, book4);
            session.persist(author1);
            session.persist(author2);
            session.persist(author3);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public void delete() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Author author = session.get(Author.class, 2);
            session.delete(author);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void main(String[] args) {
//        new HbmRun().add();
        new HbmRun().delete();
    }
}
