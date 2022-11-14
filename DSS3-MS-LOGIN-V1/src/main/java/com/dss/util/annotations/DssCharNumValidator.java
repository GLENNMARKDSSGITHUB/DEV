/**
 * This validator interface checks if the username contains number or special characters
 * @package com.dss.util.validators
 * @interface DssCharNumValidator
 * @author Glen Mark T Anduiza
 */

package com.dss.util.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DssCharNumValidatorImpl.class)
public @interface DssCharNumValidator {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
