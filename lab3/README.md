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
  - [ ] page.xhtml: describing the general aspect of the application pages: header, content, footer. The header should display the title and might include a menu bar.
  - [ ] The footer will display a copyright notice and the current version of the aplication. The header, footer and the menu bar should all be in separate .xhtml files.
  - [ ] dataView.xhtml: a generic page for displaying data as a list, dataTable, etc.
  - [ ] dataEdit.xhtml: a generic page for editing data. This could be a dialog containing a generic form.
- [ ] Create at least one composite component. For example, create a component for selecting an exam or a student, using an autocomplete.
- [ ] Use the components ajax and poll in order to continuously display how many active sessions are in progress or information about the execution of your algorithm or the students enlisted for an exam.
Implement an efficient way for obtaining connections to the database.
  - [ ] Configure a connection pool and a JDBC resource using an administrative tool (such as GlassFish/Payara Console or asadmin).
  - [ ] Create DatSource objects using either JNDI directly or resource injection.
  - [ ] Create a simple scenario in order to analyze the impact of various connection pool configuration properties (timeouts, connection leaking, etc)
