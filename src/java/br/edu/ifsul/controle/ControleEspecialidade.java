/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author romulo
 */
@ManagedBean(name = "controleEspecialidade")
@SessionScoped
public class ControleEspecialidade implements Serializable{
    
    private EspecialidadeDAO dao;
    private Especialidade objeto;
    
    public ControleEspecialidade(){
        dao = new EspecialidadeDAO();
    }
    
    public String listar(){
        return "/privado/especialidade/listar?faces-redirect=true";
    }
    
    public String novo(){
        setObjeto(new Especialidade());
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if(getDao().salvar(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
            return "listar?faces-redirect=true";
        }
        else{
            Util.mensagemErro(getDao().getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        setObjeto(getDao().localizar(id));
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if(dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else
            Util.mensagemErro(dao.getMensagem());
    }

    public EspecialidadeDAO getDao() {
        return dao;
    }

    public void setDao(EspecialidadeDAO dao) {
        this.dao = dao;
    }

    public Especialidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Especialidade objeto) {
        this.objeto = objeto;
    }
}
