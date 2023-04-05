package io.mahesh.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;
import java.util.Optional;
import java.net.HttpURLConnection;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/register")
    public Usuario Register(@RequestBody Usuario usuario) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        //final byte[] bytes = usuario.contraseña.getBytes("UTF-8");
        final String frase = "1234567890123456";
        final MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(frase.getBytes("UTF-8"));
        final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aes.init(Cipher.ENCRYPT_MODE, key);
	    //final byte[] cifrado = aes.doFinal(bytes);
        return usuarioRepository.save(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody Usuario usuario) {
        Usuario usuario_antiguo = usuarioRepository.findByEmailAndContraseña(usuario.email, usuario.contraseña);
        if(usuario_antiguo != null){
            Usuario usuario_nuevo = new Usuario();
            usuario_nuevo.id = usuario_antiguo.id;
            usuario_nuevo.nombre = usuario_antiguo.nombre;
            usuario_nuevo.email = usuario_antiguo.email;
            usuario_nuevo.altura = usuario_antiguo.altura;
            usuario_nuevo.peso = usuario_antiguo.peso;
            usuario_nuevo.sexo = usuario_antiguo.sexo;
            usuario_nuevo.fecha_nacimiento = usuario_antiguo.fecha_nacimiento;
            usuario_nuevo.actividad_diaria = usuario_antiguo.actividad_diaria;
            usuario_nuevo.isAdmin = usuario_antiguo.isAdmin;
            return ResponseEntity.ok().body(usuario_nuevo);
        } else{
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modify-user/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody Usuario Usuario) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(Usuario.getNombre());
            usuario.setEmail(Usuario.getEmail());
            usuario.setAltura(Usuario.getAltura());
            usuario.setPeso(Usuario.getPeso());
            usuario.setSexo(Usuario.getSexo());
            usuario.setFecha_nacimiento(Usuario.getFecha_nacimiento());
            usuario.setActividad_diaria(Usuario.getActividad_diaria());
            usuario.setIsAdmin(Usuario.getIsAdmin());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().body(usuario);
        }).orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search-product/scan/{codigo}")
    public ResponseEntity getScanning(@PathVariable(value = "codigo") String codigo) {
        try{
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://world.openfoodfacts.org/api/v2/product/"+codigo);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return ResponseEntity.ok().body(result.toString()); 
        }
        catch(Exception e){
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search-product/search/{product_category}")
    public ResponseEntity getSearch(@PathVariable(value = "product_category") String product_category) {
        try{
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://es.openfoodfacts.org/categoria/"+product_category+".json");
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return ResponseEntity.ok().body(result.toString()); 
        }
        catch(Exception e){
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
    }
}
