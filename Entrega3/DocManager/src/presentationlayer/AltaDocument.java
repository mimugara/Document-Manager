package presentationlayer;

import java.io.IOException;
import java.awt.*;


import utils.exceptions.database.DatabaseException;
import utils.exceptions.document.DocumentException;

public class AltaDocument extends javax.swing.JFrame {
    /**
     * Instance of the presentation controller
     */
    private CtrlPresentacio iCtrlPresentacio;

    /**
     * Button Crear for creating a new document
     */
    private javax.swing.JButton buttonCrear;
    /**
     * Button Inici for returning to main screen
     */
    private javax.swing.JButton buttonInici;
    /**
     * Label with the ALTA DOCUMENT text
     */
    private javax.swing.JLabel jLabel4;
    /**
     * Panel with parts of the view
     */
    private javax.swing.JPanel jPanel1;
    /**
     * Panel with parts of the view
     */
    private javax.swing.JPanel jPanel2;
    /**
     * Panel with parts of the view
     */
    private javax.swing.JPanel jPanel3;
    /**
     * Panel with parts of the view
     */
    private javax.swing.JPanel jPanel4;
    /**
     * Scroll Panel with parts of the view
     */
    private javax.swing.JScrollPane jScrollPane1;
    /**
     * Label with the Autor text
     */
    private javax.swing.JLabel labelAutor;
    /**
     * Label with the Contingut text
     */
    private javax.swing.JLabel labelContingut;
    /**
     * Label with the state
     */
    private javax.swing.JLabel labelEstat;
    /**
     * Label with the Titol text
     */
    private javax.swing.JLabel labelTitol;
    /**
     * Text field where the author is indicated
     */
    private javax.swing.JTextField textAutor;
    /**
     * Text area where the content is indicated
     */
    private javax.swing.JTextArea textContingut;
    /**
     * Text field where the title is indicated
     */
    private javax.swing.JTextField textTítol;

    /**
     * Default Contructor
     * @param pCtrlPresentacio Instance of the presentation controller
     */
    public AltaDocument(CtrlPresentacio pCtrlPresentacio) {
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

        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        labelTitol = new javax.swing.JLabel();
        labelAutor = new javax.swing.JLabel();
        labelContingut = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        textTítol = new javax.swing.JTextField();
        textAutor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textContingut = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        buttonInici = new javax.swing.JButton();
        buttonCrear = new javax.swing.JButton();
        labelEstat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setPreferredSize(new java.awt.Dimension(1600, 900));

        jLabel4.setText("ALTA DOCUMENT");

        labelTitol.setText("           Títol:");

        labelAutor.setText("         Autor:");

        labelContingut.setText("Contingut:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitol)
                    .addComponent(labelAutor)
                    .addComponent(labelContingut))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(labelTitol)
                .addGap(13, 13, 13)
                .addComponent(labelAutor)
                .addGap(13, 13, 13)
                .addComponent(labelContingut)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        textContingut.setColumns(20);
        textContingut.setRows(5);
        jScrollPane1.setViewportView(textContingut);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textAutor)
                    .addComponent(textTítol)
                    .addComponent(jScrollPane1)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(textTítol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(647, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        buttonInici.setText("Inici");
        buttonInici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIniciActionPerformed(evt);
            }
        });

        buttonCrear.setText("Crear");
        buttonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCrearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(buttonInici)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1173, Short.MAX_VALUE)
                .addComponent(labelEstat, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCrear))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonInici)
                        .addComponent(buttonCrear))
                    .addComponent(labelEstat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }                                          

    /**
     * Creation of the new document when the button "Crear" is pressed
     * @param evt The event that triggers the action
     */     
    private void buttonCrearActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String titol = textTítol.getText();
        String autor = textAutor.getText();
        String contingut = textContingut.getText();

        try {
            iCtrlPresentacio.crearDocument(titol, autor, contingut);
        } catch (DatabaseException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getType());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getType() + ": " + e.getMessage());
            return;
        } catch (DocumentException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getType());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getType() + ": " + e.getMessage());
            return;
        } catch (IOException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getMessage() + ": " + e.getMessage());
            return;
        }
        labelEstat.setText("Fitxer donat d'alta correctament");
        Color verd = new Color(0, 143, 57);
        labelEstat.setForeground(verd);
    }                                           

    /**
     * Return to the initial view
     * @param evt The event that triggers the action
     */
    private void buttonIniciActionPerformed(java.awt.event.ActionEvent evt) {        
        labelEstat.setText("");
        textTítol.setText("");
        textAutor.setText("");
        textContingut.setText("");
        iCtrlPresentacio.sincronitzarAltaDocumentAVistaPrincipal();
    }                                           
              
}
