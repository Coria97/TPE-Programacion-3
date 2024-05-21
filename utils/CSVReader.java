package utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import Node.*;


public class CSVReader {
	public CSVReader() {
	}

	public HashMap<String, Tarea> readTasks(String taskPath) {
		System.out.println(taskPath);
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent(taskPath);
		HashMap<String, Tarea> taskHashMap = new HashMap<String, Tarea>();

		for (String[] line: lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String id = line[0].trim();
			String nombre = line[1].trim();
			Integer tiempo = Integer.parseInt(line[2].trim());
			Boolean critica = Boolean.parseBoolean(line[3].trim());
			Integer prioridad = Integer.parseInt(line[4].trim());
			Tarea nodo = new Tarea(nombre, tiempo, critica, prioridad);
			taskHashMap.put(id, nodo);
			// NUESTRO :: Aca tenemos que agregar nuestra estructura
			// Aca instanciar lo que necesiten en base a los datos leidos
		}
		return (HashMap<String, Tarea>) taskHashMap.clone();
	}
	
	public HashMap<String, Procesador> readProcessors(String processorPath) {
		
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent(processorPath);
		HashMap<String, Procesador> processHashMap = new HashMap<String, Procesador>();

		for (String[] line: lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String id = line[0].trim();
			String codigo = line[1].trim();
			Boolean refrigerado = Boolean.parseBoolean(line[2].trim());
			Integer anio = Integer.parseInt(line[3].trim());
			Procesador nodo = new Procesador(codigo, refrigerado, anio);
			processHashMap.put(id, nodo);

			// Aca instanciar lo que necesiten en base a los datos leidos
		}
		
		return (HashMap<String, Procesador>) processHashMap.clone();
	}

	private ArrayList<String[]> readContent(String path) {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return lines;
	}
	
}