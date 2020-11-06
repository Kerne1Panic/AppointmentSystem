package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TypesImp {
    static ObservableList<Types> allTypes = FXCollections.observableArrayList();
    public static void addTypes(Types types){
        allTypes.add(types);
    }
    public static ObservableList<Types> getAllTypes(){
        return allTypes;
    }
    public static Types getType(String typeName){
        for(Types type : allTypes){
            if(type.getTypeName().contains(typeName))
                return type;
        }
        return null;
    }
}
