package com.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(student.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {

            student s = new student();

            s.setName("Sureshni");
            s.setEmail("sureshni@gmail.com");
            s.setCourse("Artificial Intelligence and Data Science");

            session.beginTransaction();

            session.persist(s);

            session.getTransaction().commit();

            System.out.println("Student inserted successfully");

            session.beginTransaction();

            student existingStudent = session.get(student.class, s.getId());

            existingStudent.setCourse("Data Science");

            session.getTransaction().commit();

            System.out.println("Student updated successfully");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            session.close();
            factory.close();

        }
    }
}