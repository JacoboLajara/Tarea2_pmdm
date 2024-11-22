package lajara.lopez.pmdm.jlltarea2;

public class CharacterData {
    private final int image;
    private final int image2;
    private final String name;
    private final String description;

    private final String skills;

    public CharacterData(int image, int image2, String name,String description, String skills){
        this.image = image;
        this.image2 = image2;
        this.name= name;
        this.description=description;
        this.skills=skills;

    }

    public int getImage() {
        return image;
    }

    public int getImage2() {
        return image2;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSkills() {
        return skills;
    }
}

