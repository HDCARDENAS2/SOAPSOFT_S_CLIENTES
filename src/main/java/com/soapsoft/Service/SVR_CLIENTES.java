/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soapsoft.Service;

import com.soapsoft.Dao.TbClientesDaoImpl;
import com.soapsoft.Model.TbClientes;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author PC
 */
@WebService(serviceName = "SVR_CLIENTES")
public class SVR_CLIENTES {

    /**
     * Web service operation
     * @param nit
     * @param razon_social
     * @param telefono
     * @param celular
     * @param contacto
     * @param estado
     * @param direccion
     * @param creadoPor
     * @return 
     */
    @WebMethod(operationName = "fn_insertar")
    public String fn_insertar(@WebParam(name = "nit") String nit, 
                              @WebParam(name = "razon_social") String razon_social, 
                              @WebParam(name = "telefono") String telefono, 
                              @WebParam(name = "celular") String celular, 
                              @WebParam(name = "contacto") int contacto, 
                              @WebParam(name = "estado") boolean estado,
                              @WebParam(name = "direccion") String direccion, 
                              @WebParam(name = "creadoPor") String creadoPor
                              ) {
        
        TbClientes obj = new TbClientes(nit, razon_social, telefono, celular, contacto, estado, direccion, creadoPor,new Date());
        
        try {
            
            TbClientesDaoImpl dao = new TbClientesDaoImpl();
            dao.create(obj);
            return "Se inserto el cliente";
        
        } catch (Exception e) {
             throw e;    
        }
        
    }

    /**
     * Web service operation
     * @param ID
     * @return 
     */
    @WebMethod(operationName = "fn_borrar")
    public String fn_borrar(@WebParam(name = "ID") int ID
                            ) {
        
          try {
            
            TbClientesDaoImpl dao = new TbClientesDaoImpl();
            
            TbClientes obj = dao.findById(ID);

            if(obj != null ){
                dao.delete(obj); 
            }else{
                return "El cliente no existe";
            }
            return "Se elimino el cliente";
        
        } catch (Exception e) {
             throw e;    
        }
        
    }
    
       /**
     * Web service operation
     * @param ID
     * @param nit
     * @param razon_social
     * @param telefono
     * @param celular
     * @param contacto
     * @param estado
     * @param direccion
     * @param modificadoPor
     * @return 
     */
    @WebMethod(operationName = "fn_modificar")
    public String fn_modificar(@WebParam(name = "ID") int ID,
                               @WebParam(name = "nit") String nit, 
                               @WebParam(name = "razon_social") String razon_social, 
                               @WebParam(name = "telefono") String telefono, 
                               @WebParam(name = "celular") String celular, 
                               @WebParam(name = "contacto") int contacto, 
                               @WebParam(name = "estado") boolean estado, 
                               @WebParam(name = "direccion") String direccion, 
                               @WebParam(name = "modificadoPor") String modificadoPor
                               ) {
        
        try {
            
          
            TbClientesDaoImpl dao = new TbClientesDaoImpl();
            TbClientes obj_modificar = dao.findById(ID);
            
            if(obj_modificar != null ){
                
                obj_modificar.setNit(nit);
                obj_modificar.setRazonSocial(razon_social);
                obj_modificar.setTelefono(telefono);
                obj_modificar.setCelular(celular);
                obj_modificar.setContacto(contacto);
                obj_modificar.setEstado(estado);
                obj_modificar.setDireccion(direccion);
                obj_modificar.setModificadoPor(modificadoPor);
                obj_modificar.setModificadoEn(new Date());

                dao.update(obj_modificar);
                
            }else{
                return "El cliente no existe";
            }
            
            return "Se modifico el cliente";
        
        } catch (Exception e) {
             throw e;    
        }
        
    }

    
    
    
 
}
