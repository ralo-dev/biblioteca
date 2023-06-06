package com.cbtis.biblioteca;

import com.cbtis.biblioteca.jwt.SignupRequest;
import com.cbtis.biblioteca.libros.Libro;
import com.cbtis.biblioteca.usuarios.ERole;
import com.cbtis.biblioteca.usuarios.Role;
import com.cbtis.biblioteca.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args){
		List<Libro> libros = new ArrayList<>();
		libros.add(new Libro("Los Juegos del Hambre", "Suzanne Collins", "Molino", "978-987-45722-1-9", "Primera", 2008, "Katniss Everdeen vive en el Distrito 12 de Panem",3));
		libros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", "Sudamericana", "978-987-739-010-0", "Primera", 1967, "La historia de la familia Buendía a través de siete generaciones",2));
		libros.add(new Libro("El Código Da Vinci", "Dan Brown", "Umbriel", "978-84-92915-14-4", "Primera", 2003, "Robert Langdon se encuentra envuelto en una peligrosa conspiración en París",3));
		libros.add(new Libro("El Alquimista", "Paulo Coelho", "HarperCollins", "978-0062315007", "Primera Edición", 1988, "Una novela mágica sobre el viaje de un pastor andaluz a través del norte de África en busca de un tesoro",5));
		libros.add(new Libro("El código Da Vinci", "Dan Brown", "Doubleday", "978-0307474278", "Primera Edición", 2003, "Una emocionante novela de misterio que sigue al simbólogo Robert Langdon mientras intenta resolver un asesinato en el Museo del Louvre",1));
		libros.add(new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", "Bloomsbury", "978-0747532743", "Primera Edición", 1997, "El primer libro de la famosa serie de Harry Potter, que sigue las aventuras de un joven mago mientras asiste a Hogwarts School of Witchcraft and Wizardry",3));
		libros.add(new Libro("1984", "George Orwell", "Harvill Secker", "978-0099518471", "Primera Edición", 1949, "Una obra clásica de ciencia ficción y sátira política que sigue al personaje de Winston Smith mientras lucha contra un gobierno totalitario y opresivo",0));
		libros.add(new Libro("Orgullo y prejuicio", "Jane Austen", "Thomas Egerton", "978-1593083250", "Primera Edición", 1813, "Una novela clásica de la literatura inglesa que sigue las complicadas relaciones amorosas de las hermanas Bennet en la Inglaterra del siglo XIX",2));
		libros.add(new Libro("La insoportable levedad del ser", "Milan Kundera", "Gallimard", "978-2070373694", "Primera Edición", 1984, "Una novela filosófica que explora temas como la libertad, el amor y la identidad a través de las historias entrelazadas de cuatro personajes",2));
		libros.add(new Libro("Matar a un ruiseñor", "Harper Lee", "J. B. Lippincott & Co.", "978-0061120084", "Primera Edición", 1960, "Una novela clásica de la literatura estadounidense que sigue las vidas de los hermanos Jem y Scout Finch mientras su padre, un abogado, defiende a un hombre negro acusado injustamente",5));

/*
		for (Libro libro : libros) {
			jdbcTemplate.update("INSERT INTO libros(titulo, autor, editorial, isbn, edicion, anio_publicacion, descripcion, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
					libro.getTitulo(), libro.getAutor(), libro.getEditorial(), libro.getIsbn(), libro.getEdicion(), libro.getAnioPublicacion(), libro.getDescripcion(), libro.getCantidad());
		}*/

		List<Role> roles = new ArrayList<>();
		roles.add(new Role(ERole.ROLE_LIBRARIAN));
		roles.add(new Role(ERole.ROLE_ADMIN));
		/*

		for (Role rol : roles){
			jdbcTemplate.update("INSERT INTO roles(name) VALUES (?)",
					rol.getName().toString());
		}*/
	}

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
