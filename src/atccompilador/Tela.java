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
import javax.swing.KeyStroke;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author marcu
 */
public class Tela extends javax.swing.JFrame {

    public Tela() {
        initComponents();
        txtCodigo.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "AnaliseLexica");
        txtCodigo.getActionMap().put("AnaliseLexica", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analiseLexica();
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextPane();
        btnRunAnalLexico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtCodigo.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(txtCodigo);

        btnRunAnalLexico.setText("Rodar Anal léxico");
        btnRunAnalLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunAnalLexicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 729, Short.MAX_VALUE)
                        .addComponent(btnRunAnalLexico)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRunAnalLexico)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRunAnalLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunAnalLexicoActionPerformed
        analiseLexica();
    }//GEN-LAST:event_btnRunAnalLexicoActionPerformed

    /**
     * Retorna o texto da area de codigo através de expressão regular
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
        String[] palavrasReservadas = {"inicio", "fimInicio", "var", "se", "entao", "fimSe","senao","senao se","escreva"};
        compiler.setPalavrasReservadas(palavrasReservadas);

        AnalisadorLexico al = new AnalisadorLexico();
        List<String> palavras = getTextCode();
        EstruturaLexica estruturaLexica = al.analisar(compiler, palavras);
        txtCodigo.setText(estruturaLexica.getEstruturaHtml());
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
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
                t.txtCodigo.setText("'Teste de string' INICIO SE variavel ENTAO ESCREVA 'variável' numero = 22.55");
                t.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRunAnalLexico;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane txtCodigo;
    // End of variables declaration//GEN-END:variables
}
