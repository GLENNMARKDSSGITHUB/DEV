/**
 * This validator interface checks if the cellphone number is
 * already in use by some other users
 * @package com.dss.util.validators
 * @interface DssPhoneNoTakenValidator
 * @author Glen Mark T Anduiza
 */

package com.dss.util.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DssPhoneNoTakenValidatorImpl.class)
public @interface DssPhoneNoTakenValidator {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
