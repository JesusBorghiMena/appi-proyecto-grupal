package com.formacionspring.apirest.service;

import java.util.List;

import com.formacionspring.apirest.entity.Producto;
import com.formacionspring.apirest.entity.Proveedor;
import com.formacionspring.apirest.entity.Venta;
public interface ProveedorService {


	//Metodo para mostrar todos los prroveedores//
	
		public List<Proveedor> mostrarTodos();
		
		//Metodo para mostrar un proveedor por id//
		
		public Proveedor mostrarPorId(Long id);
		
		//Metodo para guardar un proveedor//
		
		public Proveedor guardar(Proveedor proveedor);
		
		//Metodo para borrar un proveedor//
		
		public void borrar(Long id);
		
		//Metodo para mostrar todas las ventas//
		public List<Venta> mostrarVentas();
}