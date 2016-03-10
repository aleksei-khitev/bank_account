package uitest;

import com.ostdlabs.bank_account.web.conf.SpringWebConfiguration;
import conf.TestServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class AccountUITest {


    @Before
    public void setUp() throws Exception {
        TestServer.start();
        open("http://localhost:8080/");

    }

    @Test
    public void shouldSeeAccountList() {
        $(By.name("user.name")).setValue("johny");
    }
}
