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
import isst.grupo12.api.repository.FavoritosRepository;
import isst.grupo12.api.repository.UsuarioRepository;
import isst.grupo12.api.model.Alergenos;
import isst.grupo12.api.model.Favoritos;
import isst.grupo12.api.model.Usuario;

@SpringBootTest
class ApiApplicationTests {

	@Autowired
  	private AlergenosRepository alergenosRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private FavoritosRepository favoritosRepository;

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
		usuario1.setObjetivo("déficit calórico");
		usuario1.setNum_objetivo(500);
		usuarioRepository.save(usuario1);

		Alergenos alergeno1=new Alergenos();
		alergeno1.setId(1);
		alergeno1.setNombre("Gluten");
		alergeno1.setUsuario(usuario1);
		alergenosRepository.save(alergeno1);

		List<Alergenos> listaAlergias = alergenosRepository.findByusuario_id(1);

		assertEquals(listaAlergias.get(0).getNombre(),"Gluten");
		assertEquals(listaAlergias.get(0).getId(),1);
		assertEquals(listaAlergias.get(0).getUsuario().getId(),1);
		assertNotEquals(listaAlergias.size(),0);

	}

	@Test
	void TestUsuarios() {

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
		usuario1.setObjetivo("déficit calórico");
		usuario1.setNum_objetivo(500);
		usuarioRepository.save(usuario1);

		Optional<Usuario> usuarioTest = usuarioRepository.findOneByEmail("usuariotest@gmail.com");

		assertEquals(usuarioTest.get().getEmail(), "usuariotest@gmail.com");
	
	}

	@Test
	void TestFavoritos() {

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
		usuario1.setObjetivo("déficit calórico");
		usuario1.setNum_objetivo(500);
		usuarioRepository.save(usuario1);

		Favoritos favorito1=new Favoritos();
		favorito1.setId(1);
		favorito1.setBarcode("http://www.google.es");
		favorito1.setUsuario(usuario1);
		favoritosRepository.save(favorito1);

		List<Favoritos> listaFavoritos = favoritosRepository.findByusuario_id(1);

		assertEquals(listaFavoritos.get(0).getBarcode(),"http://www.google.es");
		assertEquals(listaFavoritos.get(0).getId(),1);
		assertEquals(listaFavoritos.get(0).getUsuario().getId(),1);
		assertNotEquals(listaFavoritos.size(),0);

		Optional<Favoritos> favoritoTest = favoritosRepository.findByUsuario_idAndBarcode(1,"http://www.google.es");

		assertEquals(favoritoTest.get().getBarcode(),"http://www.google.es");
	
	}

}
