package com.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory factory =
                configuration.buildSessionFactory();

        // INSERT STUDENT
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = new Student(
                "Rithika",
                "rithika@gmail.com",
                "AI and Data Science"
        );

        session.persist(student);
        transaction.commit();

        int studentId = student.getId();

        System.out.println("Student inserted successfully!");
        System.out.println("Student ID: " + studentId);

        session.close();

        // UPDATE STUDENT
        Session updateSession = factory.openSession();
        Transaction updateTransaction =
                updateSession.beginTransaction();

        Student existingStudent =
                updateSession.get(Student.class, studentId);

        if (existingStudent != null) {

            existingStudent.setCourse("Computer Science");

            updateTransaction.commit();

            System.out.println("Student updated successfully!");
            System.out.println("Updated Course: "
                    + existingStudent.getCourse());

        } else {
            updateTransaction.rollback();
            System.out.println("Student not found!");
        }

        updateSession.close();
        factory.close();
    }
}