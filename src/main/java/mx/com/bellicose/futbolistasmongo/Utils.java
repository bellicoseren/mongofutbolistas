package mx.com.bellicose.futbolistasmongo;

import com.google.gson.Gson;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@SuppressWarnings("deprecation")
public class Utils {
	  public static DBObject toDBObject(Object pojo) {
		    String json = new Gson().toJson(pojo);
		    return (DBObject) JSON.parse(json);
		  }

		  public static Object fromDBObject(DBObject dbObj, Class<?> clazz) {
		    String json = dbObj.toString();
		    return new Gson().fromJson(json, clazz);
		  }
}
