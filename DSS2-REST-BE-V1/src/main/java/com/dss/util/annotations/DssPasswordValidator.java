/**
 * This validator interface checks if password is a combination of at least
 * an uppercase and lowercase alphabet, a digit and a spacial character
 * @package com.dss.util.validators
 * @interface DssPasswordValidator
 * @author Glen Mark T Anduiza
 */

package com.dss.util.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DssPasswordValidatorImpl.class)
public @interface DssPasswordValidator {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
