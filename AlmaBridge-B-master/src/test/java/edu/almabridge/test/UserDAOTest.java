package edu.almabridge.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.almabridge.dao.UserDetailsDAO;
import edu.almabridge.model.UserDetails;

public class UserDAOTest {

	private static ClassPathXmlApplicationContext context;
	private static UserDetails userDetails;
	private static UserDetailsDAO userDetailsDao;

	@BeforeClass
	public static void init() {
		context = new ClassPathXmlApplicationContext("ApplicationContextConfig.xml");
		context.refresh();
		userDetailsDao = (UserDetailsDAO) context.getBean("userDetailsDAO");
	}

	//@Test
	public void saveUserTest() {
		userDetails = new UserDetails();
		userDetails.setName("TestUser");
		userDetails.setEmail("test@test.com");
		userDetails.setPassword("test@123");
		userDetails.setStatus('Y');
		userDetails.setRoleId("ROLE_STD");
		userDetails.setUserId("test");
		userDetails.setAddress("andheri west");
		userDetails.setIsOnline('Y');
		userDetails.setMobile("987654321");
		userDetails.setReason("Added thorough Test Case");

		assertEquals("USer Added ", true, userDetailsDao.saveUser(userDetails));

	}

	@Test
	public void updateUserTest() {
		userDetails = new UserDetails();
		userDetails.setUserId("test");
		userDetails.setName("NewTestName");
		assertEquals("Updated", true,userDetailsDao.updateUser(userDetails));
	}
}
