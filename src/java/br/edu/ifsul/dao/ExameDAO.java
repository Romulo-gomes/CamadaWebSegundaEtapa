/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Exame;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author romulo
 */
public class ExameDAO<TIPO> extends DAOGenerico<Exame> implements Serializable{
    
    public ExameDAO(){
        super();
        classePersistencia = Exame.class;
        ordem = "nome";
    }
   
}
