/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atccompilador;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    ArrayList<String> palavrasReservadas = new ArrayList<String>() {
        {
            add("inicio");
            add("fim");
            add("se");
            add("entao");
            add("senao");
            add("fimse");
        }
    };

    public Tela() {
        initComponents();

        atualizarLista(this.palavrasReservadas);

        txtCodigo.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "AnaliseLexica");
        txtCodigo.getActionMap().put("AnaliseLexica", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analiseLexica();
            }
        });

    }

    private void atualizarLista(ArrayList<String> elementos) {
        for (int i = 0; i < elementos.size(); i++) {
            dlmPalavrasReservadas.add(i, elementos.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnAddPalavra = new javax.swing.JButton();
        txtPalavraReservada = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        txtCodigo.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(txtCodigo);

        jList1.setModel(dlmPalavrasReservadas);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddPalavra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPalavraReservada))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPalavraReservada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddPalavra)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddPalavraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPalavraActionPerformed
        // TODO add your handling code here:
        String palavra = txtPalavraReservada.getText();
        palavra = palavra.replace(" ", "");

        if (!palavra.isEmpty()) {

            boolean isNumber = palavra.matches("^-?\\d*([\\.,]\\d+)?$");
            if (!isNumber) {
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
        Compilador compiler = new Compilador();

        compiler.setPalavrasReservadas(palavrasReservadas);

        AnalisadorLexico al = new AnalisadorLexico();
        List<String> palavras = getTextCode();
        EstruturaLexica estruturaLexica = al.analisar(compiler, palavras);
        txtCodigo.setText(estruturaLexica.getEstruturaHtml());
        analiseSemantica(estruturaLexica);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane txtCodigo;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtPalavraReservada;
    // End of variables declaration//GEN-END:variables
}
