package persistance;

import exceptions.CuentaException;
import model.Account;
import model.Notes;
import model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Persistance {
    public static final String RUTA_ARCHIVO_CUENTAS = "src/resources/cuentas.txt";
    public static final String RUTA_ARCHIVO_VENDEDORES = "src/resources/vendedores.txt";
    public static final String RUTA_ARCHIVO_USUARIOS = "src/resources/archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_LOG = "src/resources/Log.txt";
    public static final String RUTA_ARCHIVO_OBJETOS = "src/resources/archivoObjetos.txt";
    public static final String RUTA_ARCHIVO_MODELO_PANADERIA_BINARIO = "src/resources/Red.dat";
    public static final String RUTA_ARCHIVO_MODELO_PANADERIA_XML = "src/resources/Red.xml";




    public static void cargarDatosArchivos(Notes notes) throws FileNotFoundException, IOException {





        //cargar archivos empleados
        ArrayList<User> usuariosCargados = cargarVendedores();

        if(usuariosCargados.size() > 0)
            notes.getUsersList().addAll(usuariosCargados);

        //cargar archivo objetos

        //cargar archivo empleados

        //cargar archivo prestamo

    }








    public static void guardarUsuarios(ArrayList<User> listaUsuarios) throws IOException {

        // TODO Auto-generated method stub
        String contenido = "";

        for(User user : listaUsuarios)
        {
            contenido+= user.getName()+","+ user.getId()+","+user.getAccount().getUser()+","+ user.getAccount().getPassword()+","+ "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENDEDORES, contenido, false);
    }

    public static void guardarRed(Notes red) throws IOException {

        // TODO Auto-generated method stub

        ArchivoUtil.guardarRed("src/resources/prueba.txt", red);


    }

    public static Notes cargarRed() {
        return ArchivoUtil.cargarRed("src/resources/prueba.txt");
    }


//	----------------------LOADS------------------------

    /**
     *
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */




    public static ArrayList<User> cargarVendedores() throws IOException {

        ArrayList<User> users =new ArrayList<User>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_VENDEDORES);
        String linea="";

        for (String s : contenido) {
            linea = s;
            User user = new User();
            user.setName(linea.split(",")[0]);
            user.setId(linea.split(",")[1]);
            user.getAccount().setUser(linea.split(",")[2]);
            user.getAccount().setPassword(linea.split(",")[3]);
            //user.setProductos( linea.split(",")[5]);
            users.add(user);
        }
        return users;
    }




    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {

        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }


    public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, CuentaException {

        if(validarUsuario(usuario,contrasenia)) {
            return true;
        }else {
            throw new CuentaException("La cuenta no existe");
        }

    }

    private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException
    {
        ArrayList<Account> cuentas = Persistance.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);

        for (int indiceUsuario = 0; indiceUsuario < cuentas.size(); indiceUsuario++)
        {
            Account usuarioAux = cuentas.get(indiceUsuario);
            if(usuarioAux.getUser().equalsIgnoreCase(usuario) && usuarioAux.getPassword().equalsIgnoreCase(contrasenia)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Account> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
        ArrayList<Account> cuentas =new ArrayList<Account>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
        String linea="";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);

            Account cuenta = new Account();
            cuenta.setUser(linea.split(",")[0]);
            cuenta.setPassword(linea.split(",")[1]);

            cuentas.add(cuenta);
        }
        return cuentas;
    }


//	----------------------SAVES------------------------

    /**
     * Guarda en un archivo de texto todos la informaci�n de las personas almacenadas en el ArrayList
     * @throws IOException
     */

    public static void guardarObjetos(ArrayList<Account> listaCuentas) throws IOException  {
        String contenido = "";

        for(Account cuenta : listaCuentas) {
            contenido+= cuenta.getUser()+","+ cuenta.getPassword()+","+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CUENTAS, contenido, true);
    }





    //------------------------------------SERIALIZACI�N  y XML


    public static Notes cargarRecursoBancoBinario() {

        Notes snowalowe = null;

        try {
            snowalowe = (Notes)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_PANADERIA_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return snowalowe;
    }

    public static void guardarRecursoBancoBinario(Notes notes) {

        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_PANADERIA_BINARIO, notes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static Notes cargarRecursoRedXML() {

        Notes notes = null;

        try {
            notes = (Notes) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PANADERIA_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return notes;

    }



    public static void guardarRecursoRedXML(Notes notes) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PANADERIA_XML, notes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
