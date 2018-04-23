/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atccompilador;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author marcu
 */
public class AnalisadorLexico {

    public EstruturaLexica analisar(Compilador compilador, List<String> lsCodigo) {

        EstruturaLexica el = new EstruturaLexica();
        String html = "";
        boolean blankSpace = false;

        String[] arrAux = new String[lsCodigo.size()];
        String[] codigo = lsCodigo.toArray(arrAux);

        for (int i = 0; i < codigo.length; i++) {

            if (codigo[i].isEmpty()) {
                //html += " ";
                blankSpace = true;
                continue;
            }

            if (codigo[i].equals("<br>")) {
                html += "<br>";
                continue;
            }

            //Se começa com ' então avança até achar a próxima ', aí então conta tudo como string
            if (codigo[i].startsWith("'")) {
                el.getEstruturaTipos().add(Tipos.STRING);
                html += "<span style=\"color:green;\">";

                while (i < codigo.length) {

                    if (codigo[i].isEmpty()) {
                        //html += " ";
                        blankSpace = true;
                        i++;
                        continue;
                    }

                    if (codigo[i].endsWith("'")) {
                        html += (blankSpace) ? " " + codigo[i] : codigo[i];
                        break;
                    } else {
                        html += (blankSpace) ? " " + codigo[i] : codigo[i];
                        i++;
                    }
                }
                if (i >= codigo.length) {

                    break;
                }
                i++;

                html += "</span>";
            }

            //Verifica se o texto atual está contido no array de palavras reservadas
            boolean isKeyWord = Arrays.asList(compilador.getPalavrasReservadas()).contains(codigo[i].toLowerCase());
            //Verifica se é numero através de REGEX
            boolean isNumber = codigo[i].matches("^[0-9]*[.]{0,1}[0-9]*$");
            //Se não é nada acima, então é variável
            boolean isVariavel = !isKeyWord && !isNumber;

            if (isKeyWord) {
                el.getEstruturaTipos().add(Tipos.KEY_WORD);
                html += "<span style=\"color:blue;\">" + ((blankSpace) ? " " + codigo[i] : codigo[i]) + "</span>";
            }

            if (isNumber) {
                el.getEstruturaTipos().add(Tipos.NUMBER);
                html += "<span style=\"color:red;\">" + ((blankSpace) ? " " + codigo[i] : codigo[i]) + "</span>";
            }

            if (isVariavel) {
                el.getEstruturaTipos().add(Tipos.VARIAVEL);
                html += "<span style=\"color:#FF8F40;\">" + ((blankSpace) ? " " + codigo[i] : codigo[i]) + "</span>";
            }

            blankSpace = false;
        }
        el.setEstruturaHtml(html);
        return el;
    }
}
