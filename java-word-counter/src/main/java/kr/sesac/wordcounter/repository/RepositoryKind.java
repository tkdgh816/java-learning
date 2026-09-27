package kr.sesac.wordcounter.repository;

public enum RepositoryKind {
    IN_MEMORY {
        @Override
        public TokenCountRepository createForNewAnalysis() {
            return new InMemoryRepository();
        }
    },
    JDBC {
        @Override
        public TokenCountRepository createForNewAnalysis() {
            return JdbcRepository.createForNewAnalysis();
        }
    };

    public abstract TokenCountRepository createForNewAnalysis();
}
