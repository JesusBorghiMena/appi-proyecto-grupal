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

import com.formacionspring.apirest.entity.Cliente;
import com.formacionspring.apirest.entity.Venta;
import com.formacionspring.apirest.service.VentaService;

@RestController
@RequestMapping("/api")
public class VentaRestController {
	
	@Autowired
	private VentaService servicio;
	
	@GetMapping({"/venta","/"})
	public List<Venta> index(){
        return servicio.mostrarTodos();
        
	}
	
	@GetMapping("/venta/{id}")
    public ResponseEntity<?> show(@PathVariable long id) {

        Venta venta = null;
        Map<String,Object>  response = new HashMap<>();

        try {

            venta = servicio.mostrarPorId(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(venta == null) {
            response.put("mensaje", "El producto con ID: "+id+" no existe en la base de datos");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Venta>(venta,HttpStatus.OK);
    }

    @PostMapping("/venta")
    public ResponseEntity<?> create(@RequestBody Venta cliente) {
        Venta clienteNew = null;
        Map<String,Object>  response = new HashMap<>();

        try {

            clienteNew =  servicio.guardar(cliente);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","La venta ha sido creada con éxito");
        response.put("producto", clienteNew);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }
        
      //METODO PARA ACTUALIZAR UNA VENTA//
      		@PutMapping("/venta/{id}")
      	    public ResponseEntity<?> update(@RequestBody Venta venta
      	            ,@PathVariable Long id) {

      	        Venta ventaUpdate =  servicio.mostrarPorId(id);
      	        Map<String,Object>  response = new HashMap<>();


      	        if(ventaUpdate == null) {
      	            response.put("mensaje","No existe el registro con id:"+id);
      	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
      	        }

      	        try {


      	            ventaUpdate.setFecha_venta(venta.getFecha_venta());
      	            ventaUpdate.setTotal(venta.getTotal());
      	            ventaUpdate.setCliente(venta.getCliente());
      	            ventaUpdate.setProducto(venta.getProducto());
      	            
      	            //guardo y retorno los datos actualizados
      	            servicio.guardar(ventaUpdate);
      	          
      	           
      	        } catch (DataAccessException e) {
      	            response.put("mensaje", "Error al realizar en base de datos");
      	            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
      	        }

      	        response.put("mensaje","La venta ha sido actualizado con éxito");
      	        response.put("producto", ventaUpdate);
      	        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
      	        
      		}
      	        
      	    //METODO PARA BORRAR UNA VENTA//
      			@DeleteMapping("/venta/{id}")
      		    public ResponseEntity<?> delete(@PathVariable Long id) {
      		        Venta ventaBorrado = servicio.mostrarPorId(id);
      		        Map<String,Object>  response = new HashMap<>();

      		        if(ventaBorrado == null) {
      		            response.put("mensaje","No existe el registro con id:"+id);
      		            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
      		        }
      		        servicio.borrar(id);

      		        response.put("mensaje","La venta ha sido eliminada con éxito");
      		        response.put("cliente", ventaBorrado);
      		        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);


    }


	
	
	

}
