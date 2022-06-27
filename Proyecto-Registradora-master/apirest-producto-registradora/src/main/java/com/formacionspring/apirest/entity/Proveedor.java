package com.formacionspring.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="proveedores")
public class Proveedor implements Serializable{
   
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id primary key autoincremental
    long id;
    @Column(nullable =false)
    private String nombre;
    @Column(nullable =false)
    private String nif;
    @Column(nullable =false)
    private String email;
    @Column(nullable =false)
    private int  telefono;
    @Column(nullable =false)
    private String direccion;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNif() {
        return nif;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
        
        /**
    	 * 
    	 */
    	private static final long serialVersionUID = 1L;
    }

   