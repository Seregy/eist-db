package com.eistdb.dao;

import com.eistdb.composite.SubjectSemester;
import com.eistdb.semester.Semester;
import com.eistdb.subject.Subject;
import com.eistdb.test.TestKind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectSemesterDAO extends AbstractDaoJdbc<SubjectSemester> {
    private static final String TEST_KIND = "exam";

    public SubjectSemesterDAO(String username, String password, String serverName, int port, String dbName) {
        super(username, password, serverName, port, dbName);
    }

    public List<SubjectSemester> getAllBySemester(Long semesterId) {
        List<SubjectSemester> subjects = new ArrayList<>();

        try (Connection connection = getConnection()) {

            String sql = "SELECT * FROM SUBJECT" +
                    " INNER JOIN TEACH_PLAN ON SUBJECT.Subject_ID=TEACH_PLAN.Subject_ID" +
                    " INNER JOIN STEST_KIND ON TEACH_PLAN.Test_kind_ID=STEST_KIND.Test_kind_ID" +
                    " INNER JOIN SEMSTER ON TEACH_PLAN.Semester_ID=SEMSTER.Semester_ID" +
                    " WHERE SEMSTER.Semester_ID = ? AND STEST_KIND.Test_kind_name = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, semesterId);
                statement.setString(2, TEST_KIND);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    subjects.add(getObjectFromResult(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    @Override
    public boolean update(SubjectSemester object) {
        return false;
    }

    @Override
    protected SubjectSemester getObjectFromResult(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("Subject_ID"));
        subject.setName(resultSet.getString("Subject_name"));
        subject.setCode(resultSet.getString("Subject_shifr"));

        Semester semester = new Semester();
        semester.setId(resultSet.getLong("Semester_ID"));
        semester.setTeachBegin(resultSet.getDate("Teach_begin_date").toLocalDate());
        semester.setTeachEnd(resultSet.getDate("Teach_end_date").toLocalDate());
        semester.setSessionBegin(resultSet.getDate("Session_begin_date").toLocalDate());
        semester.setSessionEnd(resultSet.getDate("Session_end_date").toLocalDate());
        semester.setAttest1(resultSet.getDate("Attest1_date").toLocalDate());
        semester.setAttest2(resultSet.getDate("Attest2_date").toLocalDate());

        TestKind testKind = new TestKind();
        testKind.setId(resultSet.getLong("Test_kind_ID"));
        testKind.setName(resultSet.getString("Test_kind_name"));

        return new SubjectSemester(subject, semester, testKind);
    }

    @Override
    protected String getTableName() {
        return "SUBJECT";
    }
}
