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
    Integer Id;
    String Nombre;
    String Email;
    Integer Altura;
    Integer Peso;
    String Sexo;
    String Fecha_nacimiento;
    Integer Actividad_diaria;
    Integer IsAdmin;
    String Contraseña;
}
