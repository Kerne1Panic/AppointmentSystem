package AppointmentSystem.DAOImp;

import AppointmentSystem.DAOInterface.CustomersInt;
import AppointmentSystem.Model.Customers;
import AppointmentSystem.Utilities.QueryUtil;
import AppointmentSystem.Utilities.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;


/**
 * @author josealvarezpulido
 */
public class CustomersImp implements CustomersInt {
    //ObservableList of all customers.
     static ObservableList<Customers> customers = FXCollections.observableArrayList();

    public static ObservableList<Customers> getAllCustomers()
    {
        if(customers != null){
            customers.clear();
        }
        String sqlStatement = "SELECT * FROM customers";
        try{
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //while next() loop
            while (rs.next())
            {
                //values that are being extracted.
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDate date = rs.getDate("Create_Date").toLocalDate();
                LocalTime time = rs.getTime("Create_Date").toLocalTime();
                ZonedDateTime createDate = TimeUtil.mergeDateTime(date,time);
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastDateTime = rs.getTimestamp("Last_Update").toLocalDateTime();
                ZonedDateTime lastUpdate = TimeUtil.mergeDateTime(lastDateTime);
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionId = rs.getInt("Division_ID");
                Customers customersFound = new Customers(customerID,customerName,address,postalCode,phone,createDate,createdBy,lastUpdate,lastUpdatedBy,divisionId);
                customers.add(customersFound);
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return customers;
    }


    public static void updateCustomers(String name,String address,String postalCode,String phone,int divisionId, int customerId) {
        String sqlStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = Current_Timestamp, last_Updated_By = ? , Division_ID  = ? WHERE Customer_ID = ?";
        try {
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.setString(1,name);
            ps.setString(2,address);
            ps.setString(3,postalCode);
            ps.setString(4,phone);
            ps.setString(5,UsersImp.getUserLoggedIn());
            ps.setInt(6,divisionId);
            ps.setInt(7,customerId);

            ps.execute();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public static void deleteCustomers(int customerID)
    {
        String sqlStatement = "DELETE FROM customers WHERE Customer_ID = ?";
        try{
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.setInt(1,customerID);
            ps.execute();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     */
    public static void addCustomers(String name, String address, String postalCode, String phone, int divisionId) {
        String sqlStatement = "INSERT INTO customers(Customer_Name,Address,Postal_Code,Phone,Create_Date,Created_By,Division_ID)" +
                "VALUES(?,?,?,?,CURRENT_TIMESTAMP,?,?)";
        try{
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.setString(1,name);
            ps.setString(2,address);
            ps.setString(3,postalCode);
            ps.setString(4,phone);
            ps.setString(5,UsersImp.getUserLoggedIn());
            ps.setInt(6,divisionId);

            ps.execute();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
