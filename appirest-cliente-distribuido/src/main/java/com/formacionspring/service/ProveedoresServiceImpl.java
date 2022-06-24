package com.formacionspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspring.entity.Proveedor;
import com.formacionspring.repository.ProveedorDao;



@Service
public class ProveedoresServiceImpl implements ProveedoresService{

	@Autowired
	private ProveedorDao proveedorDao;

	@Override
	@Transactional
	public Proveedor mostrarPorId(Long Id) {
		return proveedorDao.findById(Id).orElse(null);
	}

	@Override
	@Transactional
	public Proveedor guardar(Proveedor proveedor) {
		return proveedorDao.save(proveedor);
	}
	
	@Override
	@Transactional(readOnly= true)
	public List<Proveedor> mostrarTodos() {
		return (List<Proveedor>) proveedorDao.findAll();
	}

	@Override
	@Transactional
	public void borrar(Long id) {
		proveedorDao.deleteById(id);
		
	}

	
	}
