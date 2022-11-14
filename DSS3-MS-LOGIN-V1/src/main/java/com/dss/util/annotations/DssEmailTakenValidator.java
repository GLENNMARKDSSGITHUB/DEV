/**
 * This validator interface checks if the email id is already in use by some other users
 * @package com.dss.util.validators
 * @interface DssEmailTakenValidator
 * @author Glen Mark T Anduiza
 */

package com.dss.util.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DssEmailTakenValidatorImpl.class)
public @interface DssEmailTakenValidator {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
