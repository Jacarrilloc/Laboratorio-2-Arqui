package co.edu.javeriana.as.personapp.terminal.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.javeriana.as.personapp.terminal.adapter.PhoneInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.adapter.ProfessionInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.adapter.StudyInputAdapterCli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.javeriana.as.personapp.terminal.adapter.PersonaInputAdapterCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MenuPrincipal {
	
	//Beans
	@Autowired
	private PersonaInputAdapterCli personaInputAdapterCli;
	private PhoneInputAdapterCli phoneInputAdapterCli;
	private ProfessionInputAdapterCli professionInputAdapterCli;
	private StudyInputAdapterCli studyImputAdapterCli;

	private static final int SALIR = 0;
	private static final int MODULO_PERSONA = 1;
	private static final int MODULO_PROFESION = 2;
	private static final int MODULO_TELEFONO = 3;
	private static final int MODULO_ESTUDIO = 4;

	//Menus
	private final PersonaMenu personaMenu;
	private final PhoneMenu phoneMenu;
	private final ProfessionMenu professionMenu;
	private final StudyMenu studyMenu;
	private final Scanner keyboard;

    public MenuPrincipal() {
        this.personaMenu = new PersonaMenu();
		this.phoneMenu = new PhoneMenu();
		this.professionMenu = new ProfessionMenu();
		this.studyMenu = new StudyMenu();
        this.keyboard = new Scanner(System.in);
    }

	public void inicio() {
		
		//personaMenu = new PersonaMenu(personaInputAdapterCli);
		boolean isValid = false;
		do {
			mostrarMenu();
			int opcion = leerOpcion();
			switch (opcion) {
			case SALIR:
				isValid = true;
				break;
			case MODULO_PERSONA:
				personaMenu.iniciarMenu(personaInputAdapterCli, keyboard);
				log.info("volvio");
				break;
			case MODULO_PROFESION:
				professionMenu.iniciarMenu(professionInputAdapterCli,keyboard);
				log.info("volvio");
				break;
			case MODULO_TELEFONO:
				phoneMenu.iniciarMenu(phoneInputAdapterCli,keyboard);
				log.info("volvio");
				break;
			case MODULO_ESTUDIO:
				studyMenu.iniciarMenu(studyImputAdapterCli,keyboard);
				log.info("volvio");
				break;
			default:
				log.warn("La opción elegida no es válida.");
			}

		} while (!isValid);
		keyboard.close();
	}

	private void mostrarMenu() {
		System.out.println("----------------------");
		System.out.println(MODULO_PERSONA + ". Módulo de Personas");
		System.out.println(MODULO_PROFESION + ". Módulo de Profesiones");
		System.out.println(MODULO_TELEFONO + ". Módulo de Teléfonos");
		System.out.println(MODULO_ESTUDIO + ". Módulo de Estudios");
		System.out.println(SALIR + ". Salir");
		System.out.println("----------------------");
	}


	private int leerOpcion() {
		try {
			System.out.print("Ingrese una opción: ");
			return keyboard.nextInt();
		} catch (InputMismatchException e) {
			log.warn("Solo se permiten números.");
			return leerOpcion();
		}
	}

}
