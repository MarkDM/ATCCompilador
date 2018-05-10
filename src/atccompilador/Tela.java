/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atccompilador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author marcu
 */
public class Tela extends javax.swing.JFrame {

    DefaultListModel<String> dlmPalavrasReservadas = new DefaultListModel();
    Long ultimaVezDigitou = System.currentTimeMillis();
    boolean digitando = false;
    boolean analisando = false;
    ArrayList<String> palavrasReservadas = new ArrayList<String>() {
        {
            add("inicio");
            add("fim");
            add("se");
            add("entao");
            add("senao");
            add("fimse");
            add("var");
            add("escreva");
        }
    };

    ArrayList<String> listaVariaveis = new ArrayList<String>();

    public Tela() {
        initComponents();

        atualizarLista(this.palavrasReservadas);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//
//                    Long now = System.currentTimeMillis();
//                    if (now - ultimaVezDigitou > 1000) {
//                        digitando = false;
//                        System.out.println("Parou de digitar: tempo sem digitar " + String.valueOf(now - ultimaVezDigitou) + " ms");
//                        try {
//
//                            analiseLexica();
//
//                            Thread.sleep(500);
//                        } catch (InterruptedException ex) {
//                            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                }
//            }
//        }).start();
        txtCodigo.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ultimaVezDigitou = System.currentTimeMillis();
                digitando = true;
                analisando = false;

            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        definirTeclaAnalise("TAB");

    }

