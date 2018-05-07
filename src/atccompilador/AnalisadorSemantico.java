/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atccompilador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcu
 */
public class AnalisadorSemantico {

    private List<Tipos> tipos;

    public AnalisadorSemantico(List<Tipos> tipos) {
        this.tipos = tipos;
    }

    public List<String> analisar() {
        
        List<String> erros = new ArrayList<>();
            boolean disparoSe = false;
            boolean achoEntao = false;
            boolean achoFimSe = false;

        try {

            

            if (this.tipos.get(0) != Tipos.INICIO) {
                erros.add("codigo não começa com palavra INICIO!\n\r");
            }

            if (this.tipos.get(this.tipos.size() - 1) != Tipos.FIM) {
                erros.add("codigo não termina com palavra FIM!\n\r");
            }

            for (int i = 0; i < this.tipos.size(); i++) {

//            if (i == this.tipos.size() - 1)
//                    break;
                if (this.tipos.get(i) == Tipos.SE) {
                    disparoSe = true;

                    if (this.tipos.get(i + 1) == Tipos.KEY_WORD) {
                        erros.add("Apos palavra SE não pode vim uma palavra reservada!");
                    }
                }
                if (this.tipos.get(i) == Tipos.ENTAO) {
                    achoEntao = true;
                }
                if (this.tipos.get(i) == Tipos.FIMSE) {
                    achoFimSe = true;
                }
            }

            if (disparoSe) {
                if (!achoEntao) {
                    erros.add("Palavra ENTAO não encontrada após SE!\n\r");
                }
                if (!achoFimSe) {
                    erros.add("SE não fechado com FIMSE!\n\r");
                }
            }

            return erros;
        } catch (Exception e) {
            return erros;
        }

    }

    public List<Tipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipos> tipos) {
        this.tipos = tipos;
    }
}
