package mx.com.bellicose.futbolistasmongo;

import java.util.ArrayList;
import java.util.Arrays;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import mx.com.bellicose.futbolistasmongo.dao.FutbolistaDAO;
import mx.com.bellicose.futbolistasmongo.dto.Futbolista;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ArrayList<Futbolista> futbolistas = new ArrayList<>();
    	
    	futbolistas.add(new Futbolista("Kev", "Hernandez Flores", 15, new ArrayList<String>(Arrays.asList("Delantero")), true));
    	futbolistas.add(new Futbolista("Christopher", "Hernandez Flores", 15, new ArrayList<String>(Arrays.asList("Defensa")), true));
    	futbolistas.add(new Futbolista("Keruvin", "Hernandez Flores", 15, new ArrayList<String>(Arrays.asList("Portero")), true));
    	futbolistas.add(new Futbolista("Rene", "Hernandez Ramirez", 36, new ArrayList<String>(Arrays.asList("Portero")), false));
    	futbolistas.add(new Futbolista("Rene", "Hernandez Ramirez", 36, new ArrayList<String>(Arrays.asList("Defensa")), false));
    	
//    	Conexión al Servidor de MongoDB
    	MongoClient mongoClient = new MongoClient();

    	
//    	CRUD Insertamos los objetos futbolistas (o documentos a Mongo) en la colección futbolista
    	for (@SuppressWarnings("unused") Futbolista futbolista : futbolistas) {
//			collection.insert(futbolista.toDbObjectFutbolista());
		}
    	
    	
//    	DAO leer
    	FutbolistaDAO dao = new FutbolistaDAO();
    	DBCursor leer = dao.leer();
    	System.out.println("Lectura de futbolistas");
    	for (DBObject dbObject : leer) {
			System.out.println(dbObject.toString());
		}
    	
    	
//    	Consulta por posiciones
    	DBCursor consulta = dao.consultaPosicion();
    	System.out.println("Consulta por posiciones");
    	for (DBObject dbObject : consulta) {
			System.out.println(dbObject.toString());
		}
    	
    	
//    	Actualizar edad  	
    	dao.actualizar();
    	
    	
//    	Eliminar registro
    	dao.eliminar();
    	
    	
//    	Cerrar la conexión
    	mongoClient.close();
    }
    
}
