/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.util.utils;

/**
 * This is a CommonStringUtility Class. It contains all string messages used in the project.
 */

public class CommonStringUtility {

    /*==========================CUSTOM LOGIN ERROR MESSAGES FOR LOGIN ========================*/
    public static final String  ERR_CODE_INACTIVE_SUSPENDED = "Inactive/Suspended Account.";
    public static final String  ERR_CODE_LOGIN_INCORRECT_PASSWORD = "The password you’ve entered is incorrect.";
    public static final String  ERR_CODE_LOGIN_EMAIL_NOT_CONNECTED = "The email you entered isn’t connected to an account.";


    /*=================================CUSTOM REGEX PATTERNS==================================*/
    public static final String REGEX_PATTERN_SPECIAL_CHAR_NUM = "[^a-zA-Z\\s+]";
    public static final String REGEX_PATTERN_EMAIL = ".+@.+\\..+";
    public static final String REGEX_PATTERN_PWD = "^.*(?=.{8,})(?=.*\\d.*\\d)(?=.*[a-z].*[a-z])(?=.*[A-Z].*[A-Z])(?=.*[!@#$%^&*+=].*[!@#$%^&*+=]).*$";

    /*========================CUSTOMIZED ERROR MESSAGE FOR ADD REGISTRATION====================*/
    public static final String  ERR_CODE_001_REQ_FIRSTNAME = "First name is mandatory";
    public static final String  ERR_CODE_001_REQ_LASTNAME = "Last name is mandatory";
    public static final String  ERR_CODE_001_REQ_PASSWORD = "Password is mandatory";
    public static final String  ERR_CODE_001_REQ_CELLPHONE_NO = "Cellphone number is mandatory";
    public static final String ERR_CODE_001_CELL_NO_TAKEN = "Cellphone number has already been taken.";
    public static final String ERR_CODE_001_EMAIL_TAKEN = "Email address has already been taken.";
    public static final String ERR_CODE_001_ALPHABET_ALLOWED = "Only alphabetical characters allowed.";
    public static final String ERR_CODE_001_INVALID_EMAIL = "Please enter a valid email.";
    public static final String ERR_CODE_001_PASSWORD_ALLOWED = "Password must have at least an uppercase and lowercase alphabet, a digit and a special character";

    /*========================CUSTOMIZED ERROR MESSAGE FOR DISPLAY RECORDS====================*/
    public static final String ERR_MSG_NO_DISPLAY_RECORDS = "There are no records to display.";

    /*========================CUSTOMIZED SUCCESS MESSAGE FOR REGISTRATION====================*/
    public static final String SUCCESS_MSG_CREATE_ACCT = "Congratulations! Your account has been created successfully.";

    /*========================CUSTOMIZED SUCCESS MESSAGE FOR MOVIE CATALOGUE====================*/
    public static final String SUCCESS_MSG_ADD_MOVIE = "The movie has been created successfully.";
    public static final String SUCCESS_MSG_UPDATE_MOVIE = "The movie title %s has been updated successfully.";
    public static final String SUCCESS_MSG_DELETE_MOVIE = "The movie title %s has been deleted successfully.";

    /*========================CUSTOMIZED ERROR MESSAGE FOR MOVIE CATALOGUE====================*/
    public static final String ERR_MSG_MOVIE_EXIST = "Movie title %s already existing!";
    public static final String ERR_MSG_MOVIE_NOT_EXIST = "This movie does not exist!";

    /*========================CUSTOMIZED SUCCESS MESSAGE FOR ACTOR====================*/
    public static final String SUCCESS_MSG_ADD_ACTOR = "Actor name %s has been added to the movie.";
    public static final String SUCCESS_MSG_UPDATE_ACTOR = "Actor name %s has been updated successfully.";
    public static final String SUCCESS_MSG_DELETE_ACTOR = "Actor name %s has been deleted successfully.";

    /*========================CUSTOMIZED ERROR MESSAGE FOR ACTOR====================*/
    public static final String ERR_MSG_ACTOR_EXIST = "Actor name %s already existing!";
    public static final String ERR_MSG_ACTOR_NOT_EXIST = "This actor does not exist!";

    /*========================CUSTOMIZED SUCCESS MESSAGE FOR REVIEWS====================*/
    public static final String SUCCESS_MSG_ADD_REVIEW = "Your review has been added to the movie.";

    /*======================== STR ID'S FOR MOVIE, ACTOR AND REVIEW ====================*/
    public static final String DSS_USER_ID = "US%s";
    public static final String DSS_MOVIE_ID = "DSS%s";
    public static final String DSS_ACTOR_ID = "AC%s";
    public static final String DSS_REVIEW_ID = "RT%s";
}
