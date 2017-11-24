/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soapsoft.Service;

import com.soapsoft.Dao.TbFacturaVentaDaoImpl;
import com.soapsoft.Model.TbClientes;
import com.soapsoft.Model.TbDetalleFacturaVenta;
import com.soapsoft.Model.TbFacturaVenta;

import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author felip
 */
@WebService(serviceName = "SVR_FACTURAVENTA")
public class SVR_FACTURAVENTA {

    /**
     * Web service operation
     * @param observacion
     * @param idCliente
     * @param creadoPor
     * @return
     */
    
    @WebMethod(operationName = "fn_insertar_factura_venta")
    public String fn_insertar_factura_venta(@WebParam(name = "observacion") String observacion, @WebParam(name = "id_cliente") int id_cliente, @WebParam(name = "creadoPor") String creadoPor) {
        
        TbClientes tbClientes = new TbClientes();
        tbClientes.setId(id_cliente);
        
        TbFacturaVenta objFacturaVenta = new TbFacturaVenta(observacion, tbClientes, creadoPor, new Date());
        
        try {
            TbFacturaVentaDaoImpl daoFacturaVenta = new TbFacturaVentaDaoImpl();
            daoFacturaVenta.create(objFacturaVenta);
            return "Se inserto la factura de la venta";
        } catch (Exception e) {
            throw e;
        }
        
        
    }

    /**
     * Web service operation
     * @param ID
     */
    
    @WebMethod(operationName = "fn_borrar_factura_venta")
    public String fn_borrar_factura_venta(@WebParam(name = "ID") int ID) {
        
        try {
            TbFacturaVentaDaoImpl daoFacturaVenta = new TbFacturaVentaDaoImpl();
            TbFacturaVenta objFacturaVenta =daoFacturaVenta.findById(ID);
            
            if(objFacturaVenta != null){
                daoFacturaVenta.delete(objFacturaVenta);
            }else{
                return "La factura no Existe";
            }
            return "Se elimino la factura";
            
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "fn_modificar_factura_venta")
    public String fn_modificar_factura_venta(@WebParam(name = "ID") int ID, 
                                             @WebParam(name = "observacion") String observacion, 
                                             @WebParam(name = "id_cliente") int id_cliente, 
                                             @WebParam(name = "modificadoPor") String modificadoPor) {
        try {
            TbFacturaVentaDaoImpl daoFacturaVentaDaoImpl = new TbFacturaVentaDaoImpl();
            TbFacturaVenta obj_modificar = daoFacturaVentaDaoImpl.findById(ID);
            
            TbClientes tbCliente = new TbClientes();
            tbCliente.setId(id_cliente);
            
            if(obj_modificar != null){
                obj_modificar.setId(ID);
                obj_modificar.setObservacion(observacion);
                obj_modificar.setTbClientes(tbCliente);
                obj_modificar.setModificadoPor(modificadoPor);
                obj_modificar.setModificadoEn(new Date());
                
                daoFacturaVentaDaoImpl.update(obj_modificar);
            }else{
                return "La Factura Venta No Existe";
            }
            
            return "Se Modifico la Factura Venta";
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    
    
    
}
