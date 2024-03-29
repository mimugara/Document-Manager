package presentationlayer;

import java.awt.*;

import utils.exceptions.boolexpression.BoolExpressionException;
import utils.exceptions.database.DatabaseException;

public class BaixaExpBooleana extends javax.swing.JFrame {
    
    /**
     * Instance of the presentation controller
     */
    CtrlPresentacio iCtrlPresentacio;

    /**
     * Baixa button to delete a boolean expression 
     */
    private javax.swing.JButton buttonBaixa;
    /**
     * Cancela button to cancel
     */
    private javax.swing.JButton buttonCancela;
    /**
     * Panel that contents the visible components
     */
    private javax.swing.JPanel jPanel1;
    /**
     * Panel that contents the visible components
     */
    private javax.swing.JPanel jPanel2;
    /**
     * Panel that contents the visible components
     */
    private javax.swing.JPanel jPanel3;
    /**
     * Panel that contents the visible components
     */
    private javax.swing.JPanel jPanel4;
    /**
     * Non-editable text
     */
    private javax.swing.JLabel labelNom;
    /**
     * Non-editable text
     */
    private javax.swing.JLabel labelEstat;
    /**
     * Non-editable text
     */
    private javax.swing.JLabel labelMenu;
    /**
     * Editable text
     */
    private javax.swing.JTextField textNom;
    
    
    
    /**
     * Constructs the BaixaExpBooleana view
     * @param pCtrlPresentacio
     */
    public BaixaExpBooleana(CtrlPresentacio pCtrlPresentacio) {
        iCtrlPresentacio = pCtrlPresentacio;
        initComponents();
    }

    /**
     * Makes this view visible
     */
    public void ferVisible() {
        this.setVisible(true);
    }

    /**
     * Makes this view invisible
     */
    public void ferInvisible() {
        this.setVisible(false);
    }
    
    /**
     * Initializes all the view components
     */
    private void initComponents() {

        labelNom= new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        textNom = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        buttonCancela = new javax.swing.JButton();
        buttonBaixa = new javax.swing.JButton();
        labelEstat = new javax.swing.JLabel();
        labelMenu = new javax.swing.JLabel();
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 900));
        
        labelMenu.setText("ESBORRAR EXPRESSIÓ BOOLEANA");
        
        
        buttonCancela.setText("Inici");
        buttonCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelaActionPerformed(evt);
            }
        });

        labelNom.setText("           Nom:");
        
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelNom))
                .addGap(10, 10, 10))      
        );
        
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(labelNom)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textNom)))
        );
        
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(textNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))     
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

        buttonCancela.setText("Inici");
        buttonCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelaActionPerformed(evt);
            }
        });
        
        buttonBaixa.setText("Esborrar");
        buttonBaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBaixaActionPerformed(evt);
            }
        });
        
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(buttonCancela)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1173, Short.MAX_VALUE)
                .addComponent(labelEstat, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonBaixa))
        );
        
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonCancela)
                        .addComponent(buttonBaixa))
                    .addComponent(labelEstat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMenu)
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
                .addComponent(labelMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
        pack();
    }

    
    /**
     * Deletes the boolean expression when the Baixa button is pressed
     * @param evt
     */
    private void buttonBaixaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String nom = textNom.getText();
        
        try {
            iCtrlPresentacio.baixaExpBool(nom);
        } catch (BoolExpressionException boolexperror) {
            labelEstat.setVisible(true);
            labelEstat.setText(boolexperror.getType());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(boolexperror.getType() + ": " + boolexperror.getMessage());
            return;
        }
        catch (DatabaseException boolexperror) {
            labelEstat.setVisible(true);
            labelEstat.setText(boolexperror.getType());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(boolexperror.getType() + ": " + boolexperror.getMessage());
            return;
        }
        catch (Exception boolexperror) {
            labelEstat.setVisible(true);
            labelEstat.setText(boolexperror.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.err.println(boolexperror.getMessage());
            return;
        }
        labelEstat.setVisible(true);
        labelEstat.setText("Donada de baixa correctament");
        Color verd = new Color(0, 143, 57);
        labelEstat.setForeground(verd);
    } 
    
    /**
     * Returns to the initial view when the Cancela button is pressed
     * @param evt
     */
    private void buttonCancelaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        iCtrlPresentacio.sincronitzarBaixaExpBooleanaAVistaPrincipal();
        textNom.setText("");
        labelEstat.setVisible(false);
    }

}
