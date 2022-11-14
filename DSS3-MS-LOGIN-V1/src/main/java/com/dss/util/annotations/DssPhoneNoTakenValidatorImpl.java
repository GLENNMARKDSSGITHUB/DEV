/**
 * This validator class checks if the cellphone number is
 * already in use by some other users
 * @package com.dss.util.validators
 * @class DssPhoneNoTakenValidatorImpl
 * @author Glen Mark T Anduiza
 */

package com.dss.util.annotations;

import com.dss.entity.user.Users;
import com.dss.repository.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class DssPhoneNoTakenValidatorImpl implements ConstraintValidator<DssPhoneNoTakenValidator, String> {

    @Autowired
    private UsersRepository userRepository;

    /**
     * returns a boolean value if the cellphone number is
     * already in use by some other users
     * @param phoneNo user's cellphone number
     * @param constraintValidatorContext is an ConstraintValidatorContext interface
     * @return a boolean value
     * @see #isValid(String ,ConstraintValidatorContext)
     */

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext constraintValidatorContext) {
        List<Users> userCellphoneNoList = userRepository.findUserByCellphoneNumber(phoneNo);
        return userCellphoneNoList.isEmpty();
    }
}
