package isst.grupo12.api.model;

import java.util.List;

import lombok.Data;

@Data
public class UsuarioRegistro {

    private String email;

    private String password;

    private String nombre;

    private Integer altura;

    private Integer peso;

    private String sexo;

    private String fecha_nacimiento;

    private Integer actividad_diaria;

    private Integer isAdmin;
    
    private List<Alergenos> alergenos;
}
