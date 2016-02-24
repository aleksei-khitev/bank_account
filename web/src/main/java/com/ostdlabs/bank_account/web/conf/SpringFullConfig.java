package com.ostdlabs.bank_account.web.conf;

import org.springframework.context.annotation.Import;
import ru.bn.subscription_counter.core.config.CoreSpringConfig;
import ru.bn.subscription_counter.db.config.DBSpringConfig;

@Import({DBSpringConfig.class, CoreSpringConfig.class})
public class SpringFullConfig {
}
