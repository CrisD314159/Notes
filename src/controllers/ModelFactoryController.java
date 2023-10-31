package controllers;



import exceptions.UsuarioException;
import javafx.scene.image.Image;
import model.Admin;
import model.Notes;
import model.User;
import persistance.Persistance;

import java.io.IOException;
import java.util.ArrayList;

public class ModelFactoryController implements Runnable{

	Notes notes;

	Thread guardarXml;
	Thread guardarBinario;
	Thread guardarLog;



	public User obtenerUsuario(String id) throws UsuarioException {
		return notes.buscarVendeor(id);
	}


	//------------------------------  Singleton ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public ModelFactoryController() {


		if(notes == null){
			//inicializarDatos();
			

			cargarResourceXML();
			guardarResourceXML();



		}

		//Registrar la accion de incio de sesi�n
		Persistance.guardaRegistroLog("Welcome to notes", 1, "InicioApp");


	}




	private void iniciarSalvarDatosPrueba() {
		inicializarDatos();
		try {

			Persistance.cargarDatosArchivos(getNotes());
			Persistance.guardarUsuarios(getNotes().getUsersList());



		} catch (IOException e) {
			throw new RuntimeException(e);
		}



	}

	@Override
	public void run() {
		Thread hiloActual = Thread.currentThread();
		if(hiloActual == guardarXml){
			Persistance.guardarRecursoRedXML(notes);
			//guardarResourceXml();
		}
		if(hiloActual == guardarBinario){
			Persistance.guardarRecursoBancoBinario(notes);
		}

	}

	private void cargarDatosDesdeArchivos() {

		notes = new Notes();

		try {
			Persistance.cargarDatosArchivos(getNotes());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	public void cargarResourceXML() {
		notes = Persistance.cargarRecursoRedXML();

	}





	public void guardarResourceXML() {
		guardarXml = new Thread(this);
		guardarXml.start();


	}



	private void inicializarDatos() {

		/**
		notes = new Red();


		Cuenta cuenta = new Cuenta("user1", "1234");
		Vendedor cliente = new Vendedor();
		cliente.setNombre("juan");
		cliente.setApellido("arias");
		cliente.setCedula("125454");
		cliente.setDireccion("Armenia");
		cliente.setCuenta(cuenta);

		notes.getListaUsuarios().add(cliente);

		Cuenta cuenta1 = new Cuenta("admin1", "3456");
		Administrador admin = new Administrador();
		admin.setNombre("Cristian");
		admin.setApellido("Vargas");
		admin.setCedula("1092850502");
		admin.setDireccion("Calle 13");
		admin.setCuenta(cuenta1);
		notes.getListaAdministradores().add(admin);



		System.out.println("Banco inicializado "+ notes);
		 **/

	}

	public Notes getNotes() {
		return notes;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
	}



	/**
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String user, String password) {


		Vendedor vendedor = null;

		try {
			vendedor = getNotes().nuevoVendedor(nombre, apellido, cedula, direccion, user, password);
			guardarResourceXML();
		} catch (VendedorException e) {
			throw new RuntimeException(e);
		}

		return vendedor;

	}




	public boolean actualizarVendedor(String nombre, String apellido, String cedula, String direccion) {

		return getNotes().actualizarVendedor( nombre, apellido, cedula, direccion);

	}


	public Boolean eliminarVendedor(String cedula) {

		boolean vendedor = false;

		vendedor = notes.eliminarVendedor(cedula);
		guardarResourceXML();

		return vendedor;
	}


	public boolean verificarCuenta(String user, String password) {
		boolean cuenta = false;
		try {
			cuenta =  notes.verificarUsuario(user, password);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cuenta;
	}

	public User buscarVendedor(String usuario, String contrasenia) {
		User vendedor = null;
		try{
			vendedor = notes.getUser(usuario, contrasenia);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return  vendedor;
	}


	public boolean verificarUserAdministrador(String user, String password) {
		boolean cuenta = false;
		try {
			cuenta =  notes.verificarUsuarioAdministrador(user, password);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cuenta;
	}

	public Admin obtenerAdministrador(String user, String password) {
		Admin admin = null;
		try {
			admin =  notes.getAdmin(user, password);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return admin;
	}

	 **/







}
