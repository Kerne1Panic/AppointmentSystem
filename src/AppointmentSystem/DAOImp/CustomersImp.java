package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Customers;
import AppointmentSystem.Utilities.QueryUtil;
import AppointmentSystem.Utilities.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.*;
/**
 * @author josealvarezpulido
 * Used to perfomr the CRUD operations with the data base of the Customers Class Model.
 * Create Read Update Delete.
 */
public class CustomersImp {
    /**
     * List of all customers extracted from the database, it is set and returned in the getAllCustomers static method.
     */
     static ObservableList<Customers> customers = FXCollections.observableArrayList();

    /**
     * Read, static method used to communicate with database using jdbc, and uses a sql Select Statement to read in all the values and create a new Customer Object.
     * @return the static method observable list customers.
     */
    public static ObservableList<Customers> getAllCustomers()
    {
        /**
         * used to clear the list if the list is not clear, this is because a possible error when reusing the static method causes the same list to be appended again.
         */
        if(customers != null){
            customers.clear();
        }
        /**
         * A Join SQL statement is used to retrieve the name of the first_level_division, so that the name can be stored in the object and later used in the program.
         */
        String sqlStatement = "SELECT * FROM customers, first_level_divisions WHERE" +
                " customers.Division_ID = first_level_divisions.Division_ID";
        /**
         * try catch block used for exception handling, catching wrong sql statements so the app does not crash in that event.
         */
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
                ZonedDateTime createDate = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(date,time),ZoneId.of("UTC")));
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastDateTime = rs.getTimestamp("Last_Update").toLocalDateTime();
                ZonedDateTime lastUpdate = TimeUtil.convertBack(ZonedDateTime.of(lastDateTime,ZoneId.of("UTC")));
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionId = rs.getInt("Division_ID");
                String  divisionName = rs.getString("Division");
                /**
                 * Customers are defined and stored in the customers ObservableList to be returned when calling this static function.
                 */
                Customers customersFound = new Customers(customerID,customerName,address,postalCode,phone,createDate,createdBy,lastUpdate,lastUpdatedBy,divisionId,divisionName);
                customers.add(customersFound);
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return customers;
    }
    /**
     * Update, static update method used for Customers in the database using the customerId attribute of the Customer model object, The rest of the parameters are used to as the data that will be updated.
     * @param name customer name
     * @param address customer address
     * @param postalCode postal code
     * @param phone phone number
     * @param lastUpdated Date Time of the last customer update
     * @param divisionId ID number of the division ID
     * @param customerId ID number of the customer used to ID which customer to update.
     */
    public static void updateCustomers(String name,String address,String postalCode,String phone, LocalDateTime lastUpdated, int divisionId, int customerId) {
        /**
         * sqlStatement is a string of the SQL UPDATE Statement used to update the customers in the database.
         */
        String sqlStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, last_Updated_By = ? , Division_ID  = ? WHERE Customer_ID = ?";
        /**
         * try catch block used for exception handling, catching wrong sql statements so the app does not crash in that event.
         */
        try {
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.setString(1,name);
            ps.setString(2,address);
            ps.setString(3,postalCode);
            ps.setString(4,phone);
            ps.setTimestamp(5,Timestamp.valueOf(lastUpdated));
            ps.setString(6,UsersImp.getUserLoggedIn());
            ps.setInt(7,divisionId);
            ps.setInt(8,customerId);

            ps.execute();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Delete ,static method used to delete customers using a SQL delete statement.
     * @param customerID The ID number of the customer that will be delete.
     */
    public static void deleteCustomers(int customerID)
    {
        /**
         * sqlStatement holds the String of the SQL Delete statement. uses the QueryUtil to set the prepared statement, declare a prepared statement and execute it.
         */
        String sqlStatement = "DELETE FROM customers WHERE Customer_ID = ?";
        /**
         * try catch block used for exception handling, catching wrong sql statements so the app does not crash in that event.
         */
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
     * Create, static method creates(adds) new customers and stores them in the database using a SQL INSERT INTO statement.
     * The parameters are the values that will be used to create the new object.
     * @param name name of customer
     * @param address address of customer
     * @param postalCode postal code of customer
     * @param phone phone number of customer
     * @param createDate Date Time of creation
     * @param divisionId Division ID of where customer resides
     */
    public static void addCustomers(String name, String address, String postalCode, String phone, LocalDateTime createDate, int divisionId) {
        /**
         * SQL Statement used to insert into the data base.
         */
        String sqlStatement = "INSERT INTO customers(Customer_Name,Address,Postal_Code,Phone,Create_Date,Created_By,Division_ID,Last_Update)" +
                "VALUES(?,?,?,?,?,?,?,?)";
        /**
         * try catch block used for exception handling, catching wrong sql statements so the app does not crash in that event.
         */
        try{
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.setString(1,name);
            ps.setString(2,address);
            ps.setString(3,postalCode);
            ps.setString(4,phone);
            ps.setTimestamp(5, Timestamp.valueOf(createDate));
            ps.setString(6,UsersImp.getUserLoggedIn());
            ps.setInt(7,divisionId);
            ps.setTimestamp(8,Timestamp.valueOf(createDate));
            ps.execute();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
