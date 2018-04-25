/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ReceituarioDAO;
import br.edu.ifsul.dao.MedicamentoDAO;
import br.edu.ifsul.modelo.Medicamento;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author romulo
 */
@ManagedBean(name = "controleMedicamento")
@SessionScoped
public class ControleMedicamento implements Serializable{
    
    private MedicamentoDAO dao;
    private Medicamento objeto;
    private ReceituarioDAO daoReceituario;
    
    public ControleMedicamento(){
        dao = new MedicamentoDAO();
        daoReceituario = new ReceituarioDAO();
    }
    
    public String listar(){
        return "/privado/medicamento/listar?faces-redirect=true";
    }
    
    public String novo(){
        setObjeto(new Medicamento());
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

    public MedicamentoDAO getDao() {
        return dao;
    }

    public void setDao(MedicamentoDAO dao) {
        this.dao = dao;
    }

    public Medicamento getObjeto() {
        return objeto;
    }

    public void setObjeto(Medicamento objeto) {
        this.objeto = objeto;
    }

    public ReceituarioDAO getDaoReceituario() {
        return daoReceituario;
    }

    public void setDaoReceituario(ReceituarioDAO daoReceituario) {
        this.daoReceituario = daoReceituario;
    }
}
