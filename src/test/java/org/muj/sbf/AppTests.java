package org.muj.sbf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.muj.sbf.App;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class AppTests {

	@Test
	public void contextLoads() {
	}

}
