package presentationlayer;

import java.io.IOException;
import java.awt.*;


import org.json.simple.parser.ParseException;

import utils.exceptions.database.DatabaseException;
import utils.exceptions.document.DocumentException;

public class CarregarDocument extends javax.swing.JFrame {
    /**
     * Instance of the presentation controller
     */ 
    CtrlPresentacio iCtrlPresentacio;

    /**
     * Button Crear for loading a document
     */
    private javax.swing.JButton buttonCarregar;
    /**
     * Button Inici for returning to main screen
     */
    private javax.swing.JButton buttonInici;
    /**
     * File Chooser for choosing the oath of the file
     */
    private javax.swing.JFileChooser fileChooser;
    /**
     * Panel with parts of the view
     */
    private javax.swing.JPanel jPanel1;
    /**
     * Label with the CARREGAR DOCUMENT text
     */
    private javax.swing.JLabel labelCarregar;
    /**
     * Label with the state
     */
    private javax.swing.JLabel labelEstat;
    /**
     * Label with the explanation
     */
    private javax.swing.JLabel labelExplicacio;
    /**
     * Text area where the explanation is
     */
    private javax.swing.JTextArea textExplicacio;
    /**
     * Scroll Panel with parts of the view
     */
    private javax.swing.JScrollPane jScrollPane1;

    /**
     * Default contructor
     * @param pCtrlPresentacio Instance of the presentation controller
     */
    public CarregarDocument(CtrlPresentacio pCtrlPresentacio) {
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
     * Make this view visible
     */
    public void ferInvisible() {
        this.setVisible(false);
    }
      
    /**
     * Initialitzation of all the components in the view
     */
    private void initComponents() {

        labelCarregar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelExplicacio = new javax.swing.JLabel();
        fileChooser = new javax.swing.JFileChooser();
        buttonInici = new javax.swing.JButton();
        buttonCarregar = new javax.swing.JButton();
        labelEstat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textExplicacio = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 900));

        labelCarregar.setText("CARREGAR DOCUMENT");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelExplicacio.setText("Clica aquí per triar un document:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelExplicacio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelExplicacio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        buttonInici.setText("Inici");
        buttonInici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIniciActionPerformed(evt);
            }
        });

        buttonCarregar.setText("Carregar");
        buttonCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCarregarActionPerformed(evt);
            }
        });

        textExplicacio.setEditable(false);
        textExplicacio.setColumns(20);
        textExplicacio.setRows(5);
        textExplicacio.setText("Format per documents .txt: El títol del fitxer serà el títol del document, a la\nprimera línia s'hi espera l'autor, i tota la resta és contingut.\n\nFormat per documents .txt: El títol del fitxer serà el títol del document, la línia que\ncontingui l'etiqueta \"<author>\" serà el que s'establirà com a autor del document\ni totes les línies amb l'etiqueta \"<body>\" s'establiràn com a contingut.");
        textExplicacio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(textExplicacio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonInici)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelEstat, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonCarregar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 321, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1))
                                .addGap(0, 322, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelCarregar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelCarregar)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonInici)
                        .addComponent(buttonCarregar))
                    .addComponent(labelEstat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }   

    /**
    * Adding a new document when the button "Carregar" is pressed
    * @param evt The event that triggers the action
    */
    private void buttonCarregarActionPerformed(java.awt.event.ActionEvent evt) {                  
        String path = fileChooser.getSelectedFile().getAbsolutePath();
        
        try {
            iCtrlPresentacio.carregarDocument(path);
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
        labelEstat.setText("El fitxer s'ha carregat satisfactòriament");
        Color verd = new Color(0, 143, 57);
        labelEstat.setForeground(verd);
    }            
    
    /**
     * Return to the initial view
     * @param evt The event that triggers the action
     */
    private void buttonIniciActionPerformed(java.awt.event.ActionEvent evt) {                                            
        iCtrlPresentacio.sincronitzarCarregarDocumentAVistaPrincipal();
        labelEstat.setText("");
    }   
                 
}
