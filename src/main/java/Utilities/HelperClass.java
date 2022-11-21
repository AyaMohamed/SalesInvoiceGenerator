/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import javax.swing.JOptionPane;

/**
 *
 * @author Aya.Mohamed
 */
public class HelperClass {
    
    public boolean tryParseInteger(String num)
{
    boolean result;
    
    try
    {
        int integerNumber=Integer.parseInt(num);
        result=true;
    }
    catch(NumberFormatException ex)
    {
        /*JOptionPane.showMessageDialog(newInvoiceHeaderPage, "Please Enter a valid positive number",
           "Invalid Number!!", JOptionPane.ERROR_MESSAGE);*/
        result=false;
   
    }
    return result;
}
    
}
