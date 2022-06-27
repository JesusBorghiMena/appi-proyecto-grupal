package com.formacionspring.apirest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.formacionspring.apirest.entity.Venta;
import com.formacionspring.apirest.repository.VentaDao;

@Service
public class VentaServiceImpl implements VentaService {
	
	
	 @Autowired
	    private VentaDao ventaDao;

	    @Override
	    @Transactional(readOnly = true)
	    public List<Venta> mostrarTodos() {

	        return (List<Venta>) ventaDao.findAll();
	    }

	    @Override
	    @Transactional(readOnly = true)
	    public Venta mostrarPorId(Long id) {

	        return ventaDao.findById(id).orElse(null);
	    }

	    @Override
	    @Transactional
	    public Venta guardar(Venta venta) {

	        return ventaDao.save(venta);
	    }

	    @Override
	    @Transactional
	    public void borrar(Long id) {
	        ventaDao.deleteById(id);


}
	    
}






