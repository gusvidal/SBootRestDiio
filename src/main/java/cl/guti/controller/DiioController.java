package cl.guti.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.guti.DTO.DiioDto;
import cl.guti.model.Diio;
import cl.guti.salida.Mensaje;
import cl.guti.service.IDiioService;
import cl.guti.mapper.DiioModelMapper;

@RestController
@CrossOrigin(origins = "*", 
methods= {
    RequestMethod.GET,
    RequestMethod.POST, 
    RequestMethod.PUT, 
    RequestMethod.DELETE
})
public class DiioController {

    @Autowired
    private IDiioService service;
    
    @Autowired
    private DiioModelMapper modelMapper;
    
   
    Log Log = LogFactory.getLog("DiioController");
    
	/*
	 * @GetMapping("/lista") public ResponseEntity<?> getTodos(){
	 * 
	 * List<Diio> lista = service.listar(); if (lista.size()>0){ return new
	 * ResponseEntity<List<Diio>>(lista, HttpStatus.OK); }else{ return new
	 * ResponseEntity<>("Sin existencias", HttpStatus.NOT_FOUND); } }
	 */
    
	/*
	 * @GetMapping("/lista") public List<DiioDto> getTodos(){
	 * 
	 * List<Diio> lista = service.listar();
	 * 
	 * List<DiioDto> response = new ArrayList<>();
	 * 
	 * //Collection<DiioDto> response;
	 * 
	 * List<DiioDto> dtos = response .stream() .map(diio -> modelMapper.map(diio,
	 * DiioDto.class)) .collect(Collectors.toList());
	 * 
	 * return response; }
	 */    
    
    @GetMapping("/lista/{id}")
    public ResponseEntity<?> getDiio(@PathVariable int id){
    	try {
        Diio arete = service.listarId(id);
        DiioDto diioDto = new DiioDto(
        		arete.getId(),
        		arete.getNroDiio(),
        		arete.getFechaInstall(),
        		arete.getDesc(),
        		arete.getFechaNacimiento(),
        		arete.getCampo().getId(),
        		arete.getCampo().getDireccion(),
        		arete.getCampo().getRup()
        		);
        return ResponseEntity.ok().body(diioDto);
    	}catch (Exception e) {
    		Log.info("No existe el registro: "  + id);
    		return new ResponseEntity<>("Sin existencias", HttpStatus.NOT_FOUND);
        }
    }

    /* De esta manera lo llamamos en postman
     * Creando primero el registro en la tabla campo
     {
    "nroDiio": "01.365.9866",
    "fechaInstall": "2023/05/01",
    "desc": "Vaquilla angus negro",
    "fechaNacimiento": "2022/10/21",
    "campo": {
        "id": 1,
        "rup": "10.1.01.2568",
        "direccion": "Planchado los indios KM 1"
    }
}
     * */
    @PostMapping("/nuevo-diio")
    public ResponseEntity<?> saveCow(@RequestBody Diio vaca){
        Mensaje msg = new Mensaje();
        try{
            service.save(vaca);
			Log.info("Se ingresa registro: "  + vaca.getNroDiio());
			msg.mensaje = "Se ingresa registro: "  + vaca.getNroDiio();
            return new ResponseEntity<Mensaje>(msg, HttpStatus.OK);
        }catch(Exception e) {
            Log.info("No es posible ingresar el registro: " + vaca.getNroDiio() + " error: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCow(@PathVariable("id") int id, @RequestBody Diio vaca) {
		Mensaje msg = new Mensaje();
		try {
			Diio animal = new Diio();
			Log.info("Se ha CREADO un objeto del tipo Diio");
			animal.setId(id);
			animal.setFechaInstall(vaca.getFechaInstall());
			animal.setDesc(vaca.getDesc());
			animal.setFechaNacimiento(vaca.getFechaNacimiento());
			animal.setNroDiio(vaca.getNroDiio());
			animal.setCampo(vaca.getCampo());
			service.save(animal);
			Log.info("Se ha actualizado el registro id : " + id);
			msg.mensaje = "Actualizacion de datos con exito!!";
			return new ResponseEntity<Mensaje>(msg, HttpStatus.OK);
		} catch (Exception e) {
			Log.info("No es posible actualizar el registro : " + id + " error: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	 

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCow(@PathVariable("id") int id) {
        Mensaje msg = new Mensaje();
		try{
			service.delete(id);
			Log.info("Se elimino la vaca con id : " + id);
            msg.mensaje = "Registro eliminado con exito!!";
            
            return new ResponseEntity<Mensaje>(msg, HttpStatus.OK);
        } catch(Exception e) {
            Log.info("No es posible eliminar el registro : " + id + " error: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
    
    @GetMapping("lista-mapping/{id}")
    public ResponseEntity<?> getDiioDto(@PathVariable("id") int id) {
        
        try {
            return ResponseEntity.ok().body(modelMapper.convertToDto(service.listarId(id)));
        	}catch (Exception e) {
        		Log.info("No existe el registro: "  + id);
        		return new ResponseEntity<>("Sin existencias", HttpStatus.NOT_FOUND);
            }
    }
    
    @GetMapping("/lista") 
	public ResponseEntity<?> getTodos() {
    	var auth =  SecurityContextHolder.getContext().getAuthentication();
        Log.info("Datos del Usuario: " + auth.getPrincipal());
        Log.info("Datos de los Permisos " + auth.getAuthorities());
        Log.info("Esta autenticado " + auth.isAuthenticated());
		List<DiioDto> listaDto = new ArrayList<>();
		try {
			List<Diio> lista = service.listar();
			for(Diio diio : lista) {
				listaDto.add(modelMapper.convertToDto(service.listarId(diio.getId())));
			}
		
			return new ResponseEntity<List<DiioDto>>(listaDto, HttpStatus.OK);
			
		} catch (Exception e) {
    		Log.info("Error al consultar datos ... " + e.getMessage());
    		return new ResponseEntity<>("Sin existencias", HttpStatus.NOT_FOUND);
        }
	}
}
