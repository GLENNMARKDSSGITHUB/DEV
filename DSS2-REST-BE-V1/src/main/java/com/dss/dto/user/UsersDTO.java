/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */


package com.dss.dto.user;

import com.dss.util.annotations.*;
import com.dss.util.utils.CommonStringUtility;
import lombok.*;

import java.util.Date;
/**
 * This class is a Data Transfer Object for UsersDTO
 */

@Getter
@Setter
@NoArgsConstructor
public class UsersDTO {

    private String dssUserId;

    @DssNullValidator(message = CommonStringUtility.ERR_CODE_001_REQ_FIRSTNAME)
    @DssCharNumValidator(message = CommonStringUtility.ERR_CODE_001_ALPHABET_ALLOWED)
    private String firstName;

    @DssNullValidator(message = CommonStringUtility.ERR_CODE_001_REQ_LASTNAME)
    @DssCharNumValidator(message = CommonStringUtility.ERR_CODE_001_ALPHABET_ALLOWED)
    private String lastName;

    @DssEmailValidator(message = CommonStringUtility.ERR_CODE_001_INVALID_EMAIL)
    @DssEmailTakenValidator(message = CommonStringUtility.ERR_CODE_001_EMAIL_TAKEN)
    private String email;

    @DssNullValidator(message = CommonStringUtility.ERR_CODE_001_REQ_PASSWORD )
    @DssPasswordValidator(message = CommonStringUtility.ERR_CODE_001_PASSWORD_ALLOWED)
    private String password;

    private String status;

    @DssNullValidator(message = CommonStringUtility.ERR_CODE_001_REQ_CELLPHONE_NO)
    @DssPhoneNoTakenValidator(message = CommonStringUtility.ERR_CODE_001_CELL_NO_TAKEN)
    private String cellphoneNumber;

    private Date creationDate;

    private String createdBy;

    private Date lastModificationDate;

    private String lastModifiedBy;

    public UsersDTO(String dssUserId, String firstName, String lastName, String email, String password, String status, String cellphoneNumber, Date creationDate, String createdBy, Date lastModificationDate, String lastModifiedBy) {
        this.dssUserId = dssUserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.cellphoneNumber = cellphoneNumber;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastModificationDate = lastModificationDate;
        this.lastModifiedBy = lastModifiedBy;
    }
}
