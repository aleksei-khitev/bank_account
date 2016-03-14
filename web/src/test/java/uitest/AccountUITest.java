package uitest;

import com.codeborne.selenide.WebDriverRunner;
import com.ostdlabs.bank_account.web.conf.SpringWebConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * For UI tests.
 * It's successfully started.
 * TODO: code tests for all operations cases.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringWebConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class AccountUITest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

   @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
       WebDriverRunner.setWebDriver(MockMvcHtmlUnitDriverBuilder.mockMvcSetup(mockMvc).build());
        open("http://localhost:8080/");

    }

    @Test
    public void shouldSeeAccountList() {
        $("badgeName").has(text("Bank Account Management"));
    }
}
