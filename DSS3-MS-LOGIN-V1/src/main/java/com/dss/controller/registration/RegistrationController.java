/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.controller.registration;

import com.dss.dto.UsersDTO;
import com.dss.service.registration.RegistrationService;
import com.dss.util.utils.DssCommonMessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a controller layer for DSS Registrations
 */

@RestController
@RequestMapping("/API/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    /** Returns a DssCommonMessageDetails object if the admin user successfully adds the account registration or not.
     * @param userDto userDto
     * @param result BindingResult
     * @return String
     * @see #registerAccount(UsersDTO, BindingResult)
     */
    @PostMapping("/add-registration.do")
    public DssCommonMessageDetails registerAccount(@Valid @RequestBody UsersDTO userDto, BindingResult result){
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        if(result.hasErrors()){
            List<Object> obj = new ArrayList<>();
            for(ObjectError objErr : result.getAllErrors()){
                obj.add(objErr.getDefaultMessage());
            }
            commonMsgDtl.setSuccess(false);
            commonMsgDtl.setObjList(obj);
        }else{
            commonMsgDtl = registrationService.registerAccount(userDto);
        }
        return commonMsgDtl;
    }

    /** Returns a DssCommonMessageDetails object
     * @return List<Users>
     * @see #displayAccountRegistrations()
     */
    @GetMapping("/display-registrations.do")
    public DssCommonMessageDetails displayAccountRegistrations(){
        return registrationService.displayRegistrations();
    }
}
