package mx.com.bellicose.futbolistasmongo.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import mx.com.bellicose.futbolistasmongo.Utils;
import mx.com.bellicose.futbolistasmongo.dto.Futbolista;

public class FutbolistaDAO {

	private MongoClient mongoClient = new MongoClient();
	private DBCursor cursor = null;
	private DBCollection collection = null;
	
	
	/*
	 *  READ Leer todos los futbolistas de la base de datos
	 */
	public List<Futbolista> leer() {


		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("futbol");

		collection = db.getCollection("futbolistas");

		List<Futbolista> futbolistas = new ArrayList<>();
		
		int numDocumentos = (int) collection.getCount();
		System.out.println("Hay " + numDocumentos + " en la colección");

		cursor = collection.find();
		try {
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();
				System.out.println(dbObject.toString());
				futbolistas.add((Futbolista) Utils.fromDBObject(dbObject, Futbolista.class));
			}
		} finally {
			cursor.close();
		}
		
		return futbolistas;
	}
	
	
	/*
	 * CREATE un futbolista a la base de datos
	 */
	public void agregar(Futbolista futbolista){
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("futbol");
		collection = db.getCollection("futbolistas");
		
		collection.insert(futbolista.toDbObjectFutbolista());
		System.out.println("Registro agregado");
	}
	
	
	public DBCursor consultaPosicion(){
		
    	//READ Consulta con condiciones
    	System.out.println("Futbolistas que juegan en la posición de delanteros");
    	DBObject query = new BasicDBObject("posicion", new BasicDBObject("$regex", "Delantero"));
    	
    	cursor = collection.find(query);
    	try{
	    	while(cursor.hasNext()){
	    		Futbolista futbolista = new Futbolista((BasicDBObject) cursor.next());
	    		System.out.println(futbolista.getNombre());
	    	}
    	}finally{
    		cursor.close();
    	}
		return cursor;
	}
	
	
	
	public void actualizar(){
		
    	//UPDATE Actualizar edad de los futbolistas
    	DBObject find = new BasicDBObject("edad", new BasicDBObject("$gt", 100));
    	DBObject update = new BasicDBObject().append("$inc", new BasicDBObject().append("edad", 0));
    	collection.update(find, update, false, true);
	}
	
	
	
	public void eliminar(){
    	//DELETE Borrar futbolistas que no sean profesionales
    	DBObject dbObject = new BasicDBObject("profesional", false);
    	collection.remove(dbObject);
	}
	
}
