package org.example.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {
    public static final String ACCOUNT_NOT_FOUND_MSG_KEY = "AccountNotExisted";
    public static final String ACCOUNT_NOT_FOUND_MSG = "Account Not Found";
    public static final String HOUSE_NOT_FOUND_MSG_KEY = "HouseNotExisted";
    public static final String HOUSE_NOT_FOUND_MSG = "House Not Found";
    public static final String Transaction_NOT_FOUND_MSG_KEY = "TransactionNotExisted";
    public static final String Transaction_NOT_FOUND_MSG = "Transaction Not Found";
    public static final String ODD_NOT_FOUND_MSG_KEY = "OddNotExisted";
    public static final String ODD_NOT_FOUND_MSG = "Odd Not Found";
    public static final String ODD_TYPE_NOT_FOUND_MSG_KEY = "OddTypeNotExisted";
    public static final String ODD_TYPE_NOT_FOUND_MSG = "Odd Type Not Found";
    public static final String ASSIGN_NOT_FOUND_MSG_KEY = "AssignNotExisted";
    public static final String ASSIGN_NOT_FOUND_MSG = "Assign Not Found";
    public static final String INVOICE_NOT_FOUND_MSG_KEY = "InvoiceNotExisted";
    public static final String INVOICE_NOT_FOUND_MSG = "Invoice Not Found";
    public static final String INVOICE_DETAIL_NOT_FOUND_MSG_KEY = "InvoiceDetailNotExisted";
    public static final String INVOICE_DETAIL_NOT_FOUND_MSG = "Invoice Detail Not Found";
    public static final String CUSTOMER_NOT_FOUND_MSG_KEY = "CustomerNotExisted";
    public static final String CUSTOMER_NOT_FOUND_MSG = "Customer Not Found";
    public static final String FOOTBALL_TEAM_NOT_FOUND_MSG_KEY = "FootballTeamNotExisted";
    public static final String FOOTBALL_TEAM_NOT_FOUND_MSG = "Football Team Not Found";
    public static final String FOOTBALL_MATCH_NOT_FOUND_MSG_KEY = "FootballMatchNotExisted";
    public static final String FOOTBALL_MATCH_NOT_FOUND_MSG = "Football Match Not Found";
    public static final String KEY_POSITIVE_MESSAGE = "exception.number.positive";
    public static final String POSITIVE_MESSAGE = "Must be positive number";
    public static final String FUTURE_OR_PRESENT = "Start date must be present or future";
    public static final String KEY_FUTURE_OR_PRESENT = "exception.date.wrong";

    public static final String USERNAME_INVALID="Username is invalid";
    public static final String KEY_USERNAME_INVALID="exception.username.invalid";

    public static final String KEY_PASSWORD_NULL_OR_BLANK="exception.password.invalid";
    public static final String PASSWORD_NULL_OR_BLANK="Password cannot be null or blank";
    public static final String KEY_UNAUTHORIZED_ACCESS="exception.unauthorized.access";
    public static final String UNAUTHORIZED_ACCESS="Unauthorized Access";
    public static final String KEY_FORBIDDEN_ACCESS="exception.forbidden.access";
    public static final String FORBIDDEN_ACCESS="Forbidden Access";
    public static final String AMOUNT_OVER_BALANCE_MSG_KEY = "AmountOverBalance";
    public static final String AMOUNT_OVER_BALANCE_MSG = "Amount Over Balance";
    public static final String INVALID_BET_AMOUNT_MSG_KEY = "InvalidBetAmount";
    public static final String INVALID_BET_AMOUNT_MSG = "Bet Amount must be greater than 0";
    public static final String ODD_EXPIRED_MSG_KEY = "OddExpired";
    public static final String ODD_EXPIRED_MSG = "Odd Expired";

    private ErrorMessage() {
    }

    static Map<String, String> errorKeyAndMessageMap() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(POSITIVE_MESSAGE,KEY_POSITIVE_MESSAGE);
        errorMap.put( FUTURE_OR_PRESENT,KEY_FUTURE_OR_PRESENT);
        errorMap.put(USERNAME_INVALID,KEY_USERNAME_INVALID);
        errorMap.put(PASSWORD_NULL_OR_BLANK,KEY_PASSWORD_NULL_OR_BLANK);
        return errorMap;
    }
}
