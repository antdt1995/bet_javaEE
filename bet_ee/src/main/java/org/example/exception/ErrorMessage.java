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
    public static final String KEY_POSITIVE_MESSAGE = "exception.score.positive";
    public static final String POSITIVE_MESSAGE = "Score must be positive";
    public static final String FUTURE_OR_PRESENT = "Start date must be present or future";
    public static final String KEY_FUTURE_OR_PRESENT = "exception.date.wrong";


    private ErrorMessage() {
    }

    static Map<String, String> errorKeyAndMessageMap() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(POSITIVE_MESSAGE,KEY_POSITIVE_MESSAGE);
        errorMap.put( FUTURE_OR_PRESENT,KEY_FUTURE_OR_PRESENT);


        return errorMap;
    }
}
