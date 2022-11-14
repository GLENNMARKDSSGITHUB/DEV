/**
 * This validator interface checks if the email is valid or not.
 * @package com.dss.util.validators
 * @interface DssEmailValidator
 * @author Glen Mark T Anduiza
 */

package com.dss.util.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DssEmailValidatorImpl.class)
public @interface DssEmailValidator {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
