package com.formacionspring.apirest.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.formacionspring.apirest.entity.Producto;
import com.formacionspring.apirest.service.ProductoService;

@RestController
@RequestMapping("/api")
public class ProductoRestController {
	
	@Autowired
	private ProductoService servicio;
	
	//METODO PARA MOSTRAR TODOS LOS PRODUCTOS//
	@GetMapping({"/productos","/"})
	public List<Producto> index() {
		return servicio.mostrarTodos();	
	}

	//METODO PARA MOSTRAR UN PRODUCTO POR ID//
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> show(@PathVariable long id) {
		
		 Producto producto = null;
	        Map<String,Object>  response = new HashMap<>();

	        try {

	            producto = servicio.mostrarPorId(id);

	        } catch (DataAccessException e) {
	            response.put("mensaje", "Error al realizar en base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        if(producto == null) {
	            response.put("mensaje", "El producto con ID: "+id+" no existe en la base de datos");
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<Producto>(producto,HttpStatus.OK);
	    }
	
	//METODO PARA CREAR UN NUEVO PRODUCTO//
	@PostMapping("/productos")
    public ResponseEntity<?> create(@RequestBody Producto producto) {
        Producto productoNew = null;
        Map<String,Object>  response = new HashMap<>();

        try {

            productoNew =  servicio.guardar(producto);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El producto ha sido creado con éxito");
        response.put("producto", productoNew);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
       
        
    }
	
	//METODO PARA ACTUALIZAR UN PRODUCTO//
	@PutMapping("/productos/{id}")
    public ResponseEntity<?> update(@RequestBody Producto producto
            ,@PathVariable Long id) {

        Producto productoUpdate =  servicio.mostrarPorId(id);
        Map<String,Object>  response = new HashMap<>();


        if(productoUpdate == null) {
            response.put("mensaje","No existe el registro con id:"+id);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try {


            productoUpdate.setNombre(producto.getNombre());
            producto.setCodproducto(producto.getCodproducto());
            producto.setTipo(producto.getTipo());
            producto.setPrecio(producto.getPrecio());
            producto.setFecharegistro(producto.getFecharegistro());
            producto.setCantidad(producto.getCantidad());
          
            
            if (producto.getImagen() != null) {
            	String nombreFotoAnterior = producto.getImagen();
                //verificamos que el cliente tenga registrado una imagen
                if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
                    //preparamos la ruta a la imagen guardada
                    Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                    File archivoFotoAnterior = rutaFotoAnterior.toFile();
                    //verificamos que el archivo existe y se pueda leer
                    if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                        //borramos la imagen
                        archivoFotoAnterior.delete();
                    }
                }
                producto.setImagen(producto.getImagen());
			}
            
            //guardo y retorno los datos actualizados
            servicio.guardar(productoUpdate);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El producto ha sido actualizado con éxito");
        response.put("producto", productoUpdate);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }
	@DeleteMapping("/productos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Producto productoBorrado = servicio.mostrarPorId(id);
        Map<String,Object>  response = new HashMap<>();

        if(productoBorrado == null) {
            response.put("mensaje","No existe el registro con id:"+id);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try {
        	if(productoBorrado.getImagen()!=null) {
                String nombreFotoAnterior = productoBorrado.getImagen();
                //verificamos que el cliente tenga registrado una imagen
                if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
                    //preparamos la ruta a la imagen guardada
                    Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                    File archivoFotoAnterior = rutaFotoAnterior.toFile();
                    //verificamos que el archivo existe y se pueda leer
                    if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                        //borramos la imagen
                        archivoFotoAnterior.delete();
                    }
                }

            }

            servicio.borrar(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El producto ha sido eliminado con éxito");
        response.put("cliente", productoBorrado);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);


    }
	//METODO PARA SUBIR IMAGENES//
	@PostMapping("/productos/Uploads")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,
            @RequestParam("id") Long id){

        Map<String,Object>  response = new HashMap<>();
        //Buscar el cliente por el id recibido//
        Producto producto = servicio.mostrarPorId(id);

        //Preguntamos si el archivo es distinto de vacio//
        if( !archivo.isEmpty() ) {
            //Guardamos el nombre del archivo en esta variable//
        	String nombreArchivo =  UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ", "");

            //Guardamos la ruta completa uploads/nombredelaimagen lo guardamos en una variable de tipo path que es de java.io//

            Path rutaArchivo = Paths.get("Uploads").resolve(nombreArchivo).toAbsolutePath();

            try {
                //Copiamos el archivo fisico a la ruta que definimos en Path//
                Files.copy(archivo.getInputStream(), rutaArchivo );
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen del producto");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            String nombreFotoAnterior = producto.getImagen();
            //verificamos que el cliente tenga registrado una imagen
            if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
                //preparamos la ruta a la imagen guardada
                Path rutaFotoAnterior = Paths.get("Uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                //verificamos que el archivo existe y se pueda leer
                if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                    //borramos la imagen
                    archivoFotoAnterior.delete();
                }
            }

            //Guardamos el nombre de la imagen/bvb
            producto.setImagen(nombreArchivo);
            //Registramos en base de datos//
            servicio.guardar(producto);

            response.put("producto", producto);
            response.put("mensaje","Imagen subida correctamente :"+nombreArchivo);

        }


        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }
	
	//2do METODO PARA SUBIR IMAGENES//
	@GetMapping("/Uploads/img/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen){
	
		Path rutaArchivo = Paths.get("Uploads").resolve(nombreImagen).toAbsolutePath();
		
		Resource recurso = null;
		
		try {
			//codigo para acceder al archivo por url
			recurso = new UrlResource(rutaArchivo.toUri());
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("no se puede cargar a la imagen: "+nombreImagen);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+recurso.getFilename()+"\"");
		
		
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
	
	
	
	
	}

