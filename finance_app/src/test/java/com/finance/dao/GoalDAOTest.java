package com.finance.dao;

import com.finance.config.DatabaseConfig;
import com.finance.model.Goal;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GoalDAOTest {

    private GoalDAO goalDAO;
    private Connection conn;

    @BeforeAll
    public void setupDatabase() {
        DatabaseConfig.createTables();
        goalDAO = new GoalDAO();
    }

    @BeforeEach
    public void connect() {
        conn = DatabaseConfig.getConnection();
    }

    @AfterEach
    public void cleanUp() throws Exception {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM goals")) {
            stmt.executeUpdate();
        }
        conn.close();
    }

    @Test
    public void testInsertGoal() {
        Goal goal = new Goal("Save for Holiday", 1500.00, 6);
        goalDAO.insertGoal(goal);

        List<Goal> allGoals = goalDAO.getAllGoals();
        assertEquals(1, allGoals.size());

        Goal inserted = allGoals.get(0);
        assertEquals("Save for Holiday", inserted.getName());
        assertEquals(1500.00, inserted.getSpendingLimit());
        assertEquals(6, inserted.getDuration());
    }

    @Test
    public void testDeleteGoal() {
        Goal goal = new Goal("Emergency Fund", 2000.00, 12);
        goalDAO.insertGoal(goal);

        goalDAO.deleteGoal("Emergency Fund");
        List<Goal> allGoals = goalDAO.getAllGoals();
        assertTrue(allGoals.isEmpty());
    }

    @Test
    public void testGetAllGoalsMultiple() {
        goalDAO.insertGoal(new Goal("Car Maintenance", 500.00, 3));
        goalDAO.insertGoal(new Goal("Gym Membership", 300.00, 12));

        List<Goal> allGoals = goalDAO.getAllGoals();
        assertEquals(2, allGoals.size());
    }
}