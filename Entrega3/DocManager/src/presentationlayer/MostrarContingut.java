package presentationlayer;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import utils.exceptions.database.DatabaseException;
import utils.exceptions.document.DocumentException;

public class MostrarContingut extends javax.swing.JFrame {
    /**
     * Instance of the presentation controller
     */
    CtrlPresentacio iCtrlPresentacio;

    /**
     * Button Inici for returning to main screen
     */
    private javax.swing.JButton buttonInici;
    /**
     * Button Crear for showing a document
     */
    private javax.swing.JButton buttonMostrar;
    /**
     * Panel with parts of the view
     */
    private javax.swing.JPanel jPanel1;
    /**
     * Scroll Panel with parts of the view
     */
    private javax.swing.JScrollPane jScrollPane1;
    /**
     * Label with the Autor text
     */
    private javax.swing.JLabel labelAutor;
    /**
     * Label with the MOSTRAR CONTINGUT text
     */
    private javax.swing.JLabel labelMostrar;
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
     * Text area where the content is indicated
     */
    private javax.swing.JTextArea textContingut;
    /**
     * Text field where the title is indicated
     */
    private javax.swing.JTextField textTitol;

    /**
     * Default Contructor
     * @param pCtrlPresentacio Instance of the presentation controller
     */
    public MostrarContingut(CtrlPresentacio pCtrlPresentacio) {
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

        labelMostrar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelTitol = new javax.swing.JLabel();
        labelAutor = new javax.swing.JLabel();
        textTitol = new javax.swing.JTextField();
        buttonMostrar = new javax.swing.JButton();
        textAutor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textContingut = new javax.swing.JTextArea();
        buttonInici = new javax.swing.JButton();
        labelEstat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 900));

        labelMostrar.setText("MOSTRAR CONTINGUT");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelTitol.setText("Títol:");

        labelAutor.setText("Autor:");

        buttonMostrar.setText("Mostrar");
        buttonMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMostrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelTitol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
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
                            .addComponent(labelTitol)
                            .addComponent(labelAutor)
                            .addComponent(textTitol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(buttonMostrar)))
                .addGap(14, 14, 14))
        );

        textContingut.setEditable(false);
        textContingut.setColumns(20);
        textContingut.setRows(5);
        jScrollPane1.setViewportView(textContingut);

        buttonInici.setText("Inici");
        buttonInici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIniciActionPerformed(evt);
            }
        });

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
     * Show the  document when the button "Mostrar" is pressed
     * @param evt The event that triggers the action
     */
    private void buttonMostrarActionPerformed(java.awt.event.ActionEvent evt) {                                          
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
            textContingut.setText("");
            return;
        } catch (DatabaseException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getType());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getType() + ": " + e.getMessage());
            textContingut.setText("");
            return;
        } catch (IOException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getMessage());
            textContingut.setText("");

            return;
        } catch (ParseException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getMessage());
            textContingut.setText("");
            return;
        }
        textContingut.setText(contingut);
        labelEstat.setText("");
    }                                        

    /**
     * Return to the initial view
     * @param evt The event that triggers the action
     */
    private void buttonIniciActionPerformed(java.awt.event.ActionEvent evt) {                                            
        iCtrlPresentacio.sincronitzarMostrarContingutAVistaPrincipal();
        textContingut.setText("");
        textTitol.setText("");
        textAutor.setText("");
        labelEstat.setText("");
    }                                                          
}

