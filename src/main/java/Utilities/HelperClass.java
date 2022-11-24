/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Aya.Mohamed
 */
public class HelperClass {

    public boolean tryParseInteger(String num) {
        boolean result;

        try {
            int integerNumber = Integer.parseInt(num);
            result = true;
        } catch (NumberFormatException ex) {
            /*JOptionPane.showMessageDialog(newInvoiceHeaderPage, "Please Enter a valid positive number",
           "Invalid Number!!", JOptionPane.ERROR_MESSAGE);*/
            result = false;

        }
        return result;
    }

    public boolean tryParseDouble(String num) {
        boolean result;

        try {
            double doubleNumber = Double.parseDouble(num);
            result = true;
        } catch (NumberFormatException ex) {
            /*JOptionPane.showMessageDialog(newInvoiceHeaderPage, "Please Enter a valid positive number",
                    "Invalid Number!!", JOptionPane.ERROR_MESSAGE);
             */
            result = false;

        }
        return result;
    }
    
    public boolean validateString(String str)//validate string has special characters (false means string is valid)
    {
        boolean result = false;
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        Matcher matcher = pattern.matcher(str);
        result = matcher.find();

        return result;
    }


    public boolean tryParseStringToDate(String dateString)
    {
        //SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date=new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;

    }

}
