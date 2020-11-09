Multinational Appointment System

The purpose of this application is to serve as a Client, and appointment management system for small businesses.
It includes a login screen, connection to a database containing customers, appointments and other relevant data.
The user has the ability to add, update, delete customers and appointments, as well as view reports for types of appointments by month,
a schedule of upcoming appointments for a contact, and a list of users and customers the user has created.

author: Jose Alvarez Pulido
contact information: jalv177@wgu.edu
application version: 1.23
11/09/2020

IntelliJ IDEA 2020.2.3 (Community Edition)
Build #IC-202.7660.26, built on October 6, 2020
Runtime version: 11.0.8+10-b944.34 x86_64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
macOS 10.15.7
GC: ParNew, ConcurrentMarkSweep
Memory: 990M
Cores: 4
Non-Bundled Plugins: org.jetbrains.kotlin
mysql:mysql-connector-java:5.1.48

description:
The opening view will be the login screen, username: admin password:admin, enter key would be the action needed to login from either username field or password field.
The next view is the main menu where user could navigate to (from top to bottom) the customer menu, appointment menu, reports, exit back to login.
The customer menu will contain a tableView and 3 buttons to add, update, remove and a cancel button to go back to main menu.
    - The customer add button will navigate to add customer screen, the country must be selected before the division, there is a save button to create the customer and cancel to navigate back without saving.
    - The customer update button requires a customer to be selected from the table view, save button to update the customer and cancel to navigate back without saving.
    - The remove button will trigger an alert confirmation box, if Ok is selected the customer will be deleted as well as the appointments that customer had.
The appointment menu will contain a tableView 3 buttons to add, update, remove; as well as 3 filters weekly, monthly, no filter.
Weekly which will set the tableView to show only appointments for the current week.
Monthly which will set the tableView to show only appointments for the current month.
No filter removes the filter to view all appointments.
    - The Add appointment menu includes fields for creating appointments, the start comboBox must be selected before the end comboBox,
    the start datePicker sets the end datePicker since for this app appointments must end in the max appointment length of 2 hours, the 2 hours will not pass closing time of 10:00pm EST.
    Save button will save the appointment, cancel will navigate back without saving.
    - The update appointment menu follows along with the same requirements as the add update menu.
    Save button will update the appointment, cancel will navigate back without saving.
    -The remove appointment will trigger a conformation alert if Ok is selected the appointment will be deleted.
The reports menu will contain 3 buttons and a cancel button, the cancel button will navigate back to the main menu.
    -The appointments button will display the appointment counts by Type and Month in the Text area.
    -The Schedule button will display a schedule for the contact including contact name, appointmentId, title, type, description, start date/time, end date/time, customerId.
    -The User button will display user created customers and the name of the user that created them.
User login attempts will be recorded and kept track of failed and successful logins in the login_activity.txt file.

additional report A3f is the User button in the reports menu.
It will display a report of 'Users Created Customers:' followed by an appended list of the User name followed by 'Created Customer:' followed by the customer name of the customer that the user created.
Does not included customers created by a script.



