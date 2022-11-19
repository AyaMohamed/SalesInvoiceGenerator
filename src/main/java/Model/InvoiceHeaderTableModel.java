/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aya.Mohamed
 */
public class InvoiceHeaderTableModel extends AbstractTableModel{
    
    
    String [] columns={"No.", "Date", "Customer", "Total"};
    private ArrayList<InvoiceHeader> invoiceHeader;
    
    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> invoiceHeader)
    {
        this.invoiceHeader=invoiceHeader;
    }
    
    @Override
    public int getRowCount()
    {
        return invoiceHeader.size();
    }
    
    @Override
    public int getColumnCount()
    {
        return columns.length;
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    @Override
    public Object getValueAt(int row, int col)
    {
        InvoiceHeader header=invoiceHeader.get(row); //1,20-11-2020,Ali
        switch(col)
        {
            case 0:
                return header.getNum();
            case 1:
                return header.getDate();
            case 2:
                return header.getCustomerName();
            case 3:
                return header.getTotal();
            default:
                return "";
        }
        
    }
}
