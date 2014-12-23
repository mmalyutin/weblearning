package weblearning;

import java.util.logging.Logger;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.xzymon.weblearning.model.Admin;
import com.xzymon.weblearning.model.Student;
import com.xzymon.weblearning.model.Subject;
import com.xzymon.weblearning.model.Teacher;
import com.xzymon.weblearning.service.AdminOperationsBean;
import com.xzymon.weblearning.service.AdminOperationsLocal;

@RunWith(Arquillian.class)
public class AdminOperationsBeanTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage("com/xzymon/weblearning/model")
				.addPackage("com/xzymon/weblearning/service")
				.addAsResource("META-INF/test-persistence.xml")
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");
	}
	
	@EJB
	private AdminOperationsLocal adminOperations;

	private Logger logger = Logger.getLogger(AdminOperationsBean.class.getSimpleName());
	
	@Test
	public void testCreateAdmin() {
		Admin admin = adminOperations.createAdmin("firstName", "lastName", "a_nickName", "password");
		Assert.assertNotNull(admin.getId());
		logger.info(admin.getNickName() + " was persisted with id " + admin.getId());
	}
	
	@Test
	public void testCreateStudent() {
		Student student = adminOperations.createStudent("firstName", "lastName", "s_nickName", "password");
		Assert.assertNotNull(student.getId());
		logger.info(student.getNickName() + " was persisted with id " + student.getId());
	}
	
	@Test
	public void testCreateTeacher() {
		Teacher teacher = adminOperations.createTeacher("firstName", "lastName", "t_nickName", "password");
		Assert.assertNotNull(teacher.getId());
		logger.info(teacher.getNickName() + " was persisted with id " + teacher.getId());
	}
	
	@Test
	public void testCreateSubject() {
		Teacher teacher = adminOperations.createTeacher("ts_firstName", "ts_lastName", "ts_nickName", "ts_password");
		Subject subject = adminOperations.createSubject(teacher, "name", "description");
		Assert.assertNotNull(subject.getId());
		logger.info(subject.getName() + " was persisted with id + " + subject.getId());
	}
}
