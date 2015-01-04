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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.xzymon.weblearning.model.Doc;
import com.xzymon.weblearning.model.User;
import com.xzymon.weblearning.service.AdminOperationsLocal;
import com.xzymon.weblearning.service.UserOperationsLocal;

@RunWith(Arquillian.class)
public class UserOperationsBeanTest {
	private User user;
	
	@Deployment
	public static Archive<?> createTestArchive(){
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage("com/xzymon/weblearning/model")
				.addPackage("com/xzymon/weblearning/service")
				.addAsResource("META-INF/test-persistence.xml")
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");
	}
	
	@EJB
	private UserOperationsLocal userOperations;
	
	@EJB
	private AdminOperationsLocal adminOperations;
	
	private Logger logger = Logger.getLogger(UserOperationsBeanTest.class.getSimpleName());
	
	@Before
	public void createSampleUser(){
		User user = adminOperations.createAdmin("u_firstName", "u_lastName", "u_nickName", "u_password");
	}
	
	@Test
	public void testCreateDoc() {
		byte[] binData = new byte[]{0x65, 0x66, 0x67, 0x68};
		Doc doc = userOperations.createDoc("example.txt", "text/plain", binData, user);
		Assert.assertNotNull(doc.getId());
		Assert.assertEquals((Integer)4, doc.getFileLength());
		logger.info(doc.getFileName() + " was persisted with id + " + doc.getId());
	}
	
	public void testRemoveDoc(){
		byte[] binData = new byte[]{0x65, 0x66, 0x67, 0x68};
		Doc doc = userOperations.createDoc("example.txt", "text/plain", binData, user);
		logger.info(doc.getFileName() + " was persisted with id + " + doc.getId());
		
	}

}
