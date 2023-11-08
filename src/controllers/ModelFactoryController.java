package controllers;



import exceptions.UsuarioException;
import lists.Cola;
import lists.ListaSimple;
import model.*;
import model.Process;
import persistance.Persistance;

import java.io.IOException;
import java.util.ArrayList;

public class ModelFactoryController implements Runnable{

	Notes notes = new Notes();

	Thread guardarXml;
	Thread guardarBinario;
	Thread guardarLog;



	public User obtenerUsuario(String id) throws UsuarioException {
		return getNotes().buscarVendeor(id);
	}

	public User getUserByAccount(String user, String password) {
		return getNotes().getUserByAccount(user, password);
	}

	public boolean verifyAccount(String user, String password) {
		return getNotes().verifyAccount(user, password);
	}

	public boolean verifyUser(String id, String user) {
		return getNotes().verifyUser(id, user);
	}

	public boolean createUser(String name, String id, String user, String password) {
		boolean trigger = false;
		try {
			trigger = getNotes().createUser(name, id, user, password);
			guardarResourceXML();
		} catch (Exception e){
			throw new RuntimeException(e);

		}
		return trigger;
	}

	public ArrayList<Process> getUserProcessList(User signedUser) {
		ArrayList<Process> processes = new ArrayList<Process>();
		ListaSimple<Process> lista = signedUser.getProcessList();
		for (int i = 0; i <lista.getSize() ; i++) {
			processes.add(lista.getNodeValue(i));
		}
		return processes;
	}

	public boolean deleteUserProcess(User signedUser, Process selectedProcess) {
		boolean trigger = false;
		try {
			trigger = getNotes().deleteUserProcess(signedUser, selectedProcess);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return trigger;
	}

	public boolean verifyProcess(User signedUser, String id) {
		return getNotes().verifyprocess(signedUser, id);
	}

	public Process createProcess(User signedUser, String id, String name) {
		Process process;
		try {
			process = getNotes().createprocess(signedUser, id, name);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return process;
	}

	public ArrayList<Activity> getProcessActivities(Process selectedProcess) {
		ArrayList<Activity> activities = new ArrayList<Activity>();
		ListaSimple<Activity> lista = selectedProcess.getActivitiesList();
		for (int i = 0; i <lista.getSize() ; i++) {
			activities.add(lista.getNodeValue(i));
		}
		return activities;
	}

	public ArrayList<Task> getActivityTasks(Activity activity) {
		ArrayList<Task> tasks = new ArrayList<Task>();
		Cola<Task> lista = activity.getTasksList();
		for (int i = 0; i <lista.size() ; i++) {
			tasks.add(lista.getNodeValue(i));
		}
		return tasks;
	}

	public boolean createActivity(Process process, String name, String description, boolean mustDo) {
		boolean trigger = false;
		try {
			trigger = getNotes().createActivity(process, name, description, mustDo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return trigger;
	}

	public boolean deleteActivity(Process selectedProcess, Activity selectedActivity) {
		boolean trigger = false;
		try {
			trigger = getNotes().deleteActivity(selectedProcess, selectedActivity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return trigger;
	}



	public boolean updateActivity(Activity selectedActivity, String name, String description, boolean mustDo) {
		boolean trigger = false;
		try {
			trigger = getNotes().updateActivity(selectedActivity, name, description, mustDo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return trigger;
	}

	public Admin getAdminByAccount(String user, String password) {
		return getNotes().getAdminByAccount(user, password);
	}
	public boolean verifyAdminAccount(String user, String password) {
		return getNotes().verifyAccountAdministrator(user, password);
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
