package com.formacionspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.entity.Proveedor;

@Repository
public interface ProveedorDao extends CrudRepository<Proveedor, Long> {
	
	

}
