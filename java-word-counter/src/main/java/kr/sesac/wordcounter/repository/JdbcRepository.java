package kr.sesac.wordcounter.repository;

import kr.sesac.wordcounter.model.TokenCount;

import java.sql.*;
import java.util.Collection;
import java.util.List;

public class JdbcRepository implements TokenCountRepository {
    private static final String URL =
            "jdbc:mysql://localhost:3307/wordcount?rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "wordcount";
    private static final String PASSWORD = "wordcount";

    private JdbcRepository() {
    }

    public static JdbcRepository createForNewAnalysis() {
        JdbcRepository repository = new JdbcRepository();
        repository.truncateTables();
        return repository;
    }

    public void checkConnection() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM word_counts")) {
            rs.next();
            System.out.println("연결 성공. 저장된 단어 " + rs.getLong(1) + "개");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void truncateTables() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE word_counts");
            statement.executeUpdate("TRUNCATE TABLE tokens");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getUniqueCount() {
        return 0;
    }

    @Override
    public void addAll(Collection<TokenCount> tokenCounts) {

    }

    @Override
    public List<TokenCount> findTop(int limit) {
        return List.of();
    }

    @Override
    public TokenCount findByToken(String token) {
        return null;
    }

    @Override
    public List<TokenCount> findAllSorted() {
        return List.of();
    }
}
