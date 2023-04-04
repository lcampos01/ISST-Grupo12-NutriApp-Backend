package io.mahesh.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URL;
import java.net.HttpURLConnection;

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
    public String getScanning(@PathVariable(value = "codigo") String codigo) {
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
            return result.toString();
        }
        catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/search-product/search/{product_category}")
    public String getSearch(@PathVariable(value = "product_category") String product_category) {
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
            return result.toString();
        }
        catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
}
