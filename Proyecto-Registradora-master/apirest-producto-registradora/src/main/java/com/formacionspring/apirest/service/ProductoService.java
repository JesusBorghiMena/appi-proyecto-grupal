package com.formacionspring.apirest.service;

import java.util.List;

import com.formacionspring.apirest.entity.Producto;
import com.formacionspring.apirest.entity.Venta;

public interface ProductoService {

	//Metodo para mostrar todos los productos//
	
	public List<Producto> mostrarTodos();
	
	//Metodo para mostrar un producto por id//
	
	public Producto mostrarPorId(Long id);
	
	//Metodo para guardar un producto//
	
	public Producto guardar(Producto producto);
	
	//Metodo para borrar un producto//
	
	public void borrar(Long id);
	
	//Metodo para mostrar todas las ventas//
	public List<Venta> mostrarVentas();
	
}
