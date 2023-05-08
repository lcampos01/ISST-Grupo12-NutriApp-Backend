package isst.grupo12.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Integer id;

    @Column(nullable = false, unique=true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    private String nombre;

    private Integer altura;

    private Integer peso;

    private String sexo;

    private String fecha_nacimiento;

    private Integer actividad_diaria;

    private Integer isAdmin;

    private String objetivo;

    private double num_objetivo;

    @Lob
    private byte[] imagenPerfil;
    
}
