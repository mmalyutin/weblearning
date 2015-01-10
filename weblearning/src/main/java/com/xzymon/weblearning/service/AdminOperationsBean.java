package com.xzymon.weblearning.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.xzymon.weblearning.model.Admin;
import com.xzymon.weblearning.model.Student;
import com.xzymon.weblearning.model.Subject;
import com.xzymon.weblearning.model.Teacher;
import com.xzymon.weblearning.model.User;

@Stateless
@Local(AdminOperationsLocal.class)
public class AdminOperationsBean implements AdminOperationsLocal {

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
			String nickName, String password) {
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
	public void removeUser(User toRemove) {
		User merged = em.merge(toRemove);
		if (merged != null) {
			em.remove(merged);
		}
	}

	@Override
	public List<Admin> listAdmins() {
		TypedQuery<Admin> tquery = em.createQuery("from Admin", Admin.class);
		List<Admin> list = tquery.getResultList();
		return list;
	}

	@Override
	public List<Student> listStudents() {
		TypedQuery<Student> tquery = em.createQuery("from Student",
				Student.class);
		List<Student> list = tquery.getResultList();
		return list;
	}

	@Override
	public List<Teacher> listTeachers() {
		TypedQuery<Teacher> tquery = em.createQuery("from Teacher",
				Teacher.class);
		List<Teacher> list = tquery.getResultList();
		return list;
	}

	@Override
	public List<User> listUsers() {
		TypedQuery<User> tquery = em.createQuery("from User", User.class);
		List<User> list = tquery.getResultList();
		return list;
	}

	@Override
	public Admin findAdmin(Long id) {
		TypedQuery<Admin> tquery = em.createQuery(
				"from Admin a where a.id=:id", Admin.class);
		tquery.setParameter("id", id);
		Admin admin = null;
		try {
			admin = tquery.getSingleResult();
		} catch (NoResultException ex) {

		}
		return admin;
	}

	@Override
	public Student findStudent(Long id) {
		TypedQuery<Student> tquery = em.createQuery(
				"from Student s where s.id=:id", Student.class);
		tquery.setParameter("id", id);
		Student student = null;
		try {
			student = tquery.getSingleResult();
		} catch (NoResultException ex) {

		}
		return student;
	}

	@Override
	public Teacher findTeacher(Long id) {
		TypedQuery<Teacher> tquery = em.createQuery(
				"from Teacher t where t.id=:id", Teacher.class);
		tquery.setParameter("id", id);
		Teacher teacher = null;
		try {
			teacher = tquery.getSingleResult();
		} catch (NoResultException ex) {

		}
		return teacher;
	}

	@Override
	public User findUser(Long id) {
		TypedQuery<User> tquery = em.createQuery("from User u where u.id=:id",
				User.class);
		tquery.setParameter("id", id);
		User user = null;
		try {
			user = tquery.getSingleResult();
		} catch (NoResultException ex) {

		}
		return user;
	}

}
