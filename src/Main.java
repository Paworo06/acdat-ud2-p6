import java.io.*;        // ObjectOutputStream / ObjectInputStream
import java.nio.file.*;  // Paths y Files
import java.util.*;      // List y ArrayList

public class Main {

    // Fichero binario donde guardar/leer la lista serializada
    private static final Path FICHERO = Paths.get("personas.dat");

    public static void main(String[] args) {
        // Realiza las siguientes tareas, agrupa en funciones cuando veas oportuno
        //TODO: 1) Crear una lista de personas
        Scanner sc = new Scanner(System.in);
        List<Persona> personas = new ArrayList<>();

        List<Persona> personasLeidas = new ArrayList<>();
        String cadena;

        //TODO: 2) Añadir personas (por consola)
        for (int i = 0; i < 2; i++) {
            System.out.print("Dime una personita socio: ");
            cadena = sc.nextLine();

            Persona persona = new Persona(cadena);
            personas.add(persona);
        }

        //TODO: 3) Serializar la lista al fichero

        try {

            File archivo = new File("persona.dat");
            if (archivo.exists()) {
                archivo.delete();
            }

            FileOutputStream fos = new FileOutputStream("persona.dat", true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for  (Persona persona : personas) {
                oos.writeObject(persona);
                System.out.println("Objeto serializado correctamente.");
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //TODO: 4) Deserializar la lista desde el fichero

        try {
            FileInputStream fis = new FileInputStream("persona.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    // Leer el objeto
                    Persona obj = (Persona) ois.readObject();
                    personasLeidas.add(obj);

                } catch (EOFException e) {
                    // Fin del archivo alcanzado
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        //TODO: 5) Imprimir por consola las personas deserializadas
        int contador = 0;
        for (Persona personaLeida : personasLeidas) {
            System.out.println("Persona (" + (++contador) + "): " + personaLeida);
        }
    }
}