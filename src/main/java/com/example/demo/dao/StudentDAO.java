package com.example.demo.dao;

import com.example.demo.models.Student;
import java.util.*;

public interface StudentDAO {
    Integer save(Student theStudent);
    Student findById(Integer id);
    List<Student> findByName(String name);
    String deleteById(Integer id);
    Integer deleteStudentByFirstName(String name);
}
