package lv.ctco.tpl.bff;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationContextTest {

    @Autowired ApplicationContext context;

    @Test
    public void applicationContext() {
        assertNotNull(context);
    }
}
