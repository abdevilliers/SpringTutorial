package com.example.demo;
import java.util.*;

import com.example.demo.dao.StudentDAO;
import com.example.demo.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {


	@Autowired
	StudentDAO studentDAO;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args){
		return ((r)->{System.out.println("started app after initializing all beans!!");
			//persistStudent();
			//findById(3);
			//findByFirstName("ab");
			//deleteById(1);
			deleteStudentByFirstName("ab");
		});
	}
	public void deleteStudentByFirstName(String name){
		Integer cnt = studentDAO.deleteStudentByFirstName(name);
		System.out.println(cnt+" is the number of students delted!!");

	}
	public void deleteById(Integer id){
		String res = studentDAO.deleteById(id);
		System.out.println(res);
	}
	public void findById(Integer id){
		Student stud = studentDAO.findById(id);
		System.out.println(stud);
	}
	public void findByFirstName(String name){
		List<Student> studs  = studentDAO.findByName(name);
		for(Student stud: studs){

			System.out.println(stud);
		}
	}
	public void persistStudent(){
		Student stud = new Student("John","Doe","johndoe1@gmail.com");
		Student stud1 = new Student("ab","devilliers","abdvilliers@gmail.com");
		Integer newid = studentDAO.save(stud);
		Integer newid1 = studentDAO.save(stud1);
		System.out.println(newid+" has been persisted into db");
		System.out.println(newid1+" has been persisted into db");
	}

}