    private void definirTeclaAnalise(String tecla) {
        txtCodigo.getInputMap().clear();
        txtCodigo.getActionMap().clear();

        txtCodigo.getInputMap().put(KeyStroke.getKeyStroke(tecla), "AnaliseLexica");
        txtCodigo.getActionMap().put("AnaliseLexica", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obterVariaveis();
                analiseLexica();
            }
        });
    }

    public void obterVariaveis() {
        String textoPlano = txtCodigo.getText().toString();

        Pattern p = Pattern.compile("var (\\w(,( )?)?)+", Pattern.DOTALL);
        String regAux = "";
        List<String> lista = findAllByRegEx(p, textoPlano);

        for (String s : lista) {
            regAux = s.toLowerCase().replace("var", "").trim();
            String[] arrVariaveis = regAux.split(",");

            for (String j : arrVariaveis) {

                System.out.println(j);
            }
        }

    }

    private void atualizarLista(ArrayList<String> elementos) {
        dlmPalavrasReservadas.removeAllElements();
        for (int i = 0; i < elementos.size(); i++) {
            dlmPalavrasReservadas.add(i, elementos.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnAddPalavra = new javax.swing.JButton();
        txtPalavraReservada = new javax.swing.JTextField();
        cbTeclasAnalise = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstVariaveis = new javax.swing.JList<>();

        jLabel2.setText("Tecla que dispara análise léxica");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SPACE", "F1", "F2", "CTRL" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ATC Compilador");
        setResizable(false);

        txtCodigo.setContentType("text/html"); // NOI18N
        txtCodigo.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(txtCodigo);

        jList1.setModel(dlmPalavrasReservadas);
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jList1KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        txtLog.setBackground(new java.awt.Color(0, 0, 0));
        txtLog.setColumns(20);
        txtLog.setForeground(new java.awt.Color(102, 255, 0));
        txtLog.setRows(5);
        jScrollPane3.setViewportView(txtLog);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Palavras Reservadas");

        btnAddPalavra.setText("Adicionar");
        btnAddPalavra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPalavraActionPerformed(evt);
            }
        });

        cbTeclasAnalise.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TAB", "SPACE", "F1", "F2" }));
        cbTeclasAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTeclasAnaliseActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Tecla de análise");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Variáveis");

        jScrollPane5.setViewportView(lstVariaveis);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddPalavra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbTeclasAnalise, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5))
                                .addGap(97, 97, 97))
                            .addComponent(txtPalavraReservada, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(7, 7, 7)
                        .addComponent(cbTeclasAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPalavraReservada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddPalavra))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddPalavraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPalavraActionPerformed
        // TODO add your handling code here:
        String palavra = txtPalavraReservada.getText().toLowerCase();
        palavra = palavra.replace(" ", "");

        if (!palavra.isEmpty()) {

            boolean isNumber = palavra.matches("^-?\\d*([\\.,]\\d+)?$");
            if (!isNumber) {

                if (palavrasReservadas.contains(palavra)) {
                    JOptionPane.showMessageDialog(this, "Palavra já existe");
                    return;
                }

                palavrasReservadas.add(palavra);
                dlmPalavrasReservadas.clear();
                atualizarLista(palavrasReservadas);
            } else {
                JOptionPane.showMessageDialog(this, "Palavra reservada não pode ser vazio!");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Palavra reservada não pode ser vazio!");
        }
    }//GEN-LAST:event_btnAddPalavraActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void cbTeclasAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTeclasAnaliseActionPerformed
        System.out.println(cbTeclasAnalise.getSelectedItem());
        definirTeclaAnalise(cbTeclasAnalise.getSelectedItem().toString());
    }//GEN-LAST:event_cbTeclasAnaliseActionPerformed

    private void jList1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyTyped

    }//GEN-LAST:event_jList1KeyTyped

    private void jList1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int selectionado = jList1.getSelectedIndex();
            palavrasReservadas.remove(selectionado);
            atualizarLista(palavrasReservadas);
        }


    }//GEN-LAST:event_jList1KeyPressed

    public List<String> findAllByRegEx(Pattern p, String txt) {
        Matcher m = p.matcher(txt);
        List<String> ret = new ArrayList<>();

        while (m.find()) {
            ret.add(m.group());
        }

        return ret;
    }

    public String findByRegex(Pattern p, String texto) {

        Matcher m = p.matcher(texto);
        String ret = "";

        if (m.find()) {
            ret = m.group();
        }

        return ret;

    }

    /**
     * Retorna o texto da area de codigo através de expressão regular
     *
     * @return Lista com as palavras encontradas
     */
    private List<String> getTextCode() {

        List<String> palavras = new ArrayList<>();
        String regAux = "";

        String txt1 = txtCodigo.getText();

        Pattern pat = Pattern.compile(">(.*?)<", Pattern.DOTALL);
        Matcher mat = pat.matcher(txt1);

        while (mat.find()) {

            regAux = mat.group();
            regAux = regAux.replace("<", "").replace(">", "").trim();

            if (!regAux.equals("")) {
                palavras.add(regAux);
            }

        }

        List<String> ret = new ArrayList<>();

        for (String s1 : palavras) {
            for (String s2 : s1.split(" ")) {
                ret.add(s2);
                ret.add("");

            }
        }

        return ret;

    }

    private void analiseLexica() {

//        if (digitando || analisando) {
//            return;
//        }
//        analisando = true;
        Compilador compiler = new Compilador();

        compiler.setPalavrasReservadas(palavrasReservadas);

        AnalisadorLexico al = new AnalisadorLexico();
        List<String> palavras = getTextCode();
        EstruturaLexica estruturaLexica = al.analisar(compiler, palavras);
        txtCodigo.setText(estruturaLexica.getEstruturaHtml());

        if (estruturaLexica.getEstruturaTipos().size() > 0) {
            analiseSemantica(estruturaLexica);
        }

    }

    private void analiseSemantica(EstruturaLexica el) {
        AnalisadorSemantico as = new AnalisadorSemantico(el.getEstruturaTipos());
        List<String> erros = as.analisar();

        if (erros != null) {
            txtLog.setText("");
            for (String erro : erros) {
                txtLog.append(erro);
            }
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Tela t = new Tela();

                t.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPalavra;
    private javax.swing.JComboBox<String> cbTeclasAnalise;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList<String> lstVariaveis;
    private javax.swing.JTextPane txtCodigo;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtPalavraReservada;
    // End of variables declaration//GEN-END:variables
}
