package com.xzymon.weblearning.service;

import java.util.List;

import com.xzymon.weblearning.model.Admin;
import com.xzymon.weblearning.model.Student;
import com.xzymon.weblearning.model.Subject;
import com.xzymon.weblearning.model.Teacher;
import com.xzymon.weblearning.model.User;

public interface AdminOperationsBusiness {
	Admin createAdmin(String firstName, String lastName, String nickName, String password);
	Student createStudent(String firstName, String lastName, String nickName, String password);
	Teacher createTeacher(String firstName, String lastName, String nickName, String password);
	void removeUser(User toRemove);
	List<Admin> listAdmins();
	List<Student> listStudents();
	List<Teacher> listTeachers();
	List<User> listUsers();
	Admin findAdmin(Long id);
	Student findStudent(Long id);
	Teacher findTeacher(Long id);
	User findUser(Long id);
}
