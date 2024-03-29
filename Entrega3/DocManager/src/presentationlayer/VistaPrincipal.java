package presentationlayer;

import java.awt.event.ActionEvent;
import java.awt.*;

public class VistaPrincipal extends javax.swing.JFrame {
    /**
     * Instance of the presentation controller
     */
    private CtrlPresentacio iCtrlPresentacio;

    private javax.swing.JButton buttonReset;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuDocuments;
    private javax.swing.JMenuItem menuitemalta;
    private javax.swing.JMenuItem menuitemaltabool;
    private javax.swing.JMenuItem menuitembaixabool;
    private javax.swing.JMenuItem menuitemborrar;
    private javax.swing.JMenuItem menuitemcarregar;
    private javax.swing.JMenuItem menuitemguardar;
    private javax.swing.JMenuItem menuitemllistarautor;
    private javax.swing.JMenuItem menuitemllistarbool;
    private javax.swing.JMenuItem menuitemllistarprefix;
    private javax.swing.JMenuItem menuitemllistarsemblanca;
    private javax.swing.JMenuItem menuitemmodbool;
    private javax.swing.JMenuItem menuitemmodificar;
    private javax.swing.JMenuItem menuitemmostrar;
    private javax.swing.JTextArea textExplicacio;

    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonSi;
    private javax.swing.JDialog dialogReset;
    private javax.swing.JLabel labelSegur;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelEstat;
    
