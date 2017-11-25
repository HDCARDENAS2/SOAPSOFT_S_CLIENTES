/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soapsoft.Dao;

import com.soapsoft.Model.TbClientes;
import java.util.List;

/**
 *
 * @author PC
 */
public interface TbClientesDao extends GenericDao<TbClientes, Integer>{
           public List<TbClientes>  fn_consultar_nit(int nit);
}
