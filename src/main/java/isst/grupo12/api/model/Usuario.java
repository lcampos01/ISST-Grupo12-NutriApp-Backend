package isst.grupo12.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer altura;

    @Column(nullable = false)
    private Integer peso;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private String fecha_nacimiento;

    @Column(nullable = false)
    private Integer actividad_diaria;

    @Column(nullable = false)
    private Integer isAdmin;
}
