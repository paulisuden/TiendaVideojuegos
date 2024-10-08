package com.videojuego.demo;

import com.videojuego.demo.services.ServicioCategoria;
import com.videojuego.demo.services.ServicioEstudio;
import com.videojuego.demo.services.ServicioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private ServicioVideojuego srvVideojuego;

	@Autowired
	private ServicioCategoria srvCategoria;

	@Autowired
	private ServicioEstudio srvEstudio;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		Categoria cat = new Categoria();
		cat.setNombre("Estrategia");

		Estudio estudio = new Estudio();
		estudio.setNombre("Estudio 1");

		Videojuego videojuego = new Videojuego();
		videojuego.setTitulo("Tetris");
		videojuego.setDescripcion("Videojuego de rompecabezas");
		videojuego.setPrecio(1500);
		videojuego.setStock(20);
		videojuego.setFechaLanzamiento(new Date());
		videojuego.setCategoria(cat);
		videojuego.setEstudio(estudio);
		videojuego.setImagen("https://cdn.milenio.com/uploads/media/2023/06/06/matematicos-explican-por-que-es.jpg");

		List<Videojuego> videojuegos = srvVideojuego.findAllByActivo();

		estudio.setVideojuegos(videojuegos);
		cat.setVideojuegos(videojuegos);

		srvEstudio.saveOne(estudio);
		srvCategoria.saveOne(cat);
		srvVideojuego.saveOne(videojuego);

		Categoria cat2 = new Categoria();
		cat2.setNombre("Aventura");

		Estudio estudio2 = new Estudio();
		estudio2.setNombre("Estudio 2");

		Videojuego videojuego2 = new Videojuego();
		videojuego2.setTitulo("Mario Bros");
		videojuego2.setDescripcion("Super Mario sigue las aventuras del fontanero Mario, por lo general en el ficticio Reino Champiñón.");
		videojuego2.setPrecio(2000);
		videojuego2.setStock(25);
		videojuego2.setFechaLanzamiento(new Date());
		videojuego2.setCategoria(cat2);
		videojuego2.setEstudio(estudio2);
		videojuego2.setImagen("https://www.nintendo.com/eu/media/images/10_share_images/portals_3/2x1_SuperMarioHub_image1600w.jpg");

		List<Videojuego> videojuegos2 = srvVideojuego.findAllByActivo();

		estudio2.setVideojuegos(videojuegos2);
		cat2.setVideojuegos(videojuegos2);

		srvEstudio.saveOne(estudio2);
		srvCategoria.saveOne(cat2);
		srvVideojuego.saveOne(videojuego2);

		Categoria cat3 = new Categoria();
		cat3.setNombre("Estrategia");

		Estudio estudio3 = new Estudio();
		estudio3.setNombre("Estudio 3");

		Videojuego videojuego3 = new Videojuego();
		videojuego3.setTitulo("Pac Man");
		videojuego3.setDescripcion("El objetivo es recorrer el espacio para comer todas las bolas “energizantes” sin ser atrapado por los fantasmas");
		videojuego3.setPrecio(1000);
		videojuego3.setStock(40);
		videojuego3.setFechaLanzamiento(new Date());
		videojuego3.setCategoria(cat3);
		videojuego3.setEstudio(estudio3);
		videojuego3.setImagen("https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/media/image/2022/04/pac-man-2682107.jpg?tf=1200x1200");

		List<Videojuego> videojuegos3 = srvVideojuego.findAllByActivo();

		estudio3.setVideojuegos(videojuegos3);
		cat3.setVideojuegos(videojuegos3);

		srvEstudio.saveOne(estudio3);
		srvCategoria.saveOne(cat3);
		srvVideojuego.saveOne(videojuego3);
		*/

		System.out.println("listo");
	}
}
