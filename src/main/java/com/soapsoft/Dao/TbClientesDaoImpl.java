/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soapsoft.Dao;

import com.soapsoft.Model.TbClientes;
import com.soapsoft.Util.LlaveValor;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author PC
 */
public class TbClientesDaoImpl extends GenericDaoImpl<TbClientes, Integer> implements TbClientesDao{
    
       public List<TbClientes>  fn_consultar_nit(int nit){
           
           
            List<TbClientes> resultado = null;

            try{ 

                String query = "SELECT Id, razon_social as RazonSocial  FROM tb_clientes WHERE nit = :p_nit  ";

                ArrayList<LlaveValor> parametros = new ArrayList<>();
                parametros.add(new LlaveValor("p_nit", nit));

                resultado = this.Query(query, parametros,TbClientes.class);

                } catch (Exception e) {
                    throw e;
                }

            return resultado;
          
       }
       
       public List<TbClientes>  fn_consultar_todos_clie(){
          
            List<TbClientes> resultado = null;

            try{ 

                String query = "SELECT Id, nit as Nit, razon_social as RazonSocial,telefono as Telefono, celular as Celular,direccion as Direccion  FROM  tb_clientes ORDER BY RazonSocial asc";

                ArrayList<LlaveValor> parametros = new ArrayList<>();

                resultado = this.Query(query, parametros,TbClientes.class);

                } catch (Exception e) {
                    throw e;
                }

            return resultado;
          
       }
    
}

