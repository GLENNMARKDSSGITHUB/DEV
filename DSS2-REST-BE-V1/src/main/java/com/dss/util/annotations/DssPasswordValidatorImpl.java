/**
 * This validator class checks if password is a combination of at least
 * an uppercase and lowercase alphabet, a digit and a spacial character
 * @package com.dss.util.validators
 * @class DssPasswordValidator
 * @author Glen Mark T Anduiza
 */

package com.dss.util.annotations;

import com.dss.util.utils.CommonStringUtility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class DssPasswordValidatorImpl implements ConstraintValidator<DssPasswordValidator, String> {

    /**
     * returns a boolean value if password is a combination of at least
     * an uppercase and lowercase alphabet, a digit and a spacial character
     * @param password user's password
     * @param constraintValidatorContext is an ConstraintValidatorContext interface
     * @return a boolean value
     * @see #isValid(String ,ConstraintValidatorContext)
     */

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.compile(CommonStringUtility.REGEX_PATTERN_PWD, Pattern.CASE_INSENSITIVE)
                .matcher(password).find();
    }
}
