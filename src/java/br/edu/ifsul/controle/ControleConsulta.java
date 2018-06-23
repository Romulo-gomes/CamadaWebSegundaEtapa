/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.ExameDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.dao.ReceituarioDAO;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Exame;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.modelo.Receituario;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author romulo
 */
@ManagedBean(name="controleConsulta")
@SessionScoped
public class ControleConsulta implements Serializable{
    
    
    private ConsultaDAO<Consulta> dao;
    private Consulta objeto;
    private MedicoDAO daoMedico;
    private PacienteDAO daoPaciente;
    private ExameDAO<Exame> daoExame;
    private ReceituarioDAO daoReceituario;
    private Exame exame;
    private Boolean novoExame;
    private Paciente paciente;
    private Receituario receituario;
    private Boolean novoReceituario;
    
    
    public ControleConsulta(){
        dao = new ConsultaDAO<>();
        daoMedico = new MedicoDAO();
        daoPaciente = new PacienteDAO();
        daoExame = new ExameDAO<>();
        daoReceituario = new ReceituarioDAO();
    }
    
    public void imprimeRelatorioExames(){
        HashMap hashMap = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatoriosExames", hashMap, 
                daoExame.getListaTodos());
        
    }
    
    public void novoExame(){
        exame = new Exame();
        novoExame = true;
    }
    
    public void alterarExame(int index){
        exame = objeto.getExame().get(index);
        novoExame = false;
    }
    
    public void salvarExame(){
        if(novoExame){
            objeto.adicionaExame(exame);
        }
        Util.mensagemInformacao("Exame atualizado com sucesso");
    }
    
    public void removerExame(int index){
        objeto.removeExame(index);
        Util.mensagemInformacao("Exame removido com sucesso");
    }
    
    public void novoReceituario(){
        receituario = new Receituario();
        novoReceituario = true;
    }
    
    public void alterarReceituario(int index){
        receituario = objeto.getReceituario().get(index);
        novoReceituario = false;
    }
    
    public void salvarReceituario(){
        if(novoReceituario){
            objeto.adicionaReceita(receituario);
        }
        Util.mensagemInformacao("Receituario atualizado com sucesso");
    }
    
    public void removerReceituario(int index){
        objeto.removeReceita(index);
        Util.mensagemInformacao("Receituario removido com sucesso");
    }
    
    public String listar(){
        return "/privado/consulta/listar?faces-redirect=true";
    }
    
    public String novo(){
        setObjeto(new Consulta());
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        boolean persistiu = false;
        if(objeto.getId()== null){
            persistiu = dao.persist(objeto);
        }
        else{
            persistiu = dao.merge(objeto);
        }
        if(persistiu){
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
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if(dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else
            Util.mensagemErro(dao.getMensagem());
    }

    public ConsultaDAO<Consulta> getDao() {
        return dao;
    }

    public void setDao(ConsultaDAO<Consulta> dao) {
        this.dao = dao;
    }

    public Consulta getObjeto() {
        return objeto;
    }

    public void setObjeto(Consulta objeto) {
        this.objeto = objeto;
    }

    public MedicoDAO getDaoMedico() {
        return daoMedico;
    }

    public void setDaoMedico(MedicoDAO daoMedico) {
        this.daoMedico = daoMedico;
    }

    public PacienteDAO getDaoPaciente() {
        return daoPaciente;
    }

    public void setDaoPaciente(PacienteDAO daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public ExameDAO<Exame> getDaoExame() {
        return daoExame;
    }

    public void setDaoExame(ExameDAO<Exame> daoExame) {
        this.daoExame = daoExame;
    }

    public ReceituarioDAO getDaoReceituario() {
        return daoReceituario;
    }

    public void setDaoReceituario(ReceituarioDAO daoReceituario) {
        this.daoReceituario = daoReceituario;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public Boolean getNovoExame() {
        return novoExame;
    }

    public void setNovoExame(Boolean novoExame) {
        this.novoExame = novoExame;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Receituario getReceituario() {
        return receituario;
    }

    public void setReceituario(Receituario receituario) {
        this.receituario = receituario;
    }

    public Boolean getNovoReceituario() {
        return novoReceituario;
    }

    public void setNovoReceituario(Boolean novoReceituario) {
        this.novoReceituario = novoReceituario;
    }
}
