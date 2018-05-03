package com.eistdb.dao;

import com.eistdb.composite.StudentDepartment;
import com.eistdb.department.Department;
import com.eistdb.group.Group;
import com.eistdb.student.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDepartmentDAO extends AbstractDaoJdbc<StudentDepartment> {
    public StudentDepartmentDAO(String username, String password, String serverName, int port, String dbName) {
        super(username, password, serverName, port, dbName);
    }

    public List<StudentDepartment> getAllByDepartment(String department) {
        List<StudentDepartment> students = new ArrayList<>();

        try (Connection connection = getConnection()) {

            String sql = "SELECT * FROM PERSON" +
                    " INNER JOIN STUDENT ON PERSON.Student_ID=STUDENT.Student_ID" +
                    " INNER JOIN STUDENT_GROUP ON STUDENT.Student_ID=STUDENT_GROUP.Student_ID" +
                    " INNER JOIN GROUPS ON STUDENT_GROUP.Group_ID=GROUPS.Group_ID" +
                    " INNER JOIN SPECIALITY ON GROUPS.Speciality_ID=SPECIALITY.Speciality_ID" +
                    " INNER JOIN CAFEDRA ON SPECIALITY.Cafedra_ID=CAFEDRA.Cafedra_ID" +
                    " WHERE CAFEDRA.Cafedra_name = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, department);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    students.add(getObjectFromResult(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public boolean update(StudentDepartment object) {
        return false;
    }

    @Override
    protected StudentDepartment getObjectFromResult(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("Student_ID"));
        student.getPerson().setSurname(resultSet.getString("Surname"));
        student.getPerson().setName(resultSet.getString("Name"));
        student.getPerson().setPatronymic(resultSet.getString("Patronymic"));
        student.getPerson().setBirthDate(resultSet.getDate("Birth_date").toLocalDate());
        student.getPerson().setSex(resultSet.getString("Sex"));
        student.getPerson().setBirthPlace(resultSet.getString("Birth_place"));
        student.getPerson().setAddress(resultSet.getString("Address"));
        student.getPerson().setPhone(resultSet.getString("Telephon"));
        student.setBookNumber(resultSet.getString("Book_no"));
        student.setNote(resultSet.getString("Note"));

        Group group = new Group();
        group.setCode(resultSet.getString("Group_code"));
        group.setCreationDate(resultSet.getDate("Group_creat_date").toLocalDate());

        Department department = new Department();
        department.setId(resultSet.getLong("Cafedra_ID"));
        department.setName(resultSet.getString("Cafedra_name"));
        department.setCode(resultSet.getString("Cafedra_shifr"));
        return new StudentDepartment(student, group, department);
    }

    @Override
    protected String getTableName() {
        return "STUDENT";
    }
}
