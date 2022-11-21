/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.InvoiceActionListener;
import Model.InvoiceHeader;
import Model.InvoiceHeaderTableModel;
import Model.InvoiceItem;
import Model.InvoiceItemTableModel;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 * @author Aya.Mohamed
 */
public class InvoiceForm extends javax.swing.JFrame {

    /**
     * Creates new form InvoiceForm
     */
    public InvoiceForm() {

        controller = new InvoiceActionListener(this);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        invoiceTable = new javax.swing.JTable();
        createInvoiceBtn = new javax.swing.JButton();
        createInvoiceBtn.addActionListener(controller);
        deleteInvoiceBtn = new javax.swing.JButton();
        deleteInvoiceBtn.addActionListener(controller);
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        invoiceNumLbl = new javax.swing.JLabel();
        invoiceDateLbl = new javax.swing.JLabel();
        customerNameLbl = new javax.swing.JLabel();
        invoiceTotalLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        createInvoiceItemBtn = new javax.swing.JButton();
        createInvoiceItemBtn.addActionListener(controller);
        deleteItemBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadFileMenuItem = new javax.swing.JMenuItem();
        loadFileMenuItem.addActionListener(controller);
        saveFileMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Invoices Page");

        invoiceTable.getSelectionModel().addListSelectionListener(controller);
        invoiceTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        invoiceTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        invoiceTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        invoiceTable.setShowGrid(true);
        jScrollPane1.setViewportView(invoiceTable);
        invoiceTable.getAccessibleContext().setAccessibleName("");

        createInvoiceBtn.setText("New Invoice");
        createInvoiceBtn.setActionCommand("New Invoice Header");

        deleteInvoiceBtn.setText("Delete Invoice");
        deleteInvoiceBtn.setToolTipText("");

        jLabel1.setText("Invoice Num");

        jLabel2.setText("Invoice Date");

        jLabel3.setText("Customer Name");

        jLabel4.setText("Invoice Total");

        invoiceNumLbl.setText(".");

        invoiceDateLbl.setText(".");

        customerNameLbl.setText(".");

        invoiceTotalLbl.setText(".");

        itemTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
                }
        ));
        itemTable.setShowGrid(true);
        jScrollPane2.setViewportView(itemTable);

        createInvoiceItemBtn.setText("New Item");
        createInvoiceItemBtn.setActionCommand("New Invoice Item");

        deleteItemBtn.setText("Delete Item");

        fileMenu.setText("File");

        loadFileMenuItem.setText("Load File");
        fileMenu.add(loadFileMenuItem);

        saveFileMenuItem.setText("Save File");
        fileMenu.add(saveFileMenuItem);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(89, 89, 89)
                                                .addComponent(createInvoiceBtn)
                                                .addGap(80, 80, 80)
                                                .addComponent(deleteInvoiceBtn)
                                                .addGap(172, 172, 172)
                                                .addComponent(createInvoiceItemBtn)
                                                .addGap(137, 137, 137)
                                                .addComponent(deleteItemBtn))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(26, 26, 26)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                                                                                .addGap(37, 37, 37)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(customerNameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                                                                        .addComponent(invoiceTotalLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                .addGap(55, 55, 55)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(invoiceNumLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                                                                        .addComponent(invoiceDateLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(invoiceNumLbl))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(invoiceDateLbl))
                                                .addGap(14, 14, 14)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(customerNameLbl))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(invoiceTotalLbl)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(24, 24, 24)
                                                                .addComponent(jLabel4)
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(deleteInvoiceBtn)
                                        .addComponent(createInvoiceBtn)
                                        .addComponent(createInvoiceItemBtn)
                                        .addComponent(deleteItemBtn))
                                .addContainerGap())
        );

        createInvoiceBtn.getAccessibleContext().setAccessibleName("New Invoice Header");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InvoiceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InvoiceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InvoiceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InvoiceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new InvoiceForm().setVisible(true);
                controller.loadFile("InvoiceHeader.csv", "InvoiceLine.csv");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createInvoiceBtn;
    private javax.swing.JButton createInvoiceItemBtn;
    private javax.swing.JLabel customerNameLbl;
    private javax.swing.JButton deleteInvoiceBtn;
    private javax.swing.JButton deleteItemBtn;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel invoiceDateLbl;
    private javax.swing.JLabel invoiceNumLbl;
    private javax.swing.JTable invoiceTable;
    private javax.swing.JLabel invoiceTotalLbl;
    private javax.swing.JTable itemTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem loadFileMenuItem;
    private javax.swing.JMenuItem saveFileMenuItem;
    // End of variables declaration//GEN-END:variables

    private static InvoiceActionListener controller;
    private ArrayList<InvoiceHeader> invoiceHeader;
    private InvoiceHeaderTableModel headerTableModel;
    private InvoiceItemTableModel itemTabelModel;
    private ArrayList<InvoiceItem> invoiceItem;

    public ArrayList<InvoiceHeader> getInvoiceHeader() {
        if (invoiceHeader == null) {
            invoiceHeader = new ArrayList<>();
        }
        return invoiceHeader;
    }

    public void setInvoiceHeader(ArrayList<InvoiceHeader> invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public InvoiceHeaderTableModel getInvoiceHeaderTableModel() {
        if (headerTableModel == null) {
            headerTableModel = new InvoiceHeaderTableModel(getInvoiceHeader());
        }
        return headerTableModel;

    }

    public void setInvoiceHeaderTableModel(InvoiceHeaderTableModel headerTableModel) {
        this.headerTableModel = headerTableModel;
        /*headerTableModel=new InvoiceHeaderTableModel(getInvoiceHeader());
        this.invoiceTable.setModel(headerTableModel);
*/
    }

    public ArrayList<InvoiceItem> getInvoiceItem() {
        if (invoiceItem == null) {
            invoiceItem = new ArrayList<>();
        }
        return invoiceItem;
    }

    public void setInvoiceItem(ArrayList<InvoiceItem> invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    public InvoiceItemTableModel getInvoiceItemTabelModel() {
        return itemTabelModel;
    }

    public void setInvoiceItemTableModel(InvoiceItemTableModel itemTabelModel) {
        this.itemTabelModel = itemTabelModel;
    }

    public JTable getInvoiceHeaderTable() {
        return invoiceTable;
    }

    public JTable getInvoiceItemTable() {
        return itemTable;
    }

    public void setInvoiceItemTable(JTable itemTable) {
        this.itemTable = itemTable;
    }


    public JLabel getInvoiceNumLbl() {
        return invoiceNumLbl;
    }

    public JLabel getInvoiceDateLbl() {
        return invoiceDateLbl;
    }

    public JLabel getCustomerNameLbl() {
        return customerNameLbl;
    }

    public JLabel getInvoiceTotalLbl() {
        return invoiceTotalLbl;
    }
}