    /**
     * Default Contructor
     * @param pCtrlPresentacio
     */
    public VistaPrincipal(CtrlPresentacio pCtrlPresentacio) {
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

    private void initComponents() {

        dialogReset = new javax.swing.JDialog();
        labelSegur = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        buttonSi = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        labelEstat = new javax.swing.JLabel();
        buttonReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textExplicacio = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuDocuments = new javax.swing.JMenu();
        menuitemcarregar = new javax.swing.JMenuItem();
        menuitemguardar = new javax.swing.JMenuItem();
        menuitemalta = new javax.swing.JMenuItem();
        menuitemmodificar = new javax.swing.JMenuItem();
        menuitemborrar = new javax.swing.JMenuItem();
        menuitemmostrar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuitemllistarautor = new javax.swing.JMenuItem();
        menuitemllistarprefix = new javax.swing.JMenuItem();
        menuitemllistarsemblanca = new javax.swing.JMenuItem();
        menuitemllistarbool = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuitemaltabool = new javax.swing.JMenuItem();
        menuitemmodbool = new javax.swing.JMenuItem();
        menuitembaixabool = new javax.swing.JMenuItem();

        dialogReset.setResizable(false);
        dialogReset.setSize(new java.awt.Dimension(348, 92));

        labelSegur.setText("Segur que vols fer un reset del sistema?");

        buttonSi.setText("Sí");
        buttonSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSiActionPerformed(evt);
            }
        });

        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(buttonSi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelEstat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCancelar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonSi)
                        .addComponent(buttonCancelar))
                    .addComponent(labelEstat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout dialogResetLayout = new javax.swing.GroupLayout(dialogReset.getContentPane());
        dialogReset.getContentPane().setLayout(dialogResetLayout);
        dialogResetLayout.setHorizontalGroup(
            dialogResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogResetLayout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(labelSegur)
                .addContainerGap(92, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogResetLayout.setVerticalGroup(
            dialogResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogResetLayout.createSequentialGroup()
                .addComponent(labelSegur)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 900));

        buttonReset.setText("Reset");
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        textExplicacio.setEditable(false);
        textExplicacio.setBackground(new java.awt.Color(242, 242, 242));
        textExplicacio.setColumns(70);
        textExplicacio.setRows(10);
        textExplicacio.setText("Menú Documents: Carregar, Guardar, Donar d'alta, Modificar, Esborrar i Mostrar document.\n\nMenú Llistes: Llistar documents per autor, Llistar autors per prefix, Llistar documents per semblança i Llistar documents a partir d'expressió booleana.\n\nMenú Expressions Booleanes: Donar d'alta, Modificar i Esborrar expressions booleanes.\n\nBotó Reset: Fa un reset del sistema i esborra tots els documents i expressions booleanes de la base de dades.");
        textExplicacio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(textExplicacio);

        menuDocuments.setText("Documents");

        menuitemcarregar.setText("Carregar document");
        menuitemcarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemcarregarActionPerformed(evt);
            }
        });
        menuDocuments.add(menuitemcarregar);

        menuitemguardar.setText("Guardar document");
        menuitemguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemguardarActionPerformed(evt);
            }
        });
        menuDocuments.add(menuitemguardar);

        menuitemalta.setText("Alta document");
        menuitemalta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemaltaActionPerformed(evt);
            }
        });
        menuDocuments.add(menuitemalta);

        menuitemmodificar.setText("Modificar document");
        menuitemmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemmodActionPerformed(evt);
            }
        });
        menuDocuments.add(menuitemmodificar);

        menuitemborrar.setText("Esborrar document");
        menuitemborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemborrarActionPerformed(evt);
            }
        });
        menuDocuments.add(menuitemborrar);

        menuitemmostrar.setText("Mostrar document");
        menuitemmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemmostrarActionPerformed(evt);
            }
        });
        menuDocuments.add(menuitemmostrar);

        jMenuBar1.add(menuDocuments);

        jMenu2.setText("Llistes");

        menuitemllistarautor.setText("Llistar els documents per autor");
        menuitemllistarautor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemllistarautorActionPerformed(evt);
            }
        });
        jMenu2.add(menuitemllistarautor);

        menuitemllistarprefix.setText("Llistar els autors per prefix");
        menuitemllistarprefix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemllistarprefixActionPerformed(evt);
            }
        });
        jMenu2.add(menuitemllistarprefix);

        menuitemllistarsemblanca.setText("Llistar els documents per semblança");
        menuitemllistarsemblanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemllistarsemblancaActionPerformed(evt);
            }
        });
        jMenu2.add(menuitemllistarsemblanca);

        menuitemllistarbool.setText("Llistar els documents a partir d'una expressió booleana");
        menuitemllistarbool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemllistarboolActionPerformed(evt);
            }
        });
        jMenu2.add(menuitemllistarbool);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Expressions Booleanes");

        menuitemaltabool.setText("Alta expressió booleana");
        menuitemaltabool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemaltaboolActionPerformed(evt);
            }
        });
        jMenu4.add(menuitemaltabool);

        menuitemmodbool.setText("Modificació expressió booleana");
        menuitemmodbool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemmodboolActionPerformed(evt);
            }
        });
        jMenu4.add(menuitemmodbool);

        menuitembaixabool.setText("Esborrar expressió booleana");
        menuitembaixabool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitembaixaboolActionPerformed(evt);
            }
        });
        jMenu4.add(menuitembaixabool);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonReset)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(590, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(605, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(331, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                .addComponent(buttonReset)
                .addContainerGap())
        );

        pack();
    }                       
    
    /**
     * Change to AltaDocument view
     * @param e
     */
    private void menuitemaltaActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalAAltaDocument();
    }

    /**
     * Change to ModificarDocument view
     * @param e
     */
    private void menuitemmodActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalAModificarDocument();
    }

    /**
     * Change to BaixaDocument view
     * @param e
     */
    private void menuitemborrarActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalABaixaDocument();
    }

    /**
     * Change to MostrarDocument view
     * @param e
     */
    private void menuitemmostrarActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalAMostrarContingut();
    }

    /**
     * Change to CarregarDocument view
     * @param e
     */
    private void menuitemcarregarActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalACarregarDocument();
    }

    /**
     * Change to GuardarDocument view
     * @param e
     */
    private void menuitemguardarActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalAGuardarDocument();
    }

    /**
     * Change to LlistarTitolsAutor view
     * @param e
     */
    private void menuitemllistarautorActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalALlistDocAut();
    }

    /**
     * Change to LlistarDocumentsPerPrefix view
     * @param e
     */
    private void menuitemllistarprefixActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalALlistarPrefix();
    }

    /**
     * Change to LlistarDocumentsPerSemblança view
     * @param e
     */
    private void menuitemllistarsemblancaActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalALlistarSemb();
    }

    /**
     * Change to LlistarDocumentsPerExpressióBooleana view
     * @param e
     */
    private void menuitemllistarboolActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalALlistarBool();
    }

    /**
     * Change to AltaExpressióBooleana view
     * @param e
     */
    private void menuitemaltaboolActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalAAltaExpBooleana();
    }

    /**
     * Change to BaixaExpressióBooleana view
     * @param e
     */
    private void menuitembaixaboolActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalABaixaExpBooleana();
    }

    /**
     * Change to ModificarExpressióBooleana view
     * @param e
     */
    private void menuitemmodboolActionPerformed(ActionEvent e) {
        iCtrlPresentacio.sincronitzarVistaPrincipalAModificarExpBooleana();
    }

    /**
     * Opens the reset dialog
     * @param e
     */
    private void buttonResetActionPerformed(ActionEvent e) {
        dialogReset.setVisible(true);
    }

    /**
     * Resets the database
     * @param e
     */
    private void buttonSiActionPerformed(ActionEvent e) {
        try {
            iCtrlPresentacio.reset();
        } catch (Exception exception) {
            labelEstat.setText(exception.getMessage());
            labelEstat.setForeground(java.awt.Color.red);
            System.out.println(exception);
            return;
        }
        labelEstat.setText("Reset satisfactòri");
        Color verd = new Color(0, 143, 57);
        labelEstat.setForeground(verd);
    }

    /**
     * Closes the reset dialog
     * @param e
     */
    private void buttonCancelarActionPerformed(ActionEvent e) {
        dialogReset.setVisible(false);
        labelEstat.setText("");
    }

}
