package com.formacionspring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="proveedores")
public class Proveedor implements Serializable{


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String nombre;
private String cif;
private String email;
private int telefono;

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

public String getCif() {
	return cif;
}

public void setCif(String cif) {
	this.cif = cif;
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

/**
 * 
 */
private static final long serialVersionUID = 1L;
}