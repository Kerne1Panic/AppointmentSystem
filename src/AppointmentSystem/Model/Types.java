package AppointmentSystem.Model;

/**
 * Types Class used to define Types.
 * @author josealvarezpulido
 */
public class Types {
    /**
     * The ID of the Type
     */
    private int id;
    /**
     * The name of the Type.
     */
    private String typeName;

    /**
     * The constructor that sets the attribute values when creating a new Type.
     * @param id sets the id.
     * @param typeName sets the typeName.
     */
    public Types(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    /**
     * method for returning the id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * method for setting the id for the Types.
     * @param id sets the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * method for returning the typeName.
     * @return typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * method for setting the typeName for the Types.
     * @param typeName sets typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    /**
     * This method overrides the toString method used to set an Object to a string.
     * @return typeName.
     */
    @Override
    public String toString(){
        return (typeName);
    }
}
