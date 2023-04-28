package isst.grupo12.api;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isst.grupo12.api.repository.AlergenosRepository;
import isst.grupo12.api.repository.UsuarioRepository;
import isst.grupo12.api.model.Alergenos;
import isst.grupo12.api.model.Usuario;

@SpringBootTest
class ApiApplicationTests {

	@Autowired
  	private AlergenosRepository alergenosrepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	void TestAlergenos() {

		Usuario usuario1=new Usuario();
		usuario1.setId(1);
		usuario1.setEmail("usuariotest@gmail.com");
		usuario1.setNombre("usuariotest");
		usuario1.setAltura(175);
		usuario1.setPeso(70);
		usuario1.setSexo("Hombre");
		usuario1.setFecha_nacimiento("1998-05-05");
		usuario1.setActividad_diaria(0);
		usuario1.setIsAdmin(0);
		usuario1.setPassword("1234");
		usuarioRepository.save(usuario1);

		Alergenos alergeno1=new Alergenos();
		alergeno1.setId(12);
		alergeno1.setAlimento_alergeno("Gluten");
		alergeno1.setUsuario(usuario1);
		alergenosrepository.save(alergeno1);

		List<Alergenos> listaAlergias = alergenosrepository.findByusuario_id(1);

		assertEquals(listaAlergias.get(0).getAlimento_alergeno(),"Gluten");
		assertEquals(listaAlergias.get(0).getId(),12);
		assertEquals(listaAlergias.get(0).getUsuario().getId(),1);
		assertNotEquals(listaAlergias.size(),0);

	}

	@Test
	void TestUsuario() {

		Usuario usuario1=new Usuario();
		usuario1.setId(1);
		usuario1.setEmail("usuariotest@gmail.com");
		usuario1.setNombre("usuariotest");
		usuario1.setAltura(175);
		usuario1.setPeso(70);
		usuario1.setSexo("Hombre");
		usuario1.setFecha_nacimiento("1998-05-05");
		usuario1.setActividad_diaria(0);
		usuario1.setIsAdmin(0);
		usuario1.setPassword("1234");
		usuarioRepository.save(usuario1);

		Optional<Usuario> usuarioTest = usuarioRepository.findOneByEmail("usuariotest@gmail.com");

		assertEquals(usuarioTest.get().getEmail(), "usuariotest@gmail.com");
	
	}

}
