 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soapsoft.Service;

import com.soapsoft.Dao.TbDetalleFacturaVentaDao;
import com.soapsoft.Dao.TbDetalleFacturaVentaDaoImpl;
import com.soapsoft.Model.TbDetalleFacturaVenta;
import com.soapsoft.Model.TbFacturaVenta;
import com.soapsoft.Model.TbProductoTerminado;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author felip
 */
@WebService(serviceName = "SRV_DETALLEFACTURAVENTA")
public class SRV_DETALLEFACTURAVENTA {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "fn_insertar_detalle_factura_venta")
    public String fn_insertar_detalle_factura_venta(@WebParam(name = "id_factura_venta") int id_factura_venta, @WebParam(name = "id_producto") int id_producto, @WebParam(name = "cantidad") int cantidad, @WebParam(name = "vlor_iva") int vlor_iva, @WebParam(name = "tipo_iva") String tipo_iva, @WebParam(name = "vlor_unitario") int vlor_unitario, @WebParam(name = "vlor_total") int vlor_total, @WebParam(name = "creadoPor") String creadoPor) {
        TbFacturaVenta tbFacturaVenta = new TbFacturaVenta();
        tbFacturaVenta.setId(id_factura_venta);
        
        TbProductoTerminado tbProductoTerminado = new TbProductoTerminado();
        tbProductoTerminado.setId(id_producto);
       
        TbDetalleFacturaVenta objDetalleFacturaVenta = new TbDetalleFacturaVenta(tbFacturaVenta, tbProductoTerminado, cantidad, vlor_iva, tipo_iva, vlor_unitario, vlor_total, creadoPor, new Date());
        
        try {
            TbDetalleFacturaVentaDaoImpl daoDetalleFacturaVenta = new TbDetalleFacturaVentaDaoImpl();
            daoDetalleFacturaVenta.create(objDetalleFacturaVenta);
            return "se creo el Detalle de la Factura";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "fn_borrar_detalle_factura")
    public String fn_borrar_detalle_factura(@WebParam(name = "ID") int ID) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "fn_modificar_detalle_factura_venta")
    public String fn_modificar_detalle_factura_venta(@WebParam(name = "ID") int ID, @WebParam(name = "id_factura_venta") int id_factura_venta, @WebParam(name = "id_producto") int id_producto, @WebParam(name = "cantidad") int cantidad, @WebParam(name = "vlor_iva") int vlor_iva, @WebParam(name = "tipo_iva") String tipo_iva, @WebParam(name = "vlor_unitario") int vlor_unitario, @WebParam(name = "vlor_total") int vlor_total, @WebParam(name = "modificadoPor") String modificadoPor) {
        
        try {
        TbFacturaVenta tbFacturaVenta = new TbFacturaVenta();
        tbFacturaVenta.setId(id_factura_venta);
        
        TbProductoTerminado tbProductoTerminado = new TbProductoTerminado();
        tbProductoTerminado.setId(id_producto);
        
        TbDetalleFacturaVentaDaoImpl daoDetalleFacturaVentaDaoImpl = new TbDetalleFacturaVentaDaoImpl();
        TbDetalleFacturaVenta obj_modificar = daoDetalleFacturaVentaDaoImpl.findById(ID);
        
        if(obj_modificar != null){
            obj_modificar.setId(ID);
            obj_modificar.setTbFacturaVenta(tbFacturaVenta);
            obj_modificar.setTbProductoTerminado(tbProductoTerminado);
            obj_modificar.setCantidad(cantidad);
            obj_modificar.setVlorIva(vlor_iva);
            obj_modificar.setTipoIva(tipo_iva);
            obj_modificar.setVlorUnitario(vlor_unitario);
            obj_modificar.setVlorTotal(vlor_total);
            obj_modificar.setModificadoPor(modificadoPor);
            obj_modificar.setModificadoEn(new Date());
            
            daoDetalleFacturaVentaDaoImpl.update(obj_modificar);
            
        }else{
            return "El Detalle Factura Venta no Exite";
        }
        return "se Modifico el Detalle de la  Factura Venta";
        
        } catch (Exception e) {
            throw e;
        }
    }
}
