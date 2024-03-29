package presentationlayer;

import java.util.ArrayList;

public class LlistarPrefix extends javax.swing.JFrame {
    /**
     * CtrlPresentacio instance
     */
    CtrlPresentacio iCtrlPresentacio;
    /**
     * Inici Button for going back to the main menu
     */
    private javax.swing.JButton buttonInici;
    /**
     * Mostrar Button for showing the pertinent authors
     */    
    private javax.swing.JButton buttonMostrar;
    /**
     * Panel that contains the visible components
     */  
    private javax.swing.JPanel jPanel1;
    /**
     * Permits scroll the text area where the pertinent authors are shown
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
     * Editable text
     */ 
    private javax.swing.JTextArea textContingut;
    /**
     * Editable text
     */ 
    private javax.swing.JTextField textAutor;

    /**
     * Constructor of the secondary view LlistarPrefix
     * @param pCtrlPresentacio CtrlPresentacio instance
     */    
    public LlistarPrefix(CtrlPresentacio pCtrlPresentacio) {
        iCtrlPresentacio = pCtrlPresentacio;
        initComponents();
    }
    /**
     * Makes the secondary view LlistarPrefix visible
     */                        
    public void ferVisible() {
        this.setVisible(true);
    }
    /**
     * Makes the secondary view LlistarPrefix invisible
     */
    public void ferInvisible() {
        this.setVisible(false);
    }
    /**
     * Initializes the components of the secondary view LlistarPrefix
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setPreferredSize(new java.awt.Dimension(1600, 900));
        labelMostrar.setText("LLISTAR AUTORS PER PREFIX");

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

        

        labelAutor.setText("Prefix:");

        buttonMostrar.setText("Mostrar");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAutor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonMostrar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAutor)
                    .addComponent(buttonMostrar))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonInici)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(388, Short.MAX_VALUE)
                .addComponent(labelMostrar)
                .addContainerGap(389, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelMostrar)
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonInici)
                .addContainerGap())
        );

        pack();
    }                     
    /**
     * List the authors corresponding to the prefix entered when the Mostar or Enter button is pressed.
     * @param evt Event that triggers the action.
     * 
     */
    private void textAutorActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String prefix = textAutor.getText();
        ArrayList<String> llistatAutors;
        try {
            llistatAutors = iCtrlPresentacio.getllistatAutors(prefix);
        } catch (Exception e) {
            System.err.println(e);
			return;
        }

        String convertirArray = convertirArray(llistatAutors);
        textContingut.setText(convertirArray);
        
    }
    
    /**
     * Converts an array of authors 'a' to a String where the authors are listed with the corresponding line breaks.
     * @param a Array of authors.
     * 
     */     
    private String convertirArray(ArrayList<String> a) {
        String sol = "";
        for(String aut : a) {
            sol += aut + System.lineSeparator();
        } 
        return sol;
    }
    /**
     * Returns to the main view by deleting the content of 'textcontingut'
     * @param evt Event that triggers the action.
     * 
     */
    private void buttonIniciActionPerformed(java.awt.event.ActionEvent evt) {                                            
        iCtrlPresentacio.sincronitzarLlistarPrefixAVistaPrincipal();
        textContingut.setText("");
        textAutor.setText("");
    }                                                          
}

