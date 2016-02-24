package com.ostdlabs.bank_account.db.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.*;

/**
 * Определяет, что бин относится к работе с
 * базой данных ORGANIZATIONS
 */
@Target( { ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier
public @interface Organizations {
}
