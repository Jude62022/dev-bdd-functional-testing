package com.dso.java.bdd;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevBddFunctionalTestingApplication.class)
public class DevBddFunctionalTestingApplicationTests {

	@Test
	public void contextLoads() {
		assertTrue(true);
	}

	@Test
	public void test() {
		DevBddFunctionalTestingApplication.main(new String[] {});
	}

}
