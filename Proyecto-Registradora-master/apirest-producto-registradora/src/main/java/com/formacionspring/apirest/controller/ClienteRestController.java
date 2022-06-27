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
import com.formacionspring.apirest.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private ClienteService servicio;
	
	//METODO PARA MOSTRAR TODOS LOS CLIENTES//
		@GetMapping({"/clientes","/"})
		public List<Cliente> index() {
			return servicio.mostrarTodos();	
		}

		//METODO PARA MOSTRAR UN CLIENTE POR ID//
		@GetMapping("/clientes/{id}")
		public ResponseEntity<?> show(@PathVariable long id) {
			
			 Cliente cliente = null;
		        Map<String,Object>  response = new HashMap<>();

		        try {

		            cliente = servicio.mostrarPorId(id);

		        } catch (DataAccessException e) {
		            response.put("mensaje", "Error al realizar en base de datos");
		            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		        }

		        if(cliente == null) {
		            response.put("mensaje", "El cliente con ID: "+id+" no existe en la base de datos");
		            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		        }

		        return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
		    }
		
		//METODO PARA CREAR UN NUEVO CLIENTE//
		@PostMapping("/clientes")
	    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
	        Cliente clienteNew = null;
	        Map<String,Object>  response = new HashMap<>();

	        try {

	            clienteNew =  servicio.guardar(cliente);

	        } catch (DataAccessException e) {
	            response.put("mensaje", "Error al realizar en base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        response.put("mensaje","El cliente ha sido creado con éxito");
	        response.put("producto", clienteNew);
	        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	       
	        
	    }
		
		//METODO PARA ACTUALIZAR UN CLIENTE//
		@PutMapping("/clientes/{id}")
	    public ResponseEntity<?> update(@RequestBody Cliente cliente
	            ,@PathVariable Long id) {

	        Cliente clienteUpdate =  servicio.mostrarPorId(id);
	        Map<String,Object>  response = new HashMap<>();


	        if(clienteUpdate == null) {
	            response.put("mensaje","No existe el registro con id:"+id);
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	        }

	        try {


	            clienteUpdate.setNombre(cliente.getNombre());
	            cliente.setApellido(cliente.getApellido());
	            cliente.setNif(cliente.getNif());
	            cliente.setEmail(cliente.getEmail());
	            cliente.setTelefono(cliente.getTelefono());
	            
	            //guardo y retorno los datos actualizados
	            servicio.guardar(clienteUpdate);
	          
	           
	        } catch (DataAccessException e) {
	            response.put("mensaje", "Error al realizar en base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        response.put("mensaje","El cliente ha sido actualizado con éxito");
	        response.put("producto", clienteUpdate);
	        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

	    }
		//METODO PARA BORRAR UN CLIENTE//
		@DeleteMapping("/clientes/{id}")
	    public ResponseEntity<?> delete(@PathVariable Long id) {
	        Cliente clienteBorrado = servicio.mostrarPorId(id);
	        Map<String,Object>  response = new HashMap<>();

	        if(clienteBorrado == null) {
	            response.put("mensaje","No existe el registro con id:"+id);
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	        }
	        servicio.borrar(id);

	        response.put("mensaje","El cliente ha sido eliminado con éxito");
	        response.put("cliente", clienteBorrado);
	        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);


	    }
		
	
	
	
		
}

