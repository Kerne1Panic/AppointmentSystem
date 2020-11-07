package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * @author josealvarezpulido
 * Class used to add new Types, get the Type using the typeName as a string, and storing and retriving a list of all types used to set comboxes of the Type Object model.
 */
public class TypesImp {
    /**
     * List of all Types that are initialized and used in the app, it is set and returned in the getAllTypes static method.
     */
    static ObservableList<Types> allTypes = FXCollections.observableArrayList();

    /**
     * Static method that return the allTypes observable list which contains all the types that where initialized in the app.
     * @return allTypes
     */
    public static ObservableList<Types> getAllTypes(){

        return allTypes;
    }

    /**
     * adds Objects of the class Type to the allTypes List.
     * @param types
     */
    public static void addTypes(Types types){

        allTypes.add(types);
    }

    /**
     * takes in the typeName as a String parameter and returns the type that matches with that typeName.
     * @param typeName String
     * @return the matching Type from the allTypes list, null if there is no match.
     */
    public static Types getType(String typeName){
        for(Types type : allTypes){
            if(type.getTypeName().contains(typeName))
                return type;
        }
        return null;
    }
}
