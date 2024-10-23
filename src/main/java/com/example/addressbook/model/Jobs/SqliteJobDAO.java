package com.example.addressbook.model.Jobs;

import com.example.addressbook.model.SQLiteJobConnection;
import com.example.addressbook.model.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteJobDAO {

    private Connection connection;

    public SqliteJobDAO() {
        connection = SQLiteJobConnection.getInstance();
        createTable();
        addJobs();
    }

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS jobs ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "jobName VARCHAR NOT NULL,"
                    + "category VARCHAR NOT NULL,"
                    + "salary INTEGER NOT NULL,"
                    + "degree VARCHAR NOT NULL,"
                    + "company VARCHAR NOT NULL,"
                    + "companyWebsite VARCHAR NOT NULL,"
                    + "description VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addJobs(){
        try {
            List<Job> jobs = new ArrayList<>();
            Job navy = new Job("Mechanical Engineer", "Engineering", 110000, "Bachelor", "Royal Australian Navy", "https://au.indeed.com/jobs?q=Mechanical+engineer&l=Brisbane+QLD&from=searchOnDesktopSerp&vjk=76e8287c0e650b62&advn=8285141529196758", "As a Mechanical Engineer, you’ll lead a team that looks after the mechanical aspects of the ship, both at sea and ashore. You’ll be responsible for the vessel’s structures and systems, anticipating and solving challenges, and making sure everything runs smoothly.");
            jobs.add(navy);
            Job lawyer = new Job("Property Lawyer", "Legal Professions", 105000, "Bachelor", "bytherules Conveyancing", "https://au.indeed.com/cmp/Bytherules-Conveyancing?from=mobviewjob&tk=1iapodbs6mv5e800&fromjk=f8ec67730a7b9b87&attributionid=mobvjcmp", "We are looking for Junior and Senior Queensland Property Lawyers for an award-winning Conveyancing Practice. Multiple positions are available based in our Brisbane office to join our team.");
            jobs.add(lawyer);
            Job Business = new Job("Principal Business Analyst", "Finance & Business Health Care IT", 91000, "Bachelor", "Queensland Health", "https://au.indeed.com/cmp/Queensland-Health?from=mobviewjob&tk=1iapp2uqdj3rk800&fromjk=c27a50aaa9a13d54&attributionid=mobvjcmp", "Cyber Security Program is looking for a Principal Business Analyst to lead, manage and undertake the business analysis activities such as requirements validation, gap and impact analysis, documentation of current state and future state business processes for large scale ICT projects in Cyber Security domain ensuring the delivery of valued, integrated, and effective business solutions to the program/project(s).");
            jobs.add(Business);
            Job Robotics = new Job("Robotics Engineer", "IT Engineering", 110000, "Masters", "Emesent", "https://au.indeed.com/cmp/Emesent-1?from=mobviewjob&tk=1iapqlulumv5e800&fromjk=24713b32adfe732d&attributionid=mobvjcmp", "We have an opening for a roboticist within the Robotics Group. You will be responsible for designing and implementing advanced, production grade autonomy capabilities for our Hovermap product. While you’re doing this, you’ll be getting great exposure to multiple areas in the field of robotics and engaging in your passion to make real robots do real things.");
            jobs.add(Robotics);
            Job Dean = new Job("Dean Higher Education", "Education", 145000, "Doctorate/PHD", "AIPC", "https://au.indeed.com/cmp/Aipc?from=mobviewjob&tk=1iapr7d9vmv5e800&fromjk=7a21f61d89c51fcb&attributionid=mobvjcmp", "Due to its growing presence in the Australian higher education sector, AIPC is seeking applications from experienced senior academics for the position of Dean – Higher Education.");
            jobs.add(Dean);
            for(Job job : jobs) {
                PreparedStatement checkStmt = connection.prepareStatement("Select Count(*) FROM jobs WHERE jobName = ?");
                checkStmt.setString(1,job.getJobName());
                ResultSet resultSet = checkStmt.executeQuery();
                resultSet.next();
                if(resultSet.getInt(1) == 0) {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO jobs (jobName, category, salary, degree, company, companyWebsite, description) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    statement.setString(1, job.getJobName());
                    statement.setString(2, job.getCategory());
                    statement.setInt(3, job.getSalary());
                    statement.setString(4, job.getDegree());
                    statement.setString(5, job.getCompanyWebsite());
                    statement.setString(6, job.getCompany());
                    statement.setString(7, job.getDescription());
                    statement.executeUpdate();
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        job.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM jobs";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String jobName = resultSet.getString("jobName");
                String category = resultSet.getString("category");
                Integer salary = resultSet.getInt("salary");
                String degree = resultSet.getString("degree");
                String companyWebsite=resultSet.getString("companyWebsite");
                String company = resultSet.getString("company");
                String description = resultSet.getString("description");

                Job job = new Job(jobName, category, salary, degree, companyWebsite, company, description);
                job.setId(id);
                jobs.add(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }
    public List<Job> queryJobs(String query) {
        List<Job> jobs = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String jobName = resultSet.getString("jobName");
                String category = resultSet.getString("category");
                Integer salary = resultSet.getInt("salary");
                String degree = resultSet.getString("degree");
                String companyWebsite=resultSet.getString("companyWebsite");
                String company = resultSet.getString("company");
                String description = resultSet.getString("description");

                Job job = new Job(jobName, category, salary, degree, companyWebsite, company, description);
                job.setId(id);
                jobs.add(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
