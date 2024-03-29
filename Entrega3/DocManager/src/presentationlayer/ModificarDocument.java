package presentationlayer;

import java.io.IOException;
import java.awt.*;


import org.json.simple.parser.ParseException;

import utils.exceptions.database.DatabaseException;
import utils.exceptions.document.DocumentException;

public class ModificarDocument extends javax.swing.JFrame {
    /**
     * Instance of the presentation controller
     */
    CtrlPresentacio iCtrlPresentacio;

    /**
     * Button Buscar for searching a document
     */
    private javax.swing.JButton buttonBuscar;
    /**
     * Button Guardar for saving a document
     */
    private javax.swing.JButton buttonGuardar;
    /**
     * Button Inici for returning to main screen
     */
    private javax.swing.JButton buttonInici;
    /**
     * Panel with parts of the view
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
     * Label with the Autor text
     */
    private javax.swing.JLabel labelAutor;
    /**
     * Label with the MODIFICAR DOCUMENT text
     */
    private javax.swing.JLabel labelModificar;
    /**
     * Label with the Titol text
     */
    private javax.swing.JLabel labelTitol;
    /**
     * Label with the state
     */
    private javax.swing.JLabel labelEstat;
    /**
     * Text field where the author is indicated
     */
    private javax.swing.JTextField textAutor;
    /**
     * Text field where the content is indicated and is possible to change it
     */
    private javax.swing.JEditorPane textModificar;
    /**
     * Text field where the title is indicated
     */
    private javax.swing.JTextField textTitol;

    /**
     * Default Contructor
     * @param pCtrlPresentacio Instance of the presentation controller
     */
    public ModificarDocument(CtrlPresentacio pCtrlPresentacio) {
        iCtrlPresentacio = pCtrlPresentacio;
        initComponents();
    }

    /**
     * Make this view visible
     */
    public void ferVisible() {
        this.setVisible(true);
    }

    /**
     * Make this view invisible
     */
    public void ferInvisible() {
        this.setVisible(false);
    }
      
    /**
     * Initialitzation of all the components in the view
     */
    private void initComponents() {

        labelModificar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelTitol = new javax.swing.JLabel();
        labelAutor = new javax.swing.JLabel();
        textTitol = new javax.swing.JTextField();
        textAutor = new javax.swing.JTextField();
        buttonBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textModificar = new javax.swing.JEditorPane();
        buttonInici = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        labelEstat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 900));

        labelModificar.setText("MODIFICAR DOCUMENT");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));

        labelTitol.setText("Títol:");

        labelAutor.setText("Autor:");

        buttonBuscar.setText("Buscar");
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
                .addComponent(labelTitol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(labelAutor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonBuscar)
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTitol)
                    .addComponent(labelAutor)
                    .addComponent(textTitol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBuscar))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        textModificar.setPreferredSize(new java.awt.Dimension(100, 100));
        jScrollPane2.setViewportView(textModificar);

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

        buttonInici.setText("Inici");
        buttonInici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIniciActionPerformed(evt);
            }
        });

        buttonGuardar.setText("Guardar");
        buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 424, Short.MAX_VALUE)
                        .addComponent(labelModificar)
                        .addGap(0, 424, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonInici)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelEstat, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonGuardar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonInici)
                        .addComponent(buttonGuardar))
                    .addComponent(labelEstat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }             

    /**
     * Search for the document when the button "Buscar" is pressed
     * @param evt The event that triggers the action
     */
    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        String titol = textTitol.getText();
        String autor = textAutor.getText();
        String contingut;

        try {
            contingut = iCtrlPresentacio.getContingut(titol, autor);
        } catch (DocumentException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getType());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getType() + ": " + e.getMessage());
            return;
        } catch (DatabaseException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getType());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getType() + ": " + e.getMessage());
            return;
        } catch (IOException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getMessage());
            return;
        } catch (ParseException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getMessage());
            return;
        }
        textModificar.setText(contingut);
    }                                            

    /**
     * Save the document when the button "Guardar" is pressed
     * @param evt The event that triggers the action
     */
    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {  
        String titol = textTitol.getText();
        String autor = textAutor.getText();                                            
        String contingut = textModificar.getText();
        
        try {
            iCtrlPresentacio.setContingut(titol, autor, contingut);
        } catch (DocumentException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getType());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getType() + ": " + e.getMessage());
            return;
        } catch (DatabaseException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getType());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getType() + ": " + e.getMessage());
            return;
        } catch (IOException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getMessage());
            return;
        }
        labelEstat.setText("Fitxer guardat correctament");
        Color verd = new Color(0, 143, 57);
        labelEstat.setForeground(verd);
    }   
    
    /**
     * Return to the initial view
     * @param evt The event that triggers the action
     */ 
    private void buttonIniciActionPerformed(java.awt.event.ActionEvent evt) {       
        labelEstat.setText("");  
        textTitol.setText("");                                   
        textAutor.setText("");   
        textModificar.setText("");
        iCtrlPresentacio.sincronitzarModificarDocumentAVistaPrincipal();
    }     
              
}
