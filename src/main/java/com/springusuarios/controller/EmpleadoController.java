package com.springusuarios.controller;

import com.springusuarios.exceptions.ResourceNotFoundException;
import com.springusuarios.model.Empleado;
import com.springusuarios.repository.EmpleadoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    //para traer todos los empleados
    @GetMapping("/empleados")
    public List<Empleado> getAllEmpleados(){
        return empleadoRepository.findAll();
    }

    //para crear un empleado
    @PostMapping("/empleados/save")
    public Empleado createEmpleado(@RequestBody Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    //para buscar un empleado por id
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id){
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No existe el empleado con el ID: "+ id));
        return ResponseEntity.ok(empleado);
    }

    //elimina un empleado por su id
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        empleadoRepository.deleteById(id);
    }

    //para actualizar un empleado
    @PatchMapping("/empleados/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable("id") Long id, @RequestBody Empleado updateEmpleado) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID: " + id));

        empleado.setNombre(updateEmpleado.getNombre());
        empleado.setApellido(updateEmpleado.getApellido());
        empleado.setEmail(updateEmpleado.getEmail());

        empleadoRepository.save(empleado);
        return ResponseEntity.ok(empleado);
    }


}
