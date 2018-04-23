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
public class EstruturaLexica {

    private List<Tipos> estruturaTipos;
    private String estruturaHtml;
    
    public EstruturaLexica(){
        estruturaTipos = new ArrayList<>();
    }

    public List<Tipos> getEstruturaTipos() {
        return estruturaTipos;
    }


    public String getEstruturaHtml() {
        return estruturaHtml;
    }

    public void setEstruturaHtml(String estruturaHtml) {
        this.estruturaHtml = estruturaHtml;
    }
    



}
