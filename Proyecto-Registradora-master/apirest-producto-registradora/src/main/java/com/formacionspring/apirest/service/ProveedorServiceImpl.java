package com.formacionspring.apirest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspring.apirest.entity.Proveedor;
import com.formacionspring.apirest.entity.Venta;
import com.formacionspring.apirest.repository.ProveedorDao;

@Service
public class ProveedorServiceImpl implements ProveedorService {

 @Autowired
 private ProveedorDao proveedorDao;
 

@Override
@Transactional(readOnly = true)
public List<Proveedor> mostrarTodos() {
	return (List<Proveedor>) proveedorDao.findAll() ;
}

@Override
@Transactional(readOnly = true)
public Proveedor mostrarPorId(Long id) {
	return proveedorDao.findById(id).orElse(null);
}

@Override
public Proveedor guardar(Proveedor proveedor) {
	return proveedorDao.save(proveedor);
}

@Override
public void borrar(Long id) {
 proveedorDao.deleteById(id);	
}

@Override
public List<Venta> mostrarVentas() {
	return null;
}





    


}