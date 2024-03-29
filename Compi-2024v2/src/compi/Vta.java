package compi;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Vta extends javax.swing.JFrame {
    public Vta() {
        initComponents();
    }
    private void initComponents() {
        jPFondo = new javax.swing.JPanel();
        jPCodigo = new javax.swing.JPanel();
        jLCodigo = new javax.swing.JLabel();
        jPTextArea = new javax.swing.JPanel();
        jSPTextArea = new javax.swing.JScrollPane();
        jTACodigo = new javax.swing.JTextArea();
        jPLisTok = new javax.swing.JPanel();
        jLLisTok = new javax.swing.JLabel();
        jPTLisTok = new javax.swing.JPanel();
        jSPTLisTok = new javax.swing.JScrollPane();
        jTLisTok = new javax.swing.JTable();
        jPContadores = new javax.swing.JPanel();
        jPTCont = new javax.swing.JPanel();
        jSPTCont = new javax.swing.JScrollPane();
        jTCont = new javax.swing.JTable();
        jLCont = new javax.swing.JLabel();
        jBCargar = new javax.swing.JButton();
        jBCompilar = new javax.swing.JButton();
        jBExcel = new javax.swing.JButton();
        jPLisErr = new javax.swing.JPanel();
        jLLisErr = new javax.swing.JLabel();
        jPTLisErr = new javax.swing.JPanel();
        jSPTError = new javax.swing.JScrollPane();
        jTLisErr = new javax.swing.JTable();
        jLContTErr = new javax.swing.JLabel();
        jTFContLex = new javax.swing.JTextField();
        jTFContSin = new javax.swing.JTextField();
        jLLex = new javax.swing.JLabel();
        jLSin = new javax.swing.JLabel();
        errorpnt=0;

        no = new TxtArea(jTACodigo);
        Matriz = new int[72][36];
        tokens = new LinkedList<LisToken>();
        errors = new LinkedList<LisError>();
        LisPalRes = new LinkedList<>();
        
        LisPalRes.addAll(List.of("true","false","null","if","else","switch",
                "for","do","while","console.log","forEach","break","continue",
                "let", "const","undefined","interface","typeof","Number","String",
                "any","set","get","class","toLowerCase","toUpperCase","length",
                "trim" ,"charAt","startsWith","endsWith","indexOf","Includes","slice",
                "replace","split","push","shift","in","of","splice","concat",
                "find","findIndex","filter","map","sort","reverse"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPFondo.setBackground(new java.awt.Color(51, 51, 51));
        jPCodigo.setBackground(new java.awt.Color(153, 0, 0));

        jLCodigo.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 36)); // NOI18N
        jLCodigo.setForeground(new java.awt.Color(255, 255, 255));
        jLCodigo.setText("Codigo");

        jPTextArea.setBackground(new java.awt.Color(51, 51, 51));

        jTACodigo.setColumns(20);
        jTACodigo.setRows(5);
        jSPTextArea.setViewportView(jTACodigo);
        jSPTextArea.setRowHeaderView(no);

        jTACodigo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                no.actualiza();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                no.actualiza();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                no.actualiza();
            }
        });

        javax.swing.GroupLayout jPTextAreaLayout = new javax.swing.GroupLayout(jPTextArea);
        jPTextArea.setLayout(jPTextAreaLayout);
        jPTextAreaLayout.setHorizontalGroup(
                jPTextAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPTextAreaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSPTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPTextAreaLayout.setVerticalGroup(
                jPTextAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPTextAreaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSPTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout jPCodigoLayout = new javax.swing.GroupLayout(jPCodigo);
        jPCodigo.setLayout(jPCodigoLayout);
        jPCodigoLayout.setHorizontalGroup(
                jPCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPCodigoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLCodigo))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPCodigoLayout.setVerticalGroup(
                jPCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPCodigoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPLisTok.setBackground(new java.awt.Color(0, 51, 153));

        jLLisTok.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 36)); // NOI18N
        jLLisTok.setForeground(new java.awt.Color(255, 255, 255));
        jLLisTok.setText("Lista Tokens");

        jPTLisTok.setBackground(new java.awt.Color(51, 51, 51));
        
        jTLisTok.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Estado", "Lexema", "Linea"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jSPTLisTok.setViewportView(jTLisTok);
        if (jTLisTok.getColumnModel().getColumnCount() > 0) {
            jTLisTok.getColumnModel().getColumn(0).setResizable(false);
            jTLisTok.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTLisTok.getColumnModel().getColumn(1).setResizable(false);
            jTLisTok.getColumnModel().getColumn(1).setPreferredWidth(15);
            jTLisTok.getColumnModel().getColumn(2).setResizable(false);
            jTLisTok.getColumnModel().getColumn(2).setPreferredWidth(10);
        }

        javax.swing.GroupLayout jPTLisTokLayout = new javax.swing.GroupLayout(jPTLisTok);
        jPTLisTok.setLayout(jPTLisTokLayout);
        jPTLisTokLayout.setHorizontalGroup(
                jPTLisTokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPTLisTokLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSPTLisTok, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPTLisTokLayout.setVerticalGroup(
                jPTLisTokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPTLisTokLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSPTLisTok, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout jPLisTokLayout = new javax.swing.GroupLayout(jPLisTok);
        jPLisTok.setLayout(jPLisTokLayout);
        jPLisTokLayout.setHorizontalGroup(
                jPLisTokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPLisTokLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPLisTokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPTLisTok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLLisTok))
                                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPLisTokLayout.setVerticalGroup(
                jPLisTokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPLisTokLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLLisTok)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPTLisTok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPContadores.setBackground(new java.awt.Color(204, 153, 0));

        jPTCont.setBackground(new java.awt.Color(51, 51, 51));

        jSPTCont.setBackground(new java.awt.Color(51, 51, 51));
        jSPTCont.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTCont.setModel(new javax.swing.table.DefaultTableModel(
                new String[][]{

                },
                new String[]{
                        "Tipo", "Cantidad"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jSPTCont.setViewportView(jTCont);
        if (jTCont.getColumnModel().getColumnCount() > 0) {
            jTCont.getColumnModel().getColumn(0).setResizable(false);
            jTCont.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPTContLayout = new javax.swing.GroupLayout(jPTCont);
        jPTCont.setLayout(jPTContLayout);
        jPTContLayout.setHorizontalGroup(
                jPTContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPTContLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSPTCont, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPTContLayout.setVerticalGroup(
                jPTContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPTContLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSPTCont, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jLCont.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 36)); // NOI18N
        jLCont.setForeground(new java.awt.Color(255, 255, 255));
        jLCont.setText("Contadores");


        jBCargar.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jBCargar.setText("Abrir");
        jBCargar.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jBCargarActionPerformed(e);
            }
        });

        jBCompilar.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jBCompilar.setText("Compilar");
        jBCompilar.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jBCompilarActionPerformed(e);
            }
        });

        jBExcel.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jBExcel.setText("Excel");
        jBExcel.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jBExcelActionPerformed(e);
            }
        });

        javax.swing.GroupLayout jPContadoresLayout = new javax.swing.GroupLayout(jPContadores);
        jPContadores.setLayout(jPContadoresLayout);
        jPContadoresLayout.setHorizontalGroup(
                jPContadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPContadoresLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPContadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPContadoresLayout.createSequentialGroup()
                                                .addComponent(jPTCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPContadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jBCompilar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jBCargar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jBExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(jLCont))
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPContadoresLayout.setVerticalGroup(
                jPContadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPContadoresLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLCont)
                                .addGroup(jPContadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPContadoresLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPTCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(12, Short.MAX_VALUE))
                                        .addGroup(jPContadoresLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jBCargar)
                                                .addGap(38, 38, 38)
                                                .addComponent(jBCompilar)
                                                .addGap(41, 41, 41)
                                                .addComponent(jBExcel)
                                                .addGap(105, 105, 105))))
        );

        jPLisErr.setBackground(new java.awt.Color(51, 102, 0));

        jLLisErr.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 36)); // NOI18N
        jLLisErr.setForeground(new java.awt.Color(255, 255, 255));
        jLLisErr.setText("Lista Errores");

        jPTLisErr.setBackground(new java.awt.Color(51, 51, 51));

        jTLisErr.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Token", "Descripción", "Lexema", "Tipo de Error", "Linea"
                }
        ));
        jSPTError.setViewportView(jTLisErr);

        javax.swing.GroupLayout jPTLisErrLayout = new javax.swing.GroupLayout(jPTLisErr);
        jPTLisErr.setLayout(jPTLisErrLayout);
        jPTLisErrLayout.setHorizontalGroup(
                jPTLisErrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPTLisErrLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSPTError, javax.swing.GroupLayout.DEFAULT_SIZE, 1184, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPTLisErrLayout.setVerticalGroup(
                jPTLisErrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPTLisErrLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSPTError, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jLContTErr.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jLContTErr.setForeground(new java.awt.Color(255, 255, 255));
        jLContTErr.setText("Contador Errores");

        jTFContLex.setEditable(false);
        jTFContLex.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jTFContLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFContLexActionPerformed(evt);
            }
        });

        jTFContSin.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N

        jLLex.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); // NOI18N
        jLLex.setForeground(new java.awt.Color(255, 255, 255));
        jLLex.setText("Lexico");

        jLSin.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); // NOI18N
        jLSin.setForeground(new java.awt.Color(255, 255, 255));
        jLSin.setText("Sintaxis");

        javax.swing.GroupLayout jPLisErrLayout = new javax.swing.GroupLayout(jPLisErr);
        jPLisErr.setLayout(jPLisErrLayout);
        jPLisErrLayout.setHorizontalGroup(
                jPLisErrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPLisErrLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPLisErrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPTLisErr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLLisErr))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPLisErrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLContTErr)
                                        .addGroup(jPLisErrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jTFContLex, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTFContSin, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addComponent(jLLex)
                                        .addComponent(jLSin))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPLisErrLayout.setVerticalGroup(
                jPLisErrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPLisErrLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLLisErr)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPTLisErr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(jPLisErrLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLContTErr)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLLex)
                                .addGap(2, 2, 2)
                                .addComponent(jTFContLex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLSin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFContSin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(136, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPFondoLayout = new javax.swing.GroupLayout(jPFondo);
        jPFondo.setLayout(jPFondoLayout);
        jPFondoLayout.setHorizontalGroup(
                jPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPFondoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPLisErr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPFondoLayout.createSequentialGroup()
                                                .addComponent(jPCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(jPLisTok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(jPContadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPFondoLayout.setVerticalGroup(
                jPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPFondoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPLisTok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPContadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPLisErr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    private void jBCompilarActionPerformed(ActionEvent e) {
        error=0;
        id=0;
        comen=0;
        palaRes=0;
        conCad=0;
        conNum=0;
        conRea=0;
        conExp=0;
        conBol=0;
        conNull=0;
        posfix=0;
        binario=0;
        control=0;
        mate=0; //matematicos
        expon=0; //exponente
        turno=0;
        relac=0; //relacionales
        sinIgu=0; //sin igualdad de conversion
        logicos=0;
        ternarios=0;
        asign=0;
        agrupa=0;
        System.out.println("contadores en 0, proceder a ejecutarse");
        ejecutar();
    }
    private void ejecutar() {
        char cha;
        String lexema="",type="",desErr="",code = jTACodigo.getText() + " ";
        int state = 0;
        int cell = 0;
        line = 1;
        p = 0;

        jTLisTok.setModel(new DefaultTableModel(new Object[][] {}, new String []{"Token", "Lexema", "Linea"}));
        jTLisErr.setModel(new DefaultTableModel(new Object[][]{},new String[]{"Token", "Descripción", "Lexema", "Tipo de error", "Linea"}));
        jTCont.setModel(new DefaultTableModel(new Object[][]{},new String[]{"Errores","Identificadores","Comentarios","P. Reservadas",
            "Cons. Cadena","Cons. Numerica", "Cons. real", "Cons. exponenciales","Cons. Booleanas", "Const. nula", "Operador posfix",
            "Oper. logico Binario", "Oper. de Control", "Oper. matematico", "Oper. exponente", "Oper. de turno","Oper. relacionales", 
            "Oper. sin Igualdad", "oper. logicos", "Oper. ternarios", "Oper. asignación", "Oper. agrupamiento" }));

        DefaultTableModel tableTokens = (DefaultTableModel)jTLisTok.getModel();
        DefaultTableModel tableErrors = (DefaultTableModel)jTLisErr.getModel();
        DefaultTableModel tableCounts = (DefaultTableModel)jTCont.getModel();

        while (p<code.length()){
            cha = code.charAt(p);
            cell = columna(cha);
            state = Matriz[state][cell];
            lexema=lexema.trim();
            
            if(state==-58){
                for(String fo:LisPalRes){
                    if(fo.equalsIgnoreCase(lexema)){
                        state=-59;
                        break;
                    }
                }
                if (lexema.equals("true")||lexema.equals("false")){
                    conBol++;
                    palaRes--;
                }
                if (lexema.equals("null")){
                    conNull++;
                    palaRes--;
                }
            }
            
            if (state >= 500){
                switch (state){
                    case 500:
                        type="Error Léxico";
                        desErr="Se esperaba otra cosa";
                        System.out.println("500");
                        break;
                    case 501:
                        type="Error Léxico";
                        desErr="Se esperaba un \"";
                        System.out.println("501");
                        break;
                    case 502:
                        type="Error Léxico";
                        desErr="Se esperaba un \'";
                        System.out.println("502");
                        break;
                    case 503:
                        type="Error Léxico";
                        desErr="Se esperaba un numero";
                        System.out.println("503");
                        break;
                    case 504:
                        type="Error Léxico";
                        desErr="Se esperaba un numero o un +, -";
                        System.out.println("504");
                        break;
                    case 505:
                        type="Error Léxico";
                        desErr="Esa palabra reservada no existe";
                        System.out.println("505");
                        break;
                }
                error++;
                lexema +=cha;
                if (state!=(-54)||state!=(-53)||state!=(-25)||state!=(-24)){
                    lexema=lexema.trim();
                    System.out.println("lexema: "+lexema);
                }
                errors.add(new LisError(state,desErr,lexema,type,line));
                tableErrors.addRow(new Object[]{state,desErr,lexema,type,line});
                System.out.println(state+" "+desErr);
                lexema = "";
                if (state!=500){
                    p--;
                }
                state=0;
            } else {
                if (state < 0){
                    switch (state){
                        case -3,-6:
                            posfix++;
                            break;
                        case -7,-8,-10,-13:
                            binario++;
                            break;
                        case -15,-16,-17,-18:
                            control++;
                            break;
                        case -1,-4,-19,-22,-26:
                            mate++;
                            break;
                        case -21:
                            expon++;
                            break;
                        case -30,-35,-36:
                            turno++;
                            break;
                        case -28,-33,-29,-34,-40,-44,-32:
                            relac++;
                            break;
                        case -41,-45:
                            sinIgu++;
                            break;
                        case -43,-12,-9:
                            logicos++;
                            break;
                        case -46:
                            ternarios++;
                            break;
                        case -39,-2,-5,-20,-23,-27,-11,-14,-31,-38,-37,-42:
                            asign++;
                            break;
                        case -47,-48,-49,-50,-51,-52:
                            agrupa++;
                            break;
                        case -25,-24: //comentarios
                            comen++;
                            break;
                        case -53,-54:
                            conCad++;
                            break;
                        case -55:
                            conNum++;
                            break;
                        case -56:
                            conRea++;
                            break;
                        case -57:
                            conExp++;
                            break;
                        case -58:
                            id++;
                            break;
                        case -59:
                            palaRes++;
                            switch (lexema.trim()){
                                case "true" -> state=-60;
                                case "false" -> state=-61;
                                case "null" -> state=-62;
                                case "if" -> state=-63;
                                case "else" -> state=-64;
                                case "switch" -> state=-65;
                                case "for" -> state=-66;
                                case "do" -> state=-67;
                                case "while" -> state=-68;
                                case "console.log" -> state=-69;
                                case "forEach" -> state=-70;
                                case "break" -> state=-71;
                                case "continue" -> state=-72;
                                case "let" -> state=-73;
                                case "conts" -> state=-74;
                                case "undefined" -> state=-75;
                                case "interface" -> state=-76;
                                case "typeof" -> state=-77;
                                case "Number" -> state=-78;
                                case "String" -> state=-79;
                                case "any" -> state=-80;
                                case "set" -> state=-81;
                                case "get" -> state=-82;
                                case "class" -> state=-83;
                                case "toLowerCase" -> state=-84;
                                case "toUpperCase" -> state=-85;
                                case "length" -> state=-86;
                                case "trim" -> state=-87;
                                case "charAt" -> state=-88;
                                case "startsWith" -> state=-89;
                                case "endsWith" -> state=-90;
                                case "indexOf" -> state=-91;
                                case "Includes" -> state=-92;
                                case "slice" -> state=-93;
                                case "replace" -> state=-94;
                                case "split" -> state=-95;
                                case "push" -> state=-96;
                                case "shift" -> state=-97;
                                case "in" -> state=-98;
                                case "of" -> state=-99;
                                case "splice" -> state=-100;
                                case "concat" -> state=-101;
                                case "find" -> state=-102;
                                case "findIndex" -> state=-103;
                                case "filter" -> state=-104;
                                case "map" -> state=-105;
                                case "sort" -> state=-106;
                                case "reverse" -> state=-107;
                                default -> {
                                    state=504;
                                    palaRes--;
                                }
                            }
                        break;
                    }
                    if (state==506){
                        type="Error Léxico";
                        desErr="No es palabra reservada";
                        System.out.println("506");
                        error++;
                        errors.add(new LisError(state,type,lexema,desErr,line));
                        tableErrors.addRow(new Object[]{state,type,lexema,desErr,line});
                        System.out.println("Error");
                        System.out.println(state+" "+type+" "+lexema+" "+desErr+" "+line);
                    } else {
                        switch (state){
                            case -24,-25:
                                break;
                            default:
                                tokens.add(new LisToken(state,lexema,line));
                                System.out.println("token");
                                System.out.println(state+" "+lexema+" "+line);
                                break;
                        }
                        if(state==-58){
                errorpnt = lexema.indexOf('.');
                            System.out.println(errorpnt+"error .");
                            
            }
                        if(errorpnt==0){
                            tableTokens.addRow(new Object[]{state,lexema,line});
                            
                        } else{
                            String[] compro= lexema.split("(?<=\\.|\\.)|(?=\\.|\\.)");
                            id--;
                            for(int i=0; i<compro.length;i++){
                                System.out.println(compro[i]+"posicion");
                                if(compro[i].equals(".")){
                                    control++;
                                    state=-16;
                                                                    }else{
                                    id++;
                                    state=-58;
                                }
                                tableTokens.addRow(new Object[]{state,compro[i],line});
                                
                            }
                        errorpnt=0;
                        }
                        System.out.println("Token");
                        System.out.println(state+" "+lexema+" "+line);
                    }
                    lexema = "";
                    p--;
                    state=0;
                } else {
                    lexema += cha;
                    if (cha=='\n'){
                        line+=1;
                    }
                }
            }
            p++;
        }
        if (tableCounts.getRowCount()>0){
            tableCounts.removeRow(0);
        }
        tableCounts.addRow(new Object[]{error,id,comen,palaRes,conCad,conNum,conRea,
            conExp,conBol,conNull,posfix,binario,control,mate,expon,turno,relac,sinIgu,logicos,ternarios,asign,agrupa});
    }
    private void actualizarCont(DefaultTableModel tableCounts) {
        if (tableCounts.getRowCount() > 0) {
            tableCounts.removeRow(0);
        }
        tableCounts.addRow(new Object[]{error,id,comen,palaRes,conCad,conNum,conRea,
            conExp,conBol,conNull,posfix,binario,control,mate,expon,turno,relac,sinIgu,logicos,
            ternarios,asign,agrupa});
    }
    private int columna(char cha) {
        int noCol = 0;
        switch (cha) {
            case '+':
                noCol = 0;
                break;
            case '-':
                noCol = 1;
                break;
            case '~':
                noCol = 2;
                break;
            case '|':
                noCol = 3;
                break;
            case '&':
                noCol = 4;
                break;
            case '^':
                noCol = 5;
                break;
            case ',':
                noCol = 6;
                break;
            case '.':
                noCol = 7;
                break;
            case ';':
                noCol = 8;
                break;
            case ':':
                noCol = 9;
                break;
            case '*':
                noCol = 10;
                break;
            case '/':
                noCol = 11;
                break;
            case '%':
                noCol = 12;
                break;
            case '<':
                noCol = 13;
                break;
            case '>':
                noCol = 14;
                break;
            case '=':
                noCol = 15;
                break;
            case '!':
                noCol = 16;
                break;
            case '?':
                noCol = 17;
                break;
            case '{':
                noCol = 18;
                break;
            case '}':
                noCol = 19;
                break;
            case '[':
                noCol = 20;
                break;
            case ']':
                noCol = 21;
                break;
            case '(':
                noCol = 22;
                break;
            case ')':
                noCol = 23;
                break;
            case '"':
                noCol = 24;
                break;  
            case '@':
                noCol = 28;
                break;
            case '_':
                noCol = 29;
                break;
            case ' ':
                noCol = 30;
                break;
            case '\t':
                noCol = 31;
                break;
            case '\n':
                noCol = 32;
                break;
            default:
                if (cha == '\'') {
                    noCol = 25;
                } else if (cha >= '0' && cha <= '9') {
                    noCol = 26;
                } else if ((cha >= 'a' && cha <= 'z') || (cha >= 'A' && cha <= 'Z')) {
                    noCol = 27;
                } else {
                    noCol = 33;
                }
                break;
        }
        return noCol;
    }

    private void jBExcelActionPerformed(ActionEvent e) {
        Workbook book = new XSSFWorkbook();
        String name = JOptionPane.showInputDialog("Type a name");
        Sheet sheet01 = book.createSheet("Tokens");
        Sheet sheet02 = book.createSheet("Errores");
        Sheet sheet03 = book.createSheet("Contadores");

        try{
            Row row01 = sheet01.createRow(0);
            for (int x=0;x<jTLisTok.getColumnCount();x++){
                Cell cell = row01.createCell(x);
                cell.setCellValue(jTLisTok.getColumnName(x));
            }
            for (int y=0; y< jTLisTok.getRowCount();y++){
                Row row = sheet01.createRow(y);
                for (int z=0;z<jTLisTok.getColumnCount();z++){
                    Cell cell = row.createCell(z);
                    if (jTLisTok.getValueAt(y,z)!=null){
                        cell.setCellValue(jTLisTok.getValueAt(y,z).toString());
                    }
                }
            }
            Row row02 = sheet02.createRow(0);
            for (int x=0;x<jTLisErr.getColumnCount();x++){
                Cell cell = row02.createCell(x);
                cell.setCellValue(jTLisErr.getColumnName(x));
            }
            for (int y=0; y< jTLisErr.getRowCount();y++){
                Row row = sheet02.createRow(y);
                for (int z=0;z<jTLisErr.getColumnCount();z++){
                    Cell cell = row.createCell(z);
                    if (jTLisErr.getValueAt(y,z)!=null){
                        cell.setCellValue(jTLisErr.getValueAt(y,z).toString());
                    }
                }
            }
            Row row03 = sheet03.createRow(0);
            for (int x=0;x<jTCont.getColumnCount();x++){
                Cell cell = row03.createCell(x);
                cell.setCellValue(jTCont.getColumnName(x));
            }
            for (int y=0; y< jTCont.getRowCount();y++){
                Row row = sheet03.createRow(y);
                for (int z=0;z<jTCont.getColumnCount();z++){
                    Cell cell = row.createCell(z);
                    if (jTCont.getValueAt(y,z)!=null){
                        cell.setCellValue(jTCont.getValueAt(y,z).toString());
                    }
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(name+".xlsx");
            book.write(fileOutputStream);
            book.close();
            fileOutputStream.close();

        } catch (FileNotFoundException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //SIRVE
    private void jBCargarActionPerformed(ActionEvent e) {
        JFileChooser menu = new JFileChooser();
        menu.showOpenDialog(null);
        File file = menu.getSelectedFile();
        nombre = file.getAbsolutePath();

        try {
            FileReader leer = new FileReader(nombre);
            BufferedReader br = new BufferedReader(leer);
            jTACodigo.read(br, null);
            jTACodigo.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent de) {
                    no.actualiza();
                }
                @Override
                public void insertUpdate(DocumentEvent de) {
                    no.actualiza();
                }
                @Override
                public void removeUpdate(DocumentEvent de) {
                    no.actualiza();
                }
            });
            br.close();
            jTACodigo.requestFocus();
            no.actualiza();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }

    public static void ExcelReader() throws IOException {
        try{
            //Abre archivo
            FileInputStream file = new FileInputStream(new File("Matriz.xlsx"));
            XSSFWorkbook book = new XSSFWorkbook(file);
            //obtiene la primer hoja del libro
            XSSFSheet sheet = book.getSheetAt(0);
            int fila = sheet.getLastRowNum()-sheet.getFirstRowNum();
            int columna = sheet.getRow(0).getLastCellNum()-1;
            Matriz = new int[fila][columna];
            
            //iteraciones
            for (int x=1; x<=sheet.getLastRowNum();x++){
                Row row = sheet.getRow(x);
                for (int y=1;y<row.getLastCellNum();y++){
                    Cell cell = row.getCell(y);
                    if (cell!=null){
                        int value = (int) cell.getNumericCellValue();
                        Matriz[x-1][y-1]=value;
                    }
                }
            }
            for(int x=0;x<fila;x++){
                for(int y=0;y<columna;y++){
                    System.out.print(Matriz[x][y]+" ");
                }
                System.out.println();
            } 
            file.close();
        } catch (FileNotFoundException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

    private void jTFContLexActionPerformed(java.awt.event.ActionEvent evt) {
    }
    // Variables declaration
    private javax.swing.JButton jBCargar;
    private javax.swing.JButton jBCompilar;
    private javax.swing.JButton jBExcel;
    private javax.swing.JLabel jLCodigo;
    private javax.swing.JLabel jLCont;
    private javax.swing.JLabel jLContTErr;
    private javax.swing.JLabel jLLex;
    private javax.swing.JLabel jLLisErr;
    private javax.swing.JLabel jLLisTok;
    private javax.swing.JLabel jLSin;
    private javax.swing.JPanel jPCodigo;
    private javax.swing.JPanel jPContadores;
    private javax.swing.JPanel jPFondo;
    private javax.swing.JPanel jPLisErr;
    private javax.swing.JPanel jPLisTok;
    private javax.swing.JPanel jPTCont;
    private javax.swing.JPanel jPTLisErr;
    private javax.swing.JPanel jPTLisTok;
    private javax.swing.JPanel jPTextArea;
    private javax.swing.JScrollPane jSPTCont;
    private javax.swing.JScrollPane jSPTError;
    private javax.swing.JScrollPane jSPTLisTok;
    private javax.swing.JScrollPane jSPTextArea;
    private javax.swing.JTextArea jTACodigo;
    private javax.swing.JTable jTCont;
    private javax.swing.JTextField jTFContLex;
    private javax.swing.JTextField jTFContSin;
    private javax.swing.JTable jTLisErr;
    private javax.swing.JTable jTLisTok;
    TxtArea no;
    private String nombre;
    private int id,posfix,binario,control,mate,expon,turno,
            relac,sinIgu,logicos,ternarios,asign,agrupa,
            palaRes,comen,conCad,conNum,conRea,conExp,conBol,
            conNull,error;
    private static int[][] Matriz;
    private int line, p;
    private LinkedList <LisError> errors;
    private LinkedList <LisToken> tokens;
    private List <String> LisPalRes;
    int errorpnt;
}
