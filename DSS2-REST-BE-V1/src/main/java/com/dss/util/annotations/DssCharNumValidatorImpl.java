/**
 * This validator class checks if the username contains number or special characters
 * @package com.dss.util.validators
 * @class DssCharNumValidatorImpl
 * @author Glen Mark T Anduiza
 */

package com.dss.util.annotations;

import com.dss.util.utils.CommonStringUtility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class DssCharNumValidatorImpl implements ConstraintValidator<DssCharNumValidator, String> {

    /**
     * returns a boolean value if the username contains number or special characters
     * @param name user's name
     * @param constraintValidatorContext is an ConstraintValidatorContext interface
     * @return a boolean value
     * @see #isValid(String ,ConstraintValidatorContext)
     */

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !Pattern.compile(CommonStringUtility.REGEX_PATTERN_SPECIAL_CHAR_NUM , Pattern.CASE_INSENSITIVE)
                .matcher(name).find();
    }
}
