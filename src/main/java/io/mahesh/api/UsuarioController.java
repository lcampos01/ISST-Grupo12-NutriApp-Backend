package io.mahesh.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.InputMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.Reader;
import java.io.IOException;

@RestController

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/register")
    public Usuario Register(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PostMapping("/login")
    public Usuario Login(@RequestBody Usuario usuario) {
        Usuario Usuario_antiguo = usuarioRepository.findByEmailAndContraseña(usuario.email, usuario.contraseña);
        return Usuario_antiguo;
    }

    @GetMapping("/search-product/scan/{codigo}")
    public String getScanning(@PathVariable(value = "codigo") Double codigo) {
        try{
            URL url = new URL("https://world.openfoodfacts.org/api/v2/product/"+codigo);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            Reader r = new InputStreamReader(request.getInputStream());
            BufferedReader br = new BufferedReader(r);
            String line = br.readLine();
            return line;
        }
        catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/search-product/search/{product_name}")
    public String getSearch(@PathVariable(value = "product_name") String product_name) {
        try{
            URL url = new URL("https://world.openfoodfacts.org/categorie/"+product_name+".json");
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            Reader r = new InputStreamReader(request.getInputStream());
            BufferedReader br = new BufferedReader(r);
            String line = br.readLine();
            return line;
        }
        catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
}
