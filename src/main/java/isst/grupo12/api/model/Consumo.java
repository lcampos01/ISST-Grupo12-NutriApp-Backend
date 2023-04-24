package isst.grupo12.api.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconsumo")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @Column()
    private Integer idusuario;

    @Column(nullable = false)
    private Date dia;

    private Integer grasas;

    private Integer carbohidratos;

    private Integer proteinas;

    private Integer calorias;

    @Column(nullable = false)
    private String momento;
    
    @Column(nullable = false)
    private String alimento;

    @Column(nullable = false)
    private Integer cantidad;
    
}
