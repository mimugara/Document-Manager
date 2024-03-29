package presentationlayer;

import java.io.IOException;
import java.awt.*;


import org.json.simple.parser.ParseException;

import utils.exceptions.database.DatabaseException;
import utils.exceptions.document.DocumentException;

public class GuardarDocument extends javax.swing.JFrame {
    /**
     * Instance of the presentation controller
     */
    private CtrlPresentacio iCtrlPresentacio;

    /**
     * Button Crear for saving a new document
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
     * Text field where the path is indicated
     */
    private javax.swing.JTextField textUbicacio;
    /**
     * Label with the Autor text
     */
    private javax.swing.JLabel labelAutor;
    /**
     * Label with the state
     */
    private javax.swing.JLabel labelEstat;
    /**
     * Label with the Guardar text
     */
    private javax.swing.JLabel labelGuardar;
    /**
     * Label with the Titol text
     */
    private javax.swing.JLabel labelTitol;
    /**
     * Label with the Ubicació text
     */
    private javax.swing.JLabel labelUbicacio;
    /**
     * Text field where the author is indicated
     */
    private javax.swing.JTextField textAutor;
    /**
     * Text field where the title is indicated
     */
    private javax.swing.JTextField textTitol;

    /**
     * Default Contructor
     * @param pCtrlPresentacio Instance of the presentation controller
     */
    public GuardarDocument(CtrlPresentacio pCtrlPresentacio) {
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

        labelGuardar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelTitol = new javax.swing.JLabel();
        labelAutor = new javax.swing.JLabel();
        labelUbicacio = new javax.swing.JLabel();
        textTitol = new javax.swing.JTextField();
        textAutor = new javax.swing.JTextField();
        textUbicacio = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        buttonInici = new javax.swing.JButton();
        labelEstat = new javax.swing.JLabel();
        buttonGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 900));

        labelGuardar.setText("GUARDAR DOCUMENT");
        labelGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        labelTitol.setText("Títol:");

        labelAutor.setText("Autor:");

        labelUbicacio.setText("Ubicació:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelAutor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textAutor))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelUbicacio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textUbicacio))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelTitol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textTitol)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTitol)
                    .addComponent(textTitol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAutor)
                    .addComponent(textAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUbicacio)
                    .addComponent(textUbicacio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonInici)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelEstat, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 562, Short.MAX_VALUE)
                .addComponent(buttonGuardar)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonGuardar)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelEstat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonInici, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelGuardar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(labelGuardar)
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 389, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }                                                           

    /**
     * Return to the initial view
     * @param evt The event that triggers the action
     */
    private void buttonIniciActionPerformed(java.awt.event.ActionEvent evt) {                                            
        labelEstat.setText("");
        textTitol.setText("");
        textAutor.setText("");
        textUbicacio.setText("");
        iCtrlPresentacio.sincronitzarGuardarDocumentAVistaPrincipal();
    }
    
    /**
     * Save the document when the button "Guardar" is pressed
     * @param evt The event that triggers the action
     */
    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String titol = textTitol.getText();
        String autor = textAutor.getText();
        String path = textUbicacio.getText();

        try {
            iCtrlPresentacio.guardarDocument(titol, autor, path);
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
        labelEstat.setText("El fitxer s'ha guardat satisfactòriament");
        Color verd = new Color(0, 143, 57);
        labelEstat.setForeground(verd);
    }
                  
}
