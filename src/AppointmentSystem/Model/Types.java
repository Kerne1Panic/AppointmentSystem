package AppointmentSystem.Model;

/**
 * @author josealvarezpulido
 */
public class Types {
    private int id;
    private String typeName;

    public Types(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    @Override
    public String toString(){
        return (typeName);
    }
}
