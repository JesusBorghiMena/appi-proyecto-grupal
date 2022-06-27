package com.formacionspring.apirest.service;

import java.util.List;
import com.formacionspring.apirest.entity.Venta;

public interface VentaService {
	
	//Metodo para mostrar todos las ventas//

    public List<Venta> mostrarTodos();
    
    //Metodo para mostrar una venta por id//
    
    public Venta mostrarPorId(Long id);

    //Metodo para guardar una venta//
    
    public Venta guardar(Venta venta);
    
    //Metodo para borrar una venta//

    public void borrar(Long id);


}
