package com.xzymon.weblearning.service;

import com.xzymon.weblearning.model.Admin;
import com.xzymon.weblearning.model.Student;
import com.xzymon.weblearning.model.Subject;
import com.xzymon.weblearning.model.Teacher;

public interface AdminOperationsBusiness {
	Admin createAdmin(String firstName, String lastName, String nickName, String password);
	Student createStudent(String firstName, String lastName, String nickName, String password);
	Teacher createTeacher(String firstName, String lastName, String nickName, String password);
	Subject createSubject(Teacher owner, String name, String description);
}
