package com.soapsoft.Model;
// Generated 25/11/2017 12:07:14 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * TbUbicacion generated by hbm2java
 */
@Entity
@Table(name="tb_ubicacion"
    ,catalog="soap_soft"
    , uniqueConstraints = @UniqueConstraint(columnNames="descripcion") 
)
public class TbUbicacion  implements java.io.Serializable {


     private Integer id;
     private String descripcion;
     private String creadoPor;
     private Date creadoEn;
     private String modificadoPor;
     private Date modificadoEn;
     private Set tbProductoTerminados = new HashSet(0);
     private Set tbMateriaPrimas = new HashSet(0);

    public TbUbicacion() {
    }

	
    public TbUbicacion(String descripcion, String creadoPor, Date creadoEn) {
        this.descripcion = descripcion;
        this.creadoPor = creadoPor;
        this.creadoEn = creadoEn;
    }
    public TbUbicacion(String descripcion, String creadoPor, Date creadoEn, String modificadoPor, Date modificadoEn, Set tbProductoTerminados, Set tbMateriaPrimas) {
       this.descripcion = descripcion;
       this.creadoPor = creadoPor;
       this.creadoEn = creadoEn;
       this.modificadoPor = modificadoPor;
       this.modificadoEn = modificadoEn;
       this.tbProductoTerminados = tbProductoTerminados;
       this.tbMateriaPrimas = tbMateriaPrimas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="descripcion", unique=true, nullable=false, length=50)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="creadoPor", nullable=false, length=50)
    public String getCreadoPor() {
        return this.creadoPor;
    }
    
    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creadoEn", nullable=false, length=19)
    public Date getCreadoEn() {
        return this.creadoEn;
    }
    
    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }

    
    @Column(name="modificadoPor", length=50)
    public String getModificadoPor() {
        return this.modificadoPor;
    }
    
    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modificadoEn", length=19)
    public Date getModificadoEn() {
        return this.modificadoEn;
    }
    
    public void setModificadoEn(Date modificadoEn) {
        this.modificadoEn = modificadoEn;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tbUbicacion")
    public Set getTbProductoTerminados() {
        return this.tbProductoTerminados;
    }
    
    public void setTbProductoTerminados(Set tbProductoTerminados) {
        this.tbProductoTerminados = tbProductoTerminados;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tbUbicacion")
    public Set getTbMateriaPrimas() {
        return this.tbMateriaPrimas;
    }
    
    public void setTbMateriaPrimas(Set tbMateriaPrimas) {
        this.tbMateriaPrimas = tbMateriaPrimas;
    }




}


