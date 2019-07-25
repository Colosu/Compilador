package hashmap;

import java.util.*;

public class Hashmap<T> extends HashMap<String, T> {
	
	public Hashmap() {

		padre = null;
		tabla = new HashMap<String, T>();
	}

	public Hashmap(Hashmap<T> pad) {
		
		padre = pad;
		tabla = new HashMap<String, T>();
	}
	
	public T get(String key) {
		
		T n = tabla.get(key);
		
		if (n == null && padre != null) {
			n = padre.get(key);
		}
		
		return n;
	}
	
	public T putIfAbsent(String key, T value) {
		return tabla.putIfAbsent(key, value);
	}
	
	public T put(String key, T value) {
		return tabla.put(key, value);
	}
	
	public int find(String key) {
		int q = 0;
		T n = tabla.get(key);
		
		if (n == null && padre != null) {
			q = 1 + padre.find(key);
		}
		
		return q;
	}
	
	public HashMap<String, T> getTabla() {
		return tabla;
	}

	public Hashmap<T> getPadre() {
		return padre;
	}

	public boolean isGlobal() {
		if (!isGlobal && padre != null) {
			return padre.isGlobal();
		}
		return isGlobal;
	}

	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}

	public void setPadre(Hashmap<T> padre) {
		this.padre = padre;
	}

	public void setTabla(HashMap<String, T> tabla) {
		this.tabla = tabla;
	}
	
	public String toString() {
		return tabla.toString();
	}

	private Hashmap<T> padre;
	private HashMap<String, T> tabla;
	private boolean isGlobal = false;
	private static final long serialVersionUID = 1L;
}
