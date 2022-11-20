/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Aya.Mohamed
 */
public class InvoiceHeader {
    
    private int num;
    private String customerName;
    private String date;
    private ArrayList<InvoiceItem> invoiceItem;

    public InvoiceHeader(int num, String customerName, String date) {
        this.num = num;
        this.customerName = customerName;
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<InvoiceItem> getInvoiceItem() {
        if(invoiceItem == null)
        {
            invoiceItem=new ArrayList();
        }
        return invoiceItem;
    }

    @Override
    public String toString() {
        return "InvoiceHeader{" + "num=" + num + ", customerName=" + customerName + ", date=" + date + ", invoiceItem=" + invoiceItem + '}';
    }

    public double getTotal()
    {
        double total=0.0;
        for(InvoiceItem item : getInvoiceItem())
        {
            total+=item.getTotal();
        }
        return total;
    }
    
    
}
