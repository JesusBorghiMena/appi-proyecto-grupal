package com.formacionspring.apirest.service;

import java.util.List;
import com.formacionspring.apirest.entity.Cliente;
import com.formacionspring.apirest.entity.Venta;

public interface ClienteService {
	
	//Metodo para mostrar todos los clientes//
	
		public List<Cliente> mostrarTodos();
		
		//Metodo para mostrar un cliente por id//
		
		public Cliente mostrarPorId(Long id);
		
		//Metodo para guardar un cliente//
		
		public Cliente guardar(Cliente cliente);
		
		//Metodo para borrar un cliente//
		
		public void borrar(Long id);
		
		//Metodo para mostrar todas las ventas//
		public List<Venta> mostrarVentas();

}
