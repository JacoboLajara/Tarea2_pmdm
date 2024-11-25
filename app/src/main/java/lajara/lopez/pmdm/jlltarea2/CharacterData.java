package lajara.lopez.pmdm.jlltarea2;

/**
 * La clase CharacterData representa un personaje con múltiples imágenes, un nombre,
 * una descripción y habilidades.
 */
public class CharacterData {
    private final int image;
    private final int image2;
    private final String name;
    private final String description;
    private final String skills;

    /**
     * Constructor para crear una instancia de CharacterData.
     *
     * @param image  La primera imagen del personaje.
     * @param image2 La segunda imagen del personaje.
     * @param name   El nombre del personaje. * @param description La descripción del personaje.
     * @param skills Las habilidades del personaje.
     */

    public CharacterData(int image, int image2, String name, String description, String skills) {
        this.image = image;
        this.image2 = image2;
        this.name = name;
        this.description = description;
        this.skills = skills;

    }

    /**
     * Obtiene la primera imagen del personaje.
     *
     * @return La primera imagen del personaje.
     */

    public int getImage() {
        return image;
    }

    /*
     * Obtiene la segunda imagen del personaje.
     * @return La segunda imagen del personaje.
     * */

    public int getImage2() {
        return image2;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return El nombre del personaje.
     */

    public String getName() {
        return name;
    }

    /**
     * Obtiene la descripción del personaje.
     *
     * @return La descripción del personaje.
     */

    public String getDescription() {
        return description;
    }

    /**
     * Obtiene las habilidades del personaje.
     *
     * @return Las habilidades del personaje.
     */

    public String getSkills() {
        return skills;
    }
}

