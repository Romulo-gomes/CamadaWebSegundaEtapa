/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author romulo
 */
@ManagedBean(name = "controleMedico")
@SessionScoped
public class ControleMedico implements Serializable{
    
    private MedicoDAO dao;
    private Medico objeto;
    private EspecialidadeDAO daoEspecialidade;
    
    public ControleMedico(){
        dao = new MedicoDAO();
        daoEspecialidade = new EspecialidadeDAO();
    }
    
    public String listar(){
        return "/privado/medico/listar?faces-redirect=true";
    }
    
    public String novo(){
        setObjeto(new Medico());
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

    public MedicoDAO getDao() {
        return dao;
    }

    public void setDao(MedicoDAO dao) {
        this.dao = dao;
    }

    public Medico getObjeto() {
        return objeto;
    }

    public void setObjeto(Medico objeto) {
        this.objeto = objeto;
    }

    public EspecialidadeDAO getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDAO daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }
}
