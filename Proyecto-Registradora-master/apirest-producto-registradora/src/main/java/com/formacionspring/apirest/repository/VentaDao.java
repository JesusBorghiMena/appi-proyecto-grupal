package com.formacionspring.apirest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.formacionspring.apirest.entity.Venta;

@Repository
public interface VentaDao extends CrudRepository<Venta, Long>{ 
	
}









