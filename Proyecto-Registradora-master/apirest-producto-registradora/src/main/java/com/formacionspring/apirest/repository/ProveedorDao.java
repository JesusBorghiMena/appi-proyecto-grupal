package com.formacionspring.apirest.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.formacionspring.apirest.entity.Proveedor;

@Repository
public interface ProveedorDao extends CrudRepository<Proveedor, Long> {



}