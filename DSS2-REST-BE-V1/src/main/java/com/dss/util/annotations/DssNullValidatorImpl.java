/**
 * This validator class checks if the field is null or not.
 * @package com.dss.util.validators
 * @class DssNullValidatorImpl
 * @author Glen Mark T Anduiza
 */

package com.dss.util.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class DssNullValidatorImpl implements ConstraintValidator<DssNullValidator, String> {

    /**
     * returns a boolean value if field is null/empty or not.
     * @param field user's field
     * @param constraintValidatorContext is an ConstraintValidatorContext interface
     * @return a boolean value
     * @see #isValid(String ,ConstraintValidatorContext)
     */

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return !field.isEmpty();
    }
}
