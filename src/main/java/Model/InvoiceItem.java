/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * @author Aya.Mohamed
 */
public class InvoiceItem {
    private String itemName;
    private double price;
    private int count;
    private InvoiceHeader invoiceHeader;

    public InvoiceItem(String itemName, double price, int count, InvoiceHeader invoiceHeader) {
        this.itemName = itemName;
        this.price = price;
        this.count = count;
        this.invoiceHeader = invoiceHeader;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }


    @Override
    public String toString() {
        return "InvoiceItem{" + "itemName=" + itemName + ", price=" + price + ", count=" + count + '}';
    }


    public double getTotal() {
        double total;
        total = this.getCount() * this.getPrice();
        return total;
    }
    
    public String convertToCsv() {
        return invoiceHeader.getNum() + "," + this.getItemName()+ "," + this.getPrice()+ ","+ this.getCount() + "\n";
    }
}
