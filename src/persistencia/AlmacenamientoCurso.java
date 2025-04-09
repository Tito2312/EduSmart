package persistencia;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import mundo.Curso;


public class AlmacenamientoCurso {
	
	private File archivo;

    public AlmacenamientoCurso() {
        archivo = new File("Cursos.obj");
    }

    public void guardarCursos(List<Curso> cursos) {
    	
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(cursos);
        } catch (IOException e) {
        	
            System.out.println("Error al guardar los cursos: " + e.getMessage());
        }
    }

	public List<Curso> cargarCursos() {
    	
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
        	
            return (List<Curso>) ois.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
        	
            System.out.println("Error al cargar los cursos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
