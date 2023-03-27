package es.upm.dit.isst.tfgapi.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class TFG {
    @Id
    private String email;
    private String pass;
    private String nombre;
    private String titulo;
    private Integer status;
    @Lob
    private byte[] memoria;
    private Integer nota;
    private String tutor;
    public String getEmail() {
        return email;
    }
    @Override
    public String toString() {
        return "TFG [email=" + email + ", pass=" + pass + ", nombre=" + nombre + ", titulo=" + titulo + ", status="
                + status + ", memoria=" + Arrays.toString(memoria) + ", nota=" + nota + ", tutor=" + tutor + "]";
    }
    public TFG(String email, String pass, String nombre, String titulo, Integer status, byte[] memoria, Integer nota,
            String tutor) {
        this.email = email;
        this.pass = pass;
        this.nombre = nombre;
        this.titulo = titulo;
        this.status = status;
        this.memoria = memoria;
        this.nota = nota;
        this.tutor = tutor;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public byte[] getMemoria() {
        return memoria;
    }
    public void setMemoria(byte[] memoria) {
        this.memoria = memoria;
    }
    public Integer getNota() {
        return nota;
    }
    public void setNota(Integer nota) {
        this.nota = nota;
    }
    public String getTutor() {
        return tutor;
    }
    public void setTutor(String tutor) {
        this.tutor = tutor;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((pass == null) ? 0 : pass.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + Arrays.hashCode(memoria);
        result = prime * result + ((nota == null) ? 0 : nota.hashCode());
        result = prime * result + ((tutor == null) ? 0 : tutor.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TFG other = (TFG) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (pass == null) {
            if (other.pass != null)
                return false;
        } else if (!pass.equals(other.pass))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (!Arrays.equals(memoria, other.memoria))
            return false;
        if (nota == null) {
            if (other.nota != null)
                return false;
        } else if (!nota.equals(other.nota))
            return false;
        if (tutor == null) {
            if (other.tutor != null)
                return false;
        } else if (!tutor.equals(other.tutor))
            return false;
        return true;
    }
}
