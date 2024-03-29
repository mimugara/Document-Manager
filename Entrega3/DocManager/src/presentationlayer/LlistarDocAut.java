package presentationlayer;

import java.util.ArrayList;

import utils.exceptions.document.AutorNoExisteixException;

public class LlistarDocAut extends javax.swing.JFrame {
    /**
     * CtrlPresentacio instance
     */
    CtrlPresentacio iCtrlPresentacio;
    /**
     * Inici Button for going back to the main menu
     */
    private javax.swing.JButton buttonInici;
    /**
     * Mostrar Button for showing the documents
     */    
    private javax.swing.JButton buttonMostrar;
    /**
     * Panel that contains the visible components
     */     
    private javax.swing.JPanel jPanel1;
    /**
     * Permits scrolling the text area where the documents are shown
     */      
    private javax.swing.JScrollPane jScrollPane1;
    /**
     * Non-editable text 
     */      
    private javax.swing.JLabel labelMostrar;
    /**
     * Non-editable text 
     */
    private javax.swing.JLabel labelAutor;
    /**
     * Label with the state
     */
    private javax.swing.JLabel labelEstat;
    /**
     * Editable text 
     */      
    private javax.swing.JTextArea textContingut;
    /**
     * Editable text 
     */   
    private javax.swing.JTextField textAutor;

    /**
     * Constructor of the secondary view LlistarDocAut
     * @param pCtrlPresentacio CtrlPresentacio instance
     */

    public LlistarDocAut(CtrlPresentacio pCtrlPresentacio) {
        iCtrlPresentacio = pCtrlPresentacio;
        initComponents();
    }
    /**
     * Makes the secondary view visible
     */    
                     
    public void ferVisible() {
        this.setVisible(true);
    }
    /**
     * Makes the secondary view invisible
     */
    public void ferInvisible() {
        this.setVisible(false);
    }
    /**
     * Initializes the components of the secondary view LlistarDocAut
    */
    private void initComponents() {

        labelMostrar = new javax.swing.JLabel();
        buttonInici = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textContingut = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        textAutor = new javax.swing.JTextField();
        labelAutor = new javax.swing.JLabel();
        buttonMostrar = new javax.swing.JButton();
        labelEstat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setPreferredSize(new java.awt.Dimension(1600, 900));

        labelMostrar.setText("LLISTAR DOCUMENTS PER AUTOR");

        buttonInici.setText("Inici");
        buttonInici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIniciActionPerformed(evt);
            }
        });

        textContingut.setEditable(false);
        textContingut.setColumns(20);
        textContingut.setRows(5);
        jScrollPane1.setViewportView(textContingut);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAutorActionPerformed(evt);
            }
        });

        textAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAutorActionPerformed(evt);
            }
        });

        labelAutor.setText("Autor:");

        buttonMostrar.setText("Mostrar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelAutor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 504, Short.MAX_VALUE)
                .addComponent(buttonMostrar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelAutor)
                            .addComponent(textAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        ))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(buttonMostrar)))
                .addGap(14, 14, 14))
        );

        textContingut.setEditable(false);
        textContingut.setColumns(20);
        textContingut.setRows(5);
        jScrollPane1.setViewportView(textContingut);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonInici)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelEstat, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelMostrar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelMostrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonInici, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEstat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pack();
    }                     
    /**
     * List the documents corresponding to the author entered when the Mostrar or Enter button is pressed.
     * @param evt Event that triggers the action.
     * 
     */ 
    private void textAutorActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String autor = textAutor.getText();
        ArrayList<String> llistatTitols;
        try {
            llistatTitols = iCtrlPresentacio.getllistatTitols(autor);
        } catch (AutorNoExisteixException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getType());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getType() + ": " + e.getMessage());
            textContingut.setText("");

            return;
        }

        String convertirArray = convertirArray(llistatTitols);
        textContingut.setText(convertirArray);
        labelEstat.setText("");

        
    }
    /**
     * Converts an array of authors 'a' to a String where the titles of the documents are listed with the corresponding line breaks.
     * @param a Array of authors.
     * 
     */   
    private String convertirArray(ArrayList<String> a) {
        String sol = "";
        for(String tit : a) {
            sol += tit + System.lineSeparator();
        } 
        return sol;
    }
    /**
     * Returns to the main view by deleting the content of 'textcontingut'
     * @param evt Event that triggers the action.
     * 
     */
    private void buttonIniciActionPerformed(java.awt.event.ActionEvent evt) {                                            
        iCtrlPresentacio.sincronitzarLlistDocAutAVistaPrincipal();
        textContingut.setText("");
        textAutor.setText("");
        labelEstat.setText("");
    }                                                          
}