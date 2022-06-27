package com.formacionspring.apirest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.formacionspring.apirest.entity.Producto;
import com.formacionspring.apirest.entity.Venta;
import com.formacionspring.apirest.repository.ProductoDao;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> mostrarTodos() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto mostrarPorId(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	public Producto guardar(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	public void borrar(Long id) {
		productoDao.deleteById(id);;
		
	}

	@Override
	public List<Venta> mostrarVentas() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	

}


