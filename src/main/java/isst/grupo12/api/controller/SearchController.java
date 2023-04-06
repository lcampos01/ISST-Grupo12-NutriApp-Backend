package isst.grupo12.api.controller;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.net.URL;
import java.net.HttpURLConnection;


@RestController
@AllArgsConstructor
public class SearchController {

    @GetMapping("/search-product/scan/{codigo}")
    public ResponseEntity<String> getScanning(@PathVariable(value = "codigo") String codigo) {
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
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/search-product/search/{product_category}")
    public ResponseEntity<String> getSearch(@PathVariable(value = "product_category") String product_category) {
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
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
}
