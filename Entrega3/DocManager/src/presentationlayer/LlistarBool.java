package presentationlayer;

import java.util.ArrayList;

import domainlayer.model.Document;

public class LlistarBool extends javax.swing.JFrame {
    /**
     * CtrlPresentacio instance
     */
    CtrlPresentacio iCtrlPresentacio;
    /**
     * Buscar Button for searching a document
     */
    private javax.swing.JButton buttonBuscar;
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
     * Panel with parts of the view
     */
    private javax.swing.JPanel jPanel2;
    /**
     * Scroll Panel with parts of the view
     */
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JLabel labelExpr;
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
    private javax.swing.JTextField textExpr;
    
    /**
     * Constructor of the secondary view LlistarBool
     * @param pCtrlPresentacio CtrlPresentacio instance
     */
    public LlistarBool(CtrlPresentacio pCtrlPresentacio) {
        iCtrlPresentacio = pCtrlPresentacio;
        initComponents();
    }

    /**
     * Makes the secondary view LlistarBool visible
     */                     
    public void ferVisible() {
        this.setVisible(true);
    }
    /**
     * Makes the secondary view LlistarBool invisible
     */
    public void ferInvisible() {
        this.setVisible(false);
    }

    /**
     * Initializes the components of the secondary view LlistarBool
     */
    private void initComponents() {

        labelMostrar = new javax.swing.JLabel();
        buttonInici = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        textContingut = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        textExpr = new javax.swing.JTextField();
        labelExpr = new javax.swing.JLabel();
        labelEstat = new javax.swing.JLabel();
        buttonBuscar = new javax.swing.JButton();
        buttonMostrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setPreferredSize(new java.awt.Dimension(1600, 900));

        labelMostrar.setText("LLISTAR PER EXPRESSIÓ BOOLEANA");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));
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

        textExpr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAutorActionPerformed(evt);
            }
        });

        labelExpr.setText("Expressió booleana");

        buttonMostrar.setText("Mostrar documents");

        buttonBuscar.setText("Buscar expressió");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(labelExpr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textExpr, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonBuscar)
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelExpr)
                    .addComponent(textExpr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBuscar))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        textContingut.setPreferredSize(new java.awt.Dimension(100, 100));
        jScrollPane2.setViewportView(textContingut);


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
        );

        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 424, Short.MAX_VALUE)
                        .addComponent(labelMostrar)
                        .addGap(0, 424, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonInici)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelEstat, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonMostrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelMostrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonInici)
                        .addComponent(buttonMostrar))
                    .addComponent(labelEstat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }     
    
    /**
     * List the documents corresponding to the boolean expressions entered when the Mostrar or Enter button is pressed.
     * @param evt Event that triggers the action.
     * 
     */    

    private void textAutorActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String expr = textExpr.getText();
        ArrayList<Document> docs;
        try {
            docs = iCtrlPresentacio.getllistatBool(expr);
        } catch (Exception e) {
            System.err.println(e);
			return;
        }
        String out = convertirArray(docs);
        textContingut.setText(out);
    }

    /**
     * Converts an array of documents 'l' to a String where the documents are listed by title and author, including the corresponding line breaks.
     * @param l Array of documents to be converted.
     * 
     */    
    private String convertirArray(ArrayList<Document> l) {
        String sol = "";
        for(Document d : l) {
            String t = "Títol: " + d.get_titol() + ", ";
            String a = "Autor: " + d.get_autor();
            sol += t + " " + a + System.lineSeparator();
        } 
        return sol;
    }                      
    
    /**
     * Returns to the main view by deleting the content of 'textcontingut'.
     * @param evt Event that triggers the action.
     * 
     */

    private void buttonIniciActionPerformed(java.awt.event.ActionEvent evt) {                                            
        iCtrlPresentacio.sincronitzarLlistarBoolAVistaPrincipal();
        labelEstat.setText("");                                 
        textExpr.setText("");   
        textContingut.setText("");
    }       
    
    /**
     * Shows the tree corresponding to the boolean expression entered when the Buscar button is pressed.
     * @param evt Event that triggers the action.
     * 
     */
    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        String expr = textExpr.getText();
        String arbre;
        try {
            arbre = iCtrlPresentacio.getExpBool(expr);
        } catch (Exception e) {
            labelEstat.setText("L'expressió booleana no existeix");
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e);
            textContingut.setText("");
			return;
        }
        textContingut.setText(arbre);
        labelEstat.setText("");
    }  
}

