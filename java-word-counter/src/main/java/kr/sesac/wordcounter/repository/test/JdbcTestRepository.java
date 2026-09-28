package kr.sesac.wordcounter.repository.test;

import kr.sesac.wordcounter.model.TokenCount;
import kr.sesac.wordcounter.repository.RepositoryException;
import kr.sesac.wordcounter.repository.TokenCountRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JdbcTestRepository implements TokenCountRepository {
    private static final String URL =
            "jdbc:mysql://localhost:3307/wordcount?rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "wordcount";
    private static final String PASSWORD = "wordcount";

    private JdbcTestRepository() {
    }

    public static JdbcTestRepository createForNewAnalysis() {
        JdbcTestRepository repository = new JdbcTestRepository();
        repository.truncateTables();
        return repository;
    }

    public JdbcTestSession createSession(DbAggregationMode dbAggregationMode) throws RepositoryException {
        try {
            return new JdbcTestSession(DriverManager.getConnection(URL, USER, PASSWORD), dbAggregationMode);
        } catch (SQLException e) {
            throw new RepositoryException("", e);
        }
    }

    public void checkConnection() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM word_counts")) {
            rs.next();
            System.out.println("연결 성공. 저장된 단어 " + rs.getLong(1) + "개");
        } catch (SQLException e) {
            throw new RepositoryException("", e);
        }
    }

    public void truncateTables() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE word_counts");
            statement.executeUpdate("TRUNCATE TABLE tokens");
        } catch (SQLException e) {
            throw new RepositoryException("", e);
        }
    }

    @Override
    public long getUniqueCount() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM word_counts")) {
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RepositoryException("", e);
        }
    }

    @Override
    public void addAll(Collection<TokenCount> tokenCounts) {
        throw new RepositoryException("");
    }

    @Override
    public List<TokenCount> findTop(int limit) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("""
                     SELECT word, count
                     FROM word_counts
                     ORDER BY count DESC, word ASC
                     LIMIT ?
                     """)) {
            statement.setInt(1, limit);
            try (ResultSet rs = statement.executeQuery()) {
                List<TokenCount> tokenCounts = new ArrayList<>();
                while (rs.next()) {
                    tokenCounts.add(new TokenCount(rs.getString(1), rs.getLong(2)));
                }
                return tokenCounts;
            }
        } catch (SQLException e) {
            throw new RepositoryException("", e);
        }
    }

    @Override
    public TokenCount findByToken(String token) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("""
                     SELECT count
                     FROM word_counts
                     WHERE word = ?"""
             )) {
            statement.setString(1, token);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new TokenCount(token, rs.getLong(1));
                }
                return new TokenCount(token, 0L);
            }
        } catch (SQLException e) {
            throw new RepositoryException("", e);
        }
    }

    @Override
    public List<TokenCount> findAllSorted() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("""
                     SELECT word, count
                     FROM word_counts
                     ORDER BY count DESC, word ASC
                     """)) {
            List<TokenCount> tokenCounts = new ArrayList<>();
            while (rs.next()) {
                tokenCounts.add(new TokenCount(rs.getString(1), rs.getLong(2)));
            }
            return tokenCounts;
        } catch (SQLException e) {
            throw new RepositoryException("", e);
        }
    }
}
