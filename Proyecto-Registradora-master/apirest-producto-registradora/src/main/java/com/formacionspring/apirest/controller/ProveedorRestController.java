package com.formacionspring.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.apirest.entity.Proveedor;
import com.formacionspring.apirest.service.ProveedorService;

@RestController
@RequestMapping("/api")
public class ProveedorRestController {

	
	@Autowired
	private ProveedorService servicio;
	
	//METODO PARA MOSTRAR TODOS LOS PROVEEDORES//
	@GetMapping({"/proveedores","/"})
	public List<Proveedor> index() {
		return servicio.mostrarTodos();	
	}

	//METODO PARA MOSTRAR UN PRODUCTO POR ID//
	@GetMapping("/proveedores/{id}")
	public ResponseEntity<?> show(@PathVariable long id) {
		
		 Proveedor proveedor = null;
	        Map<String,Object>  response = new HashMap<>();

	        try {

	            proveedor = servicio.mostrarPorId(id);

	        } catch (DataAccessException e) {
	            response.put("mensaje", "Error al realizar en base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        if(proveedor == null) {
	            response.put("mensaje", "El proveedor con ID: "+id+" no existe en la base de datos");
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<Proveedor>(proveedor,HttpStatus.OK);
	    }
	
	//METODO PARA CREAR UN NUEVO PROVEEDOR//
		@PostMapping("/proveedores")
	    public ResponseEntity<?> create(@RequestBody Proveedor proveedor) {
	        Proveedor proveedorNew = null;
	        Map<String,Object>  response = new HashMap<>();

	        try {

	            proveedorNew =  servicio.guardar(proveedor);

	        } catch (DataAccessException e) {
	            response.put("mensaje", "Error al realizar en base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        response.put("mensaje","El proveedor ha sido creado con éxito");
	        response.put("proveedor", proveedorNew);
	        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	       
	        
	    }
	
	//METODO PARA ACTUALIZAR UN PROVEEDOR//
	@PutMapping("/proveedores/{id}")
    public ResponseEntity<?> update(@RequestBody Proveedor proveedor
            ,@PathVariable Long id) {

        Proveedor proveedorUpdate =  servicio.mostrarPorId(id);
        Map<String,Object>  response = new HashMap<>();


        if(proveedorUpdate == null) {
            response.put("mensaje","No existe el registro con id:"+id);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try {


            proveedorUpdate.setNombre(proveedor.getNombre());
            proveedor.setNif(proveedor.getNif());
            proveedor.setEmail(proveedor.getEmail());
            proveedor.setTelefono(proveedor.getTelefono());
            proveedor.setDireccion(proveedor.getDireccion());
                     
            
            //guardo y retorno los datos actualizados
            servicio.guardar(proveedorUpdate);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El proveedor ha sido actualizado con éxito");
        response.put("proveedor", proveedorUpdate);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }
	//METODO PARA BORRAR UN PROVEEDOR//
	@DeleteMapping("/proveedores/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Proveedor proveedorBorrado = servicio.mostrarPorId(id);
        Map<String,Object>  response = new HashMap<>();

        if(proveedorBorrado == null) {
            response.put("mensaje","No existe el registro con id:"+id);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        servicio.borrar(id);

        response.put("mensaje","El proveedor ha sido eliminado con exito");
        response.put("cliente", proveedorBorrado);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);


    }
	
}

	
