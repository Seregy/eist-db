package com.eistdb.dao;

import com.eistdb.composite.StudentMark;
import com.eistdb.group.Group;
import com.eistdb.mark.Mark;
import com.eistdb.student.Student;
import com.eistdb.subject.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentMarkDAO extends AbstractDaoJdbc<StudentMark> {
    public StudentMarkDAO(String username, String password, String serverName, int port, String dbName) {
        super(username, password, serverName, port, dbName);
    }

    public List<StudentMark> getAllByGroup(String group) {
        List<StudentMark> marks = new ArrayList<>();

        try (Connection connection = getConnection()) {

            String sql = "SELECT * FROM PERSON" +
                    " INNER JOIN STUDENT ON PERSON.Student_ID=STUDENT.Student_ID" +
                    " INNER JOIN STUDENT_GROUP ON STUDENT.Student_ID=STUDENT_GROUP.Student_ID" +
                    " INNER JOIN GROUPS ON STUDENT_GROUP.Group_ID=GROUPS.Group_ID" +
                    " INNER JOIN STUDENT_MARKS ON STUDENT.Student_ID=STUDENT_MARKS.Student_ID" +
                    " INNER JOIN SMARK ON STUDENT_MARKS.Mark_ID=SMARK.Mark_ID" +
                    " INNER JOIN TEACH_PLAN ON STUDENT_MARKS.Teach_plan_ID=TEACH_PLAN.Teach_plan_ID" +
                    " INNER JOIN SUBJECT ON TEACH_PLAN.Subject_ID=SUBJECT.Subject_ID" +
                    " WHERE GROUPS.Group_code = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, group);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    marks.add(getObjectFromResult(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return marks;
    }

    @Override
    public boolean update(StudentMark object) {
        return false;
    }

    @Override
    protected StudentMark getObjectFromResult(ResultSet resultSet) throws SQLException {
        StudentMark studentMark = new StudentMark();

        Student student = new Student();
        student.setId(resultSet.getLong("Student_ID"));
        student.getPerson().setSurname(resultSet.getString("Surname"));
        student.getPerson().setName(resultSet.getString("Name"));
        student.getPerson().setPatronymic(resultSet.getString("Patronymic"));
        student.setBookNumber(resultSet.getString("Book_no"));
        studentMark.setStudent(student);

        Group group = new Group();
        group.setCode(resultSet.getString("Group_code"));
        studentMark.setGroup(group);

        Mark mark = new Mark();
        mark.setMark(resultSet.getString("Mark_name"));
        studentMark.setMark(mark);

        Subject subject = new Subject();
        subject.setName(resultSet.getString("Subject_name"));
        studentMark.setSubject(subject);

        return studentMark;
    }

    @Override
    protected String getTableName() {
        return "STUDENT_MARKS";
    }
}
