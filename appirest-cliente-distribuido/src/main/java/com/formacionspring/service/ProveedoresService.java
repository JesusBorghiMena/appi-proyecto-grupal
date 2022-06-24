package com.formacionspring.service;

import java.util.List;

import com.formacionspring.entity.Proveedor;

public interface ProveedoresService {

	Proveedor mostrarPorId(Long Id);

	Proveedor guardar(Proveedor proveedor);

	List<Proveedor> mostrarTodos();

	public void borrar(Long id);


}
