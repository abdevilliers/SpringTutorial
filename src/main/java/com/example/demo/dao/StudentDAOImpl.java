package com.example.demo.dao;

import com.example.demo.models.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class StudentDAOImpl implements StudentDAO{

    EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager em){
        entityManager = em;
    }

    @Transactional
    public Integer save(Student theStudent){
        entityManager.persist(theStudent);
        return theStudent.getId();
    }
    public Student findById(Integer id){
        return entityManager.find(Student.class,id);
    }
    public List<Student> findByName(String name){
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student where firstName=:somename",Student.class);
        theQuery.setParameter("somename",name);
        return theQuery.getResultList();
    }

    @Transactional
    public String deleteById(Integer id){
        Student st = findById(id);
        if(st==null){
            return "Student not found with given id!!";
        }
        entityManager.remove(st);
        return "Student deleted successfully!!";
    }

    @Transactional
    public void deleteStudent(Student student){
        entityManager.remove(student);
    }

    @Transactional
    public Integer deleteStudentByFirstName(String name){
        TypedQuery<Student> query = entityManager.createQuery("from Student where firstName=:firstName",Student.class);
        query.setParameter("firstName",name);
        List<Student> students = query.getResultList();
        Integer res = students.size();
        for(Student stud: students){
            deleteStudent(stud);
        }
        return res;
    }
}
