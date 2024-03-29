package presentationlayer;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import domainlayer.model.Document;
import utils.exceptions.database.DatabaseInconsistentException;
import utils.exceptions.database.FolderNotFoundException;
import utils.exceptions.document.AutorNoExisteixException;
import utils.exceptions.document.AutorTitolNoExisteixException;

public class LlistarSemb extends javax.swing.JFrame {
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
    private javax.swing.JLabel labelAutor;
    /**
     * Non-editable text
     */ 
    private javax.swing.JLabel labelMostrar;
    /**
     * Non-editable text
     */ 
    private javax.swing.JLabel labelTitol;
    /**
     * Label with the state
     */
    private javax.swing.JLabel labelEstat;
    /**
     * Non-editable text
     */ 
    private javax.swing.JLabel labelK;
    /**
     * Editable text
     */   
    private javax.swing.JTextField textAutor;
    /**
     * Editable text
     */ 
    private javax.swing.JTextField textK;
    /**
     * Editable text
     */ 
    private javax.swing.JTextArea textContingut;
    /**
     * Editable text
     */ 
    private javax.swing.JTextField textTitol;

    /**
     * Constructor of the secondary view LlistarSemb
     * @param pCtrlPresentacio CtrlPresentacio instance
     */
    public LlistarSemb(CtrlPresentacio pCtrlPresentacio) {
        iCtrlPresentacio = pCtrlPresentacio;
        initComponents();
    }
    /**
     * Makes the secondary view LlistarSemb visible
     */
    public void ferVisible() {
        this.setVisible(true);
    }
    /**
     * Makes the secondary view LlistarSemb invisible
     */
    public void ferInvisible() {
        this.setVisible(false);
    }
    /**
     * Initializes the components of the secondary view LlistarSemb
     */
    private void initComponents() {

        labelMostrar = new javax.swing.JLabel();
        buttonInici = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textContingut = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        textAutor = new javax.swing.JTextField();
        labelAutor = new javax.swing.JLabel();
        textTitol = new javax.swing.JTextField();
        labelTitol = new javax.swing.JLabel();
        textK = new javax.swing.JTextField();
        labelK = new javax.swing.JLabel();
        labelEstat = new javax.swing.JLabel();

        
        
        buttonMostrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setPreferredSize(new java.awt.Dimension(1600, 900));

        labelMostrar.setText("LLISTAR PER SEMBLANÇA");

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

        textK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAutorActionPerformed(evt);
            }
        });

        textAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAutorActionPerformed(evt);
            }
        });

        textTitol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAutorActionPerformed(evt);
            }
        });

        labelAutor.setText("Autor:");

        labelTitol.setText("Títol:");

        labelK.setText("Nombre de documents:");

        buttonMostrar.setText("Mostrar");

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
                .addGap(50, 50, 50)
                .addComponent(labelK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textK, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(labelK)
                            .addComponent(textTitol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
     * List the corresponding documents when the Mostrar or Enter button is pressed.
     * @param evt Event that triggers the action.
     * 
     */
    private void textAutorActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String titol = textTitol.getText();
        String autor = textAutor.getText();
        Integer k = Integer.parseInt(textK.getText());
        ArrayList<Document> docs;
        try {
            docs = iCtrlPresentacio.getllistatSembl(titol, autor,k);
        } catch (ParseException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getMessage());
            textContingut.setText("");

            return;
        } catch (IOException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getMessage());
            textContingut.setText("");

            return;
        } catch (DatabaseInconsistentException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getType());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getType() + ": " + e.getMessage());
            textContingut.setText("");

            return;
        } catch (FolderNotFoundException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getMessage());
            textContingut.setText("");

            return;
        } catch (AutorTitolNoExisteixException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getMessage());
            textContingut.setText("");

            return;
        }
        catch (AutorNoExisteixException e) {
            labelEstat.setVisible(true);
            labelEstat.setText(e.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(e.getMessage());
            textContingut.setText("");

            return;
        }
        String out = convertirArray(docs);
        
        textContingut.setText(out);
        labelEstat.setText("");

    }
    /**
     * Converts an array of documents 'l' to a String where the documents are listed by title and author with the corresponding line breaks.
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
     * Returns to the main view deleting the content of 'textcontingut'
     * @param evt Event that triggers the action.
     * 
     */
    private void buttonIniciActionPerformed(java.awt.event.ActionEvent evt) {                                            
        iCtrlPresentacio.sincronitzarLlistarSembAVistaPrincipal();
        textContingut.setText("");
        textTitol.setText("");
        textK.setText("");
        textAutor.setText("");
        labelEstat.setText("");
    }                                                          
}

