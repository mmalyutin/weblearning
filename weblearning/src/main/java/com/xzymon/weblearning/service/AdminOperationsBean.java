package com.xzymon.weblearning.service;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.xzymon.weblearning.model.Admin;
import com.xzymon.weblearning.model.Student;
import com.xzymon.weblearning.model.Subject;
import com.xzymon.weblearning.model.Teacher;

@Stateless
@Local(AdminOperationsLocal.class)
public class AdminOperationsBean implements AdminOperationsLocal{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Admin createAdmin(String firstName, String lastName,
			String nickName, String password) {
		Admin admin = new Admin();
		admin.setFirstName(firstName);
		admin.setLastName(lastName);
		admin.setNickName(nickName);
		admin.setPasswordHash(password);
		em.persist(admin);
		return admin;
	}
	
	@Override
	public Student createStudent(String firstName, String lastName, 
			String nickName, String password){
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setNickName(nickName);
		student.setPasswordHash(password);
		em.persist(student);
		return student;
	}
	
	@Override
	public Teacher createTeacher(String firstName, String lastName,
			String nickName, String password) {
		Teacher teacher = new Teacher();
		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
		teacher.setNickName(nickName);
		teacher.setPasswordHash(password);
		em.persist(teacher);
		return teacher;
	}

	@Override
	public Subject createSubject(Teacher owner, String name, String description) {
		Subject subject = new Subject();
		subject.setOwner(owner);
		subject.setName(name);
		subject.setDescription(description);
		em.persist(subject);
		return subject;
	}

}
