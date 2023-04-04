package io.mahesh.api;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue
    Integer id;
    String nombre;
    String email;
    Integer altura;
    Integer peso;
    String sexo;
    String fecha_nacimiento;
    Integer actividad_diaria;
    Integer isAdmin;
    String contraseña;
}