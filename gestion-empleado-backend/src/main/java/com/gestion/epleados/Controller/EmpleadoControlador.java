package com.gestion.epleados.Controller;

import com.gestion.epleados.Excepciones.ResourceNoException;
import com.gestion.epleados.Model.Empleado;
import com.gestion.epleados.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoRepository repository;

    @GetMapping("/empleados")
    public List<Empleado> listarTodosLosEmpleados() {
        return repository.findAll();
    }

    @PostMapping("/empleados")
    public Empleado gurdarEmpleado(@RequestBody Empleado empleado) {
        return repository.save(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> ObtenerEmpleadoPorId(@PathVariable Long id) {

        Empleado empleado = repository.findById(id).orElseThrow(() -> new
                ResourceNoException(" No se encontro el empleado en la base de datos"));

        return ResponseEntity.ok(empleado);

    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Empleado> eliminarEmpleado(@PathVariable Long id){

        Empleado eliminar = repository.findById(id).orElseThrow(() -> new
                ResourceNoException(" no se pudo eliminar el empleado")) ;

        repository.delete(eliminar);
        return  ResponseEntity.ok(eliminar);

    }


    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actudalizarEmpleado(@PathVariable Long id,@RequestBody Empleado detallesEmpleado) {

        Empleado empleado = repository.findById(id).orElseThrow(() -> new
                ResourceNoException(" No se encontro el empleado en la base de datos"));

        empleado.setName(detallesEmpleado.getName());
        empleado.setLastName(detallesEmpleado.getLastName());
        empleado.setEmail(detallesEmpleado.getEmail());

        Empleado empleadoActualizado= repository.save(empleado);

        return ResponseEntity.ok(empleadoActualizado);
    }
}
