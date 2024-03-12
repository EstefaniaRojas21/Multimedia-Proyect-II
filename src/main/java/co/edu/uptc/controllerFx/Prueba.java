package co.edu.uptc.controllerFx;

import co.edu.uptc.model.Movie;

public class Prueba {
    
    
    private static Prueba instance;
    private String nombre;

    
        public Prueba() {
            // constructor privado
        }
    
        public static Prueba getInstance() {
            if (instance == null) {
                instance = new Prueba();
            }
            return instance;
        }

        public void GuardarPelicula (String nombre){
            this.nombre = nombre;
        }

        public String nombreMov(){
            return nombre;
        } 
    

}
