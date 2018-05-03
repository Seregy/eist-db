package com.eistdb.dao;

import com.eistdb.composite.SubjectTeacher;
import com.eistdb.lesson.Lesson;
import com.eistdb.lesson.LessonKind;
import com.eistdb.person.Person;
import com.eistdb.subject.Subject;
import com.eistdb.teachplan.TeachPlan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectTeacherDAO extends AbstractDaoJdbc<SubjectTeacher> {
    private static final boolean OBLIGATORY = true;

    public SubjectTeacherDAO(String username, String password, String serverName, int port, String dbName) {
        super(username, password, serverName, port, dbName);
    }

    public List<SubjectTeacher> getAllByTeacherId(Long teacherId) {
        List<SubjectTeacher> subjects = new ArrayList<>();

        try (Connection connection = getConnection()) {

            String sql = "SELECT * FROM SUBJECT" +
                    " INNER JOIN TEACH_PLAN ON SUBJECT.Subject_ID=TEACH_PLAN.Subject_ID" +
                    " INNER JOIN LESSON ON TEACH_PLAN.Teach_plan_ID=LESSON.Teach_plan_ID" +
                    " INNER JOIN PERSON ON LESSON.Teacher_ID=Person.Person_ID" +
                    " WHERE PERSON.Person_ID = ? AND TEACH_PLAN.Is_obligatory = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, teacherId);
                statement.setBoolean(2, OBLIGATORY);
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
    public boolean update(SubjectTeacher object) {
        return false;
    }

    @Override
    protected SubjectTeacher getObjectFromResult(ResultSet resultSet) throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setId(resultSet.getLong("Lesson_ID"));
        lesson.setHours(resultSet.getString("Hours"));
        LessonKind lessonKind = new LessonKind(resultSet.getLong("Lesson_kind_ID"),
                resultSet.getString("Lesson_kind_name"));
        lesson.setLessonKind(lessonKind);

        Person person = new Person();
        person.setId(resultSet.getLong("Person_ID"));
        person.setSurname(resultSet.getString("Surname"));
        person.setName(resultSet.getString("Name"));
        person.setPatronymic(resultSet.getString("Patronymic"));

        TeachPlan teachPlan = new TeachPlan();
        teachPlan.setId(resultSet.getLong("Teach_plan_ID"));
        teachPlan.setObligatory(resultSet.getBoolean("Is_obligatory"));

        Subject subject = new Subject();
        subject.setId(resultSet.getLong("Subject_ID"));
        subject.setName(resultSet.getString("Subject_name"));
        subject.setCode(resultSet.getString("Subject_shifr"));
        return new SubjectTeacher(lesson, person, teachPlan, subject);
    }

    @Override
    protected String getTableName() {
        return "SUBJECT";
    }
}
