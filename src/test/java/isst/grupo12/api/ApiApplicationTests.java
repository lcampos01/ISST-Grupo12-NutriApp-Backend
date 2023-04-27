package isst.grupo12.api;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isst.grupo12.api.repository.AlergenosRepository;
import isst.grupo12.api.model.Alergenos;

@SpringBootTest
class ApiApplicationTests {

	@Autowired
  	private AlergenosRepository repository;
	@Test
	void contextLoads() {
	}

	@Test
	void verifyBootstrappingByPersistingAnEmployee() {

		List<Alergenos> listaAlergias=repository.findByusuario_id(1);
		System.out.println("AAAAAAAAAAAAAAAAAAAAA");
		
		// System.out.println("AAAAAAAAAAAAAAAAAAAAA");
		int size=listaAlergias.size();
		int alergeno1=listaAlergias.get(0).getId();
		assertNotEquals(size,0);
		assertEquals(alergeno1,12);
		// System.out.println(listaAlergias.toString());
		
	}

}
