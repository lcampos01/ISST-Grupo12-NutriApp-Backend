package isst.grupo12.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Alimentos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idalimento")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @Column()
    private Integer idusuario;

    @Column(nullable = false)
    private String nombre;

    private Integer grasas;

    private Integer carbohidratos;

    private Integer proteinas;

    private Integer calorias;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "alimentos_favoritos", joinColumns = @JoinColumn(name = "idalimento"), 
        inverseJoinColumns = @JoinColumn(name = "idusuario")
    )
    private List<Usuario> usuarios;

}
