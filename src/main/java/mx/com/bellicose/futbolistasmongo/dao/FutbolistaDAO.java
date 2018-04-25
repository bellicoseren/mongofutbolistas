package mx.com.bellicose.futbolistasmongo.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import mx.com.bellicose.futbolistasmongo.dto.Futbolista;

public class FutbolistaDAO {

	public DBCursor cursor = null;
	public DBCollection collection = null;
	// Conexión al Servidor de MongoDB
	MongoClient mongoClient = new MongoClient();
	
	
	public DBCursor leer() {


		// Conexión a la base de datos
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("futbol");

		// Obtener la collection
		collection = db.getCollection("futbolistas");

		
		
		// READ Leer todos los documentos de la base de datos
		int numDocumentos = (int) collection.getCount();
		System.out.println("Hay " + numDocumentos + " en la colección");

		cursor = collection.find();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next().toString());
			}
		} finally {
			cursor.close();
		}
		
		return cursor;
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
