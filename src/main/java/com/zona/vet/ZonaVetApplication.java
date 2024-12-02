package com.zona.vet;

import com.zona.vet.model.Mascota;
import com.zona.vet.service.IMascotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ZonaVetApplication implements CommandLineRunner {
	@Autowired
	private IMascotaService mascotaService;

	private static final Logger logger = LoggerFactory.getLogger(ZonaVetApplication.class);

	public static void main(String[] args) {
		logger.info("--------Iniciando-Aplicacion--------");
		//Levantando la fabrica de spring
		SpringApplication.run(ZonaVetApplication.class, args);
		logger.info("--------Finalizando-Aplicacion--------");
	}

	@Override
	public void run(String... args) throws Exception {
		 zonaVetApp();
	}

	public void zonaVetApp(){
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;

		while (!salir){
			try {
				int opcion = mostrarMenu(scanner);
				salir = ejecutarOpciones(scanner, opcion);
			}catch (Exception e){
				logger.error("Error al ejecutar ZonaVet " + e.getMessage());
			}
		}
	}
	public int mostrarMenu(Scanner scanner){
		logger.info("""
				-----ZONA-VET-----
				1. Listar Mascotas
				2. Buscar Mascota
				3. Agregar Mascota
				4. Actualizar Mascota
				5. Eliminar Mascota
				6. Salir
				Elije una opcion: \s""");
		return Integer.parseInt(scanner.nextLine());
	}

	public boolean ejecutarOpciones(Scanner scanner, int opcion){
		boolean salir = false;
		switch (opcion){
			case 1 ->{
				logger.info("---Listado-de-mascotas---");
				List<Mascota> mascotas = mascotaService.findAll();
				mascotas.forEach(System.out::println);
			}
			case 2 -> {
				logger.info("---Ingrese el codigo de la mascota---");
				int id = Integer.parseInt(scanner.nextLine());
				Mascota mascota = mascotaService.findById(id);

				if(mascota != null){
					logger.info("Mascota encontrada: " + mascota);
				}else {
					logger.info("No se encontro ninguna mascota con el id: " + id);
				}
			}
			case 3 -> {
				Mascota mascota = new Mascota();

				logger.info("Ingresa el nombre: ");
				mascota.setNombre(scanner.nextLine());

				logger.info("Ingresa la edad: ");
				mascota.setEdad(Integer.parseInt(scanner.nextLine()));

				logger.info("Ingresa el sexo: ");
				mascota.setSexo(scanner.nextLine().toLowerCase().charAt(0));

				logger.info("Ingresa la especie: ");
				mascota.setEspecie(scanner.nextLine());

				try {
					mascotaService.save(mascota);
					logger.info("Mascota agregada correctamente");
				}catch (Exception e){
					logger.error("Error al agregar mascota" + e.getMessage());
				}
			}
			case 4 -> {
				logger.info("Ingresa el id de la mascota: ");
				int id = Integer.parseInt(scanner.nextLine());

				logger.info("Ingresa el nombrea: ");
				String nombre = scanner.nextLine();

				logger.info("Ingresa la edad: ");
				int edad = Integer.parseInt(scanner.nextLine());

				logger.info("Ingresa el sexo: ");
				char sexo = scanner.nextLine().toLowerCase().charAt(0);

				logger.info("Ingresa la especie: ");
				String especie = scanner.nextLine();

				Mascota mascota = new Mascota(id, nombre, edad, sexo, especie);
				try {
					mascotaService.save(mascota);
					logger.info("Mascota actualizada correctamente");
				}catch (Exception e){
					logger.error("Error al actualizar la mascota " + e.getMessage());
				}
			}
			case 5 -> {
				logger.info("Ingresa el id de la mascota:");
				int id = Integer.parseInt(scanner.nextLine());
				try {
					Mascota mascota = mascotaService.findById(id);
					if(mascota != null){
						mascotaService.deleteById(id);
						logger.info("Mascota con el id: " + id + " eliminado correctamente");
					}else{
						logger.warn("No se encontro a la mascota con el id: " + id);
					}
				}catch (Exception e){
					logger.error("No se pudo eliminar a la mascota con el id: " + id + e.getMessage());
				}
			}
			case 6-> {
				salir = true;
				logger.info("Esperamos volver a verte pronto!");
			}
			default -> {
				logger.info("Opcion ingresada no valida");
			}
		}
		return salir;
	}
}
