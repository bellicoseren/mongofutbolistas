package mx.com.bellicose.futbolistasmongo.dto;

import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class Futbolista {

	private String nombre;
	private String apellidos;
	private int edad;
	private ArrayList<String> posicion;
	private Boolean profesional;
	
	public Futbolista(){	
	}
	
	public Futbolista(String nombre, String apellidos, int edad, ArrayList<String> posicion, Boolean profesional){
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.edad=edad;
		this.posicion=posicion;
		this.profesional=profesional;
	}
	
	public Futbolista(BasicDBObject dbObjectFutbolista){
		this.nombre=dbObjectFutbolista.getString("nombre");
		this.apellidos=dbObjectFutbolista.getString("apellidos");
		this.edad=dbObjectFutbolista.getInt("edad");
		
		BasicDBList listPosiciones = (BasicDBList) dbObjectFutbolista.get("posicion");
		this.posicion=new ArrayList<>();
		for (Object object : listPosiciones) {
			this.posicion.add(object.toString());
		}
		
		this.profesional=dbObjectFutbolista.getBoolean("profesional");
	}
	
	public BasicDBObject toDbObjectFutbolista(){
		BasicDBObject dBObjectFutbolista = new BasicDBObject();
		
		dBObjectFutbolista.append("nombre", this.getNombre());
		dBObjectFutbolista.append("apellidos", this.getApellidos());
		dBObjectFutbolista.append("edad", this.getEdad());
		dBObjectFutbolista.append("posicion", this.getPosiciones());
		dBObjectFutbolista.append("profesional", this.isProfesional());
		
		return dBObjectFutbolista;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public ArrayList<String> getPosiciones() {
		return posicion;
	}

	public void setPosiciones(ArrayList<String> posiciones) {
		this.posicion = posiciones;
	}

	public boolean isProfesional() {
		return profesional;
	}

	public void setProfesional(boolean profesional) {
		this.profesional = profesional;
	}

	@Override
	public String toString() {
		return "nombre: " + nombre + " apellidos: " + apellidos + " edad: " + edad + " posicion: " + posicion + " profesional: " + profesional;
	}
}
