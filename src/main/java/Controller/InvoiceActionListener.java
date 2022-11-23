/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InvoiceHeader;
import Model.InvoiceHeaderTableModel;
import Model.InvoiceItem;
import Model.InvoiceItemTableModel;
import Utilities.HelperClass;
import View.InvoiceForm;
import View.NewInvoiceHeaderPage;
import View.NewInvoiceItemPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Aya.Mohamed
 */
public class InvoiceActionListener implements ActionListener, ListSelectionListener {

    private static InvoiceForm form;
    private static NewInvoiceHeaderPage newInvoiceHeaderPage;
    private static NewInvoiceItemPage newInvoiceItemPage;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static HelperClass helperClass;

    public InvoiceActionListener(InvoiceForm form) {
        this.form = form;
        helperClass=new HelperClass();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Load File":
                System.out.println("Load File Action");
                loadFile("", "");
                break;

            case "Save File":
                System.out.println(e.getActionCommand());
                saveFile();
                break;

            case "New Invoice Header":
                System.out.println(e.getActionCommand());
                addNewInvoiceHeader();
                break;

            case "Delete Invoice":
                System.out.println(e.getActionCommand());
                deleteInvoiceHeader();
                break;

            case "Delete Item":
                System.err.println(e.getActionCommand());
                deleteInvoiceItem();
                break;

            case "Ok Invoice Header":
                System.out.println(e.getActionCommand());
                okInvoiceHeader();
                break;

            case "New Invoice Item":
                System.out.println(e.getActionCommand());
                addNewInvoiceItem();
                break;

            case "Ok Invoice Item":
                System.out.println(e.getActionCommand());
                okInvoiceItem();
                break;
                
            case "Cancel Invoice Header":
                cancelInvoiceHeader();
                break;

            case "Cancel Invoice Item":
                cancelInvoiceItem();
                break;
        }

    }

    public void loadFile(String invoiceHeaderPath, String invoiceItemPath) {
        try {
            File invoiceHeaderFile = null, invoiceItemFile = null;
            List<String> invoiceHeaderList = null; //the list that will hold invoice header row in the excel sheet
            List<String> invoiceItemList = null; //the list that will hold invoice line row in the excel sheet
            JFileChooser fileChooser = new JFileChooser();
            

            if (invoiceHeaderPath == "" && invoiceItemPath == "")//if file path is not sent in the main function
            {
                int result = fileChooser.showOpenDialog(form);
                if (result == JFileChooser.APPROVE_OPTION) {
                    invoiceHeaderFile = fileChooser.getSelectedFile();
                    result = fileChooser.showOpenDialog(form);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        invoiceItemFile = fileChooser.getSelectedFile();
                    }
                }
            } else {
                invoiceHeaderFile = new File(invoiceHeaderPath);
                invoiceItemFile = new File(invoiceItemPath);
            }

            invoiceHeaderList = Files.readAllLines(Paths.get(invoiceHeaderFile.getAbsolutePath()));
            invoiceItemList = Files.readAllLines(Paths.get(invoiceItemFile.getAbsolutePath()));

            for (int i = 0; i < invoiceHeaderList.size(); i++) {
                String[] invoiceHeaderArray = invoiceHeaderList.get(i).split(",");
                int num = Integer.parseInt(invoiceHeaderArray[0]);
                //Date date= InvoiceForm.simpleDateFormat.parse(invoiceHeaderArray[1]);
                String customerName = invoiceHeaderArray[2];
                String date = invoiceHeaderArray[1];
                InvoiceHeader header = new InvoiceHeader(num, customerName, date);
                form.getInvoiceHeader().add(header);
            }

            InvoiceHeader header = null;
            for (int i = 0; i < invoiceItemList.size(); i++) {
                String[] invoiceItemArray = invoiceItemList.get(i).split(",");
                int num = Integer.parseInt(invoiceItemArray[0]);
                String itemName = invoiceItemArray[1];
                double price = Double.parseDouble(invoiceItemArray[2]);
                int count = Integer.parseInt(invoiceItemArray[3]);

                for (int j = 0; j < form.getInvoiceHeader().size(); j++) {
                    if (form.getInvoiceHeader().get(j).getNum() == num) {
                        header = form.getInvoiceHeader().get(j);
                    }
                }
                InvoiceItem invoiceItem = new InvoiceItem(itemName, price, count, header);
                header.getInvoiceItem().add(invoiceItem);
            }

            form.getInvoiceHeaderTable().setModel(new InvoiceHeaderTableModel(form.getInvoiceHeader()));
            //form.getInvoiceItemTabelModel().set(new InvoiceItemTableModel(form.getInvoiceItem()));
            form.getInvoiceHeaderTableModel().fireTableDataChanged();
            form.setInvoiceHeaderTableModel(form.getInvoiceHeaderTableModel());

        } catch (UncheckedIOException e) {
            JOptionPane.showMessageDialog(form, "Invalid File Format, only CSV files are allowed",
                    "Invalid File Format", JOptionPane.ERROR_MESSAGE);

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(form, "No files are chosen",
                    "No File found!!", JOptionPane.ERROR_MESSAGE);

        } catch (IOException ex) {
            Logger.getLogger(InvoiceActionListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void saveFile() {
        //1. get data in invoice header table into an arraylist
        //2. separate each value with a comma (1,Ali,20-10-2020)
        //3. get invoice item data in invoice header arraylist table into a string
        //4. separate each value with a comma (1,mobile,3200,1)
        //5. open save dialog to select a path for the file
        //6. write into file
        //7. flush
        //8. close

        ArrayList<InvoiceHeader> invoiceHeaders = form.getInvoiceHeader();
        String allHeaders = "";
        String allItems = "";
        if (invoiceHeaders.size() < 1) {
            JOptionPane.showMessageDialog(form, "No invoice headers found to be saved",
                    "No Headers exist", JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i = 0; i < invoiceHeaders.size(); i++) {
                String temp = invoiceHeaders.get(i).convertToCsv();
                allHeaders += temp;

                for (InvoiceItem invoiceItem : invoiceHeaders.get(i).getInvoiceItem()) {
                    String temp2 = invoiceItem.convertToCsv();
                    allItems += temp2;
                }
            }

            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select where to save the header file");
                int result = fileChooser.showSaveDialog(form);

                if (result == JFileChooser.APPROVE_OPTION) {

                    File invoiceHeaderFile = fileChooser.getSelectedFile();
                    FileWriter headerFileWriter = new FileWriter(invoiceHeaderFile);
                    headerFileWriter.write(allHeaders);
                    headerFileWriter.flush();
                    headerFileWriter.close();
                    JOptionPane.showMessageDialog(form, "Header File saved successfully!");
                    result = fileChooser.showSaveDialog(form);
                    fileChooser.setDialogTitle("Select where to save the items file");
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File invoiceItemFile = fileChooser.getSelectedFile();
                        FileWriter itemFileWriter = new FileWriter(invoiceItemFile);
                        itemFileWriter.write(allItems);
                        itemFileWriter.flush();
                        itemFileWriter.close();
                        JOptionPane.showMessageDialog(form, "Items File saved successfully!");
                    }

                }
            } catch (IOException e) {
                Logger.getLogger(InvoiceActionListener.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selected = form.getInvoiceHeaderTable().getSelectedRow();

        if (selected != -1) {
            InvoiceHeader invoiceHeader = form.getInvoiceHeader().get(selected);
            int invoiceNum = invoiceHeader.getNum();
            String date = invoiceHeader.getDate();
            String customerName = invoiceHeader.getCustomerName();
            double total = invoiceHeader.getTotal();

            form.getInvoiceNumLbl().setText(String.valueOf(invoiceNum));
            form.getInvoiceDateLbl().setText(date);
            form.getCustomerNameLbl().setText(customerName);
            form.getInvoiceTotalLbl().setText(String.valueOf(total));

            ArrayList<InvoiceItem> invoiceItems = invoiceHeader.getInvoiceItem();
            InvoiceItemTableModel invoiceItemTableModel = new InvoiceItemTableModel(invoiceItems);
            form.getInvoiceItemTable().setModel(invoiceItemTableModel);
            invoiceItemTableModel.fireTableDataChanged();
        }

    }

    //click on "Create Invoice" button from the main InvoiceForm page, to open a new frame to enter the new invoice's fields (num, customern name and date
    public NewInvoiceHeaderPage addNewInvoiceHeader() {
        ArrayList<InvoiceHeader> invoiceHeaders = form.getInvoiceHeader();
        if (invoiceHeaders.size() < 1) {
            JOptionPane.showMessageDialog(form, "No invoice headers found, please add load invoice header first",
                    "No Headers exist", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            newInvoiceHeaderPage = new NewInvoiceHeaderPage(form);
            newInvoiceHeaderPage.setVisible(true);
            int num;
            num = invoiceHeaders.get(invoiceHeaders.size() - 1).getNum() + 1;
            newInvoiceHeaderPage.getInvoiceNumTxt().setText(String.valueOf(num));
            return new NewInvoiceHeaderPage(form);
        }
    }

    //click on Ok button to add invoice header fields into invoice header table
    public void okInvoiceHeader() {
        //1. get invoice num, customer name and date from (newInvoiceHeaderPage) object
        //2. save it in an arraylist
        //3. close the form
        //4. add arraylist into invoiceHeaderTableModel
        //5. invoiceHeaderTableModel.fireTableDateChanged();
        try {
            int num = Integer.parseInt(newInvoiceHeaderPage.getInvoiceNumTxt().getText());
            String customerName = newInvoiceHeaderPage.getCustomerNameTxt().getText();
            Date date = newInvoiceHeaderPage.getDateChooser().getDate();
            String dateString = simpleDateFormat.format(date);
            //SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            //System.out.println( sdf.format(newInvoiceHeaderPage.getjDateChooser1().getDate()).toString());
            if (!helperClass.validateString(customerName) && !customerName.isEmpty() == true && !date.toString().isEmpty()) {
                InvoiceHeader invoiceHeader = new InvoiceHeader(num, customerName, dateString);
                newInvoiceHeaderPage.dispose();
                form.getInvoiceHeader().add(invoiceHeader);

                InvoiceHeaderTableModel headerTableModel = new InvoiceHeaderTableModel(form.getInvoiceHeader());
                form.getInvoiceHeaderTable().setModel(headerTableModel);

            } else {
                JOptionPane.showMessageDialog(newInvoiceHeaderPage, "Please Enter a customer name and date",
                        "Missing or invalid data!!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(newInvoiceHeaderPage, "Please Enter a date in a format dd-MM-yyyy",
                    "Missing or invalid date!!", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void deleteInvoiceHeader() {
        //1. get selected invoice header
        //2. delete it from array list
        //3. refresh invoiceHeaderTableModel
        int selected = form.getInvoiceHeaderTable().getSelectedRow();
        if (selected == -1) {
            JOptionPane.showMessageDialog(form, "No rows selected",
                    "No rows selected!!", JOptionPane.ERROR_MESSAGE);

        } else {
            //InvoiceHeader invoiceHeader=form.getInvoiceHeader().get(selected);
            try {
                form.getInvoiceHeader().remove(selected);
                form.getInvoiceHeaderTable().addNotify();//reconfigure enclosing scrollpane.
                form.getInvoiceHeaderTableModel().fireTableDataChanged();

                //delete all invoice items that belongs to this header
                form.getInvoiceNumLbl().setText("");
                form.getInvoiceDateLbl().setText("");
                form.getCustomerNameLbl().setText("");
                form.getInvoiceTotalLbl().setText("");
                InvoiceItemTableModel itemTableModel = new InvoiceItemTableModel(null);
                form.setInvoiceItemTableModel(itemTableModel);
                form.getInvoiceItemTable().setModel(itemTableModel);
                form.getInvoiceItemTable().removeAll();
                form.getInvoiceItemTable().addNotify();
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(form, "No rows selected, please select a row",
                        "No rows selected!!", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    public void deleteInvoiceItem() {
        int selectedHeader = form.getInvoiceHeaderTable().getSelectedRow();
        int selectedItem = form.getInvoiceItemTable().getSelectedRow();

        if (selectedHeader == -1 || selectedItem == -1) {

        } else {
            try {
                InvoiceHeader header = form.getInvoiceHeader().get(selectedHeader);
                header.getInvoiceItem().remove(selectedItem);
                form.getInvoiceHeaderTable().addNotify();
                InvoiceItemTableModel invoiceItemTableModel = new InvoiceItemTableModel(header.getInvoiceItem());
                form.getInvoiceItemTable().setModel(invoiceItemTableModel);
                invoiceItemTableModel.fireTableDataChanged();
                int j = 4;
                double total = 0.0;
                for (int i = 0; i < header.getInvoiceItem().size(); i++) {
                    total += Double.parseDouble(invoiceItemTableModel.getValueAt(i, j).toString());
                }
                form.getInvoiceTotalLbl().setText(String.valueOf(total));
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(form, "No items selected, please select an item",
                        "No rows selected!!", JOptionPane.ERROR_MESSAGE);

            }
        }

    }

    public NewInvoiceItemPage addNewInvoiceItem() {
        try {
            int num = Integer.parseInt(form.getInvoiceNumLbl().getText());
            newInvoiceItemPage = new NewInvoiceItemPage(form);
            newInvoiceItemPage.setVisible(true);
            newInvoiceItemPage.getInvoiceNumTxt().setText(String.valueOf(num));
            //newInvoiceItemPage.setVisible(true);
            return newInvoiceItemPage;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(newInvoiceHeaderPage, "Please select an invoice header to add invoice item for it",
                    "No invoice header selected", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    public void okInvoiceItem() {
        int num = Integer.parseInt(newInvoiceItemPage.getInvoiceNumTxt().getText());
        String itemName = newInvoiceItemPage.getItemNameTxt().getText();
        String priceString = newInvoiceItemPage.getItemPriceTxt().getText();
        String countString = newInvoiceItemPage.getCountTxt().getText();
        int selected = form.getInvoiceHeaderTable().getSelectedRow();
        if (!itemName.isEmpty() && !priceString.isEmpty() && !countString.isEmpty()) {
            if (!helperClass.validateString(itemName) && helperClass.tryParseInteger(countString) && helperClass.tryParseDouble(priceString)) {
                double price = Double.parseDouble(priceString);
                int count = Integer.parseInt(countString);

                if (price > 0 && count > 0) {

                    InvoiceHeader invoiceHeader = form.getInvoiceHeader().get(selected);
                    ArrayList<InvoiceItem> invoiceItems = invoiceHeader.getInvoiceItem();
                    InvoiceItem invoiceItem = new InvoiceItem(itemName, price, count, invoiceHeader);
                    invoiceItems.add(invoiceItem);
                    newInvoiceItemPage.dispose();
                    //form.getInvoiceItem().add(invoiceItem);
                    InvoiceItemTableModel itemTableModel = new InvoiceItemTableModel(invoiceItems);

                    //calculate Invoice total
                    int j = 4;//column index
                    double total = 0;
                    for (int i = 0; i < invoiceItems.size(); i++) //i -> row index
                    {
                        total += Double.parseDouble(itemTableModel.getValueAt(i, j).toString());

                    }
                    form.getInvoiceTotalLbl().setText(String.valueOf(total));

                    form.getInvoiceItemTable().setModel(itemTableModel);
                    form.setInvoiceItemTableModel(itemTableModel);
                    form.getInvoiceItemTabelModel().fireTableDataChanged();
                    form.getInvoiceHeaderTable().addNotify();//to refresh selected invoice header to update total value

                } else {
                    JOptionPane.showMessageDialog(newInvoiceHeaderPage, "Price & count should be greater than 0",
                            "Invalid data!!", JOptionPane.ERROR_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(newInvoiceHeaderPage, "Please Enter valid data",
                        "Invalid data!!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(newInvoiceHeaderPage, "Please Enter all data",
                    "Missing data!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cancelInvoiceHeader()
    {
        newInvoiceHeaderPage.dispose();
    }
    
    public void cancelInvoiceItem()
    {
        newInvoiceItemPage.dispose();
    }

    

    

}
