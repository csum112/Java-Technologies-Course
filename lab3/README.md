## Java Technologies - Lab 3

- [x] A Web page for defining the exams. Each exam has a name, a starting time and a duration.
- [x] A Web page for defining the students that must attend the exams. Each student has a name and the list of exams.
- [x] Use a relational database and JDBC in order to store and retrieve data. (PostgreSQL is recommended).
- [x] Use at least one non trivial JSF component, for example a data table, a dialog, etc.
- [x] Internationalize the user interface and offer support for at least two locales.
- [ ] Create an algorithm for solving the problem such that the number of days in the session is minimum.
- [ ] Create a page for displaying the results.
- [ ] Create a random generator that produces problem instances of various sizes and test your algorithm.
- [ ] A bonus may be awarded for an "efficient" implementation of the algorithm.



## Java Technologies - Lab 4


Create the pages using templates:
  - [x] page.xhtml: describing the general aspect of the application pages: header, content, footer. The header should display the title and might include a menu bar.
  - [ ] The footer will display a copyright notice and the current version of the aplication. The header, footer and the menu bar should all be in separate .xhtml files.
  - [x] dataView.xhtml: a generic page for displaying data as a list, dataTable, etc.
  - [ ] dataEdit.xhtml: a generic page for editing data. This could be a dialog containing a generic form.
- [ ] Create at least one composite component. For example, create a component for selecting an exam or a student, using an autocomplete.
- [ ] Use the components ajax and poll in order to continuously display how many active sessions are in progress or information about the execution of your algorithm or the students enlisted for an exam.
Implement an efficient way for obtaining connections to the database.
  - [x] Configure a connection pool and a JDBC resource using an administrative tool (such as GlassFish/Payara Console or asadmin).
  - [ ] Create DatSource objects using either JNDI directly or resource injection.
  - [ ] Create a simple scenario in order to analyze the impact of various connection pool configuration properties (timeouts, connection leaking, etc)

## Java Technologies - Lab 5
(2p) Rewrite the persistence layer of the application created for the previous laboratory using a technology that implements the JPA specifications.
  - [x] Define the persistence unit using a data source configured as a JDBC Resource.
  - [x] Create the EntityManager objects using dependency injection.
  - [x] Define the mappings using JPA-only annotations.
  - [x] Implement the repository classes using JPA-QL.
  - [ ] Create a test unit for the CRUD operations of at least one entity class. (Ecountered difficulties with jUnit5, weld-junit5, h2 and wildfly)
(1p) Exams can be of various types, for example written test, project presentation, etc, each having specific properties, such as bibliography or required resources, etc.
  - [x] Define at least two type of classes representing exams. Use inheritance mapping in order to define this new model. ~Adapt the user interface accordingly.~
#### Curent status: Backend only implementation, didnt manage to add the UI requirements.
(1p) Add an "exam search page". This page will allow specifying various filters: exam name, a student name, a time, etc.
  - [ ] Each filter will have a checkox - if it is checked then the filter will be taken into consideration.
  - [x] The query must be implemented using JPA Criteria API.

#### The lack of UI is due to poor time management, and various wildfly & primefaces encountered issues. (L4&L5)


## Java Technologies - Lab 6
Enterprise Java Beans (EJB)
- [x] (2p) Rewrite the data access layer of the application created in the previous laboratories, implementing the repository classes as Enterprise Java Beans.
- [x] Use the support offered by the EJB technology for implementing transactions.
- [x] Suppose that each exam must be assigned some resources (for example: a room, a video projector, etc) and each resource is in limited number. Create a "reservation" page, allowing the reservation of a group of resources for a specific exam (all of them, or none).
- [x] The following enterprise beans must be implemented:
- [x] A stateless session bean that offers methods for checking the availability of a resource.
- [x] A stateful session bean responsible with the assignment of one or more resource to a specific exam. The assignment should be atomic, either all resources are successfully assigned, or the transaction will be rolled back.
- [x] A singleton session bean that keeps an in-memory map of the current assignments. The map will be instantiated at application startup and updated whenever the assignments change.
- [] (2p) Create a test case that highlights the performance gain of using various forms of JPA optimizations: second level cache, lazy loading and entity graphs, etc.
- [x] Use an EJB interceptor in order to monitor the running time of a business method.
- [x] Create a timer that will trigger the invocation of the business method, using a specified schedule.
