/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InvoiceHeader;
import Model.InvoiceHeaderTableModel;
import Model.InvoiceItem;
import Model.InvoiceItemTableModel;
import View.InvoiceForm;
import View.NewInvoiceHeaderPage;
import View.NewInvoiceItemPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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

    public InvoiceActionListener(InvoiceForm form) {
        this.form = form;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(e.getActionCommand().toString());
        switch (e.getActionCommand()) {
            case "Load File":
                System.out.println("Load File Action");
                loadFile("", "");
                break;

            case "Save File":
                System.out.println(e.getActionCommand());
                break;

            case "New Invoice Header":
                System.out.println(e.getActionCommand().toString());
                addNewInvoiceHeader();
                break;

            case "Delete Invoice":
                System.out.println(e.getActionCommand());
                deleteInvoiceHeader();
                break;

            case "Ok Invoice Header":
                System.out.println(e.getActionCommand().toString());
                okInvoiceHeader();
                break;

            case "New Invoice Item":
                System.out.println(e.getActionCommand().toString());
                addNewInvoiceItem();
                break;

            case "Ok Invoice Item":
                System.out.println(e.getActionCommand().toString());
                okInvoiceItem();
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
        //form.getInvoiceHeaderTable().add
        newInvoiceHeaderPage = new NewInvoiceHeaderPage(form);
        newInvoiceHeaderPage.setVisible(true);
        ArrayList<InvoiceHeader> invoiceHeaders = form.getInvoiceHeader();
        int num;
        num = invoiceHeaders.get(invoiceHeaders.size() - 1).getNum() + 1;
        newInvoiceHeaderPage.getInvoiceNumTxt().setText(String.valueOf(num));
        return new NewInvoiceHeaderPage(form);
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
            if (!validateString(customerName) && !customerName.isEmpty() == true && !date.toString().isEmpty()) {
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
                    "rows selected!!", JOptionPane.ERROR_MESSAGE);

        } else {
            //InvoiceHeader invoiceHeader=form.getInvoiceHeader().get(selected);
            try {
                form.getInvoiceHeader().remove(selected);
                form.getInvoiceHeaderTable().addNotify();//reconfigure enclosing scrollpane.
                form.getInvoiceHeaderTableModel().fireTableDataChanged();

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
                        "rows selected!!", JOptionPane.ERROR_MESSAGE);

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
            if (!validateString(itemName) && tryParseInteger(countString) && tryParseDouble(priceString)) {
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

    public boolean validateString(String str)//validate string has special characters (false means string is valid)
    {
        boolean result = false;
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        Matcher matcher = pattern.matcher(str);
        result = matcher.find();

        return result;
    }

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

}
