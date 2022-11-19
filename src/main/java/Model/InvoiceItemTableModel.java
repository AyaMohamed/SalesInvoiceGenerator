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
public class InvoiceItemTableModel extends AbstractTableModel {
    
    
    String [] columns={"No.", "Item Name", "Item Price", "Count", "Item Total"};
    private ArrayList<InvoiceItem> invoiceItems;
    
    public InvoiceItemTableModel(ArrayList<InvoiceItem> invoiceItems)
    {
        this.invoiceItems=invoiceItems;
    }
    
    @Override
    public int getRowCount()
    {
        return invoiceItems.size();
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
        InvoiceItem item=invoiceItems.get(row); //1,Mobile,3200,1
        switch(col)
        {
            case 0:
                return item.getInvoiceHeader().getNum();
            case 1:
                return item.getItemName();
            case 2:
                return item.getPrice();
            case 3:
                return item.getCount();
            case 4: 
                return item.getTotal();
            default:
                return "";
        }
        
    }
    
}
