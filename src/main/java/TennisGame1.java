public class TennisGame1 implements TennisGame {

    private static final String PLAYER_1 = "player1";
    private static final String LOVE_ALL = "Love-All";
    private static final String FIFTEEN_ALL = "Fifteen-All";
    private static final String THIRTY_ALL = "Thirty-All";
    private static final String DEUCE = "Deuce";
    private static final String ADVANTAGE_PLAYER1 = "Advantage player1";
    private static final String ADVANTAGE_PLAYER2 = "Advantage player2";
    private static final String WIN_FOR_PLAYER1 = "Win for player1";
    private static final String WIN_FOR_PLAYER2 = "Win for player2";
    private static final String LOVE = "Love";
    private static final String FIFTEEN = "Fifteen";
    private static final String THIRTY = "Thirty";
    private static final String FORTY = "Forty";
    private static final String DIVIDER = "-";
    private int m_score1;
    private int m_score2;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.m_score1 = 0;
        this.m_score2 = 0;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(PLAYER_1)) {
            this.m_score1 += 1;
        } else {
            this.m_score2 += 1;
        }
    }

    public String getScore() {
        StringBuilder score = new StringBuilder();

        if (bothPlayerSameScore(this.m_score1, this.m_score2)) {
            equalityStringBuilder(score);
        } else if (aPlayerReachFourPoint()) {
            advantageStringBuilder(score);
        } else {
            for (int i = 1; i < 3; i++) {
                standardScoreStringBuilder(score, getTempScore(score, i));
            }
        }
        return score.toString();
    }

    private boolean aPlayerReachFourPoint() {
        return this.m_score1 >= 4 || this.m_score2 >= 4;
    }

    private boolean bothPlayerSameScore(int m_score1, int m_score2) {
        return m_score1 == m_score2;
    }

    private int getTempScore(StringBuilder score, int i) {
        int tempScore;
        if (bothPlayerSameScore(i, 1)) {
            tempScore = this.m_score1;
        } else {
            score.append(DIVIDER);
            tempScore = this.m_score2;
        }
        return tempScore;
    }

    private void equalityStringBuilder(StringBuilder score) {
        switch (this.m_score1) {
            case 0:
                score.append(LOVE_ALL);
                break;
            case 1:
                score.append(FIFTEEN_ALL);
                break;
            case 2:
                score.append(THIRTY_ALL);
                break;
            default:
                score.append(DEUCE);
                break;
        }
    }

    private void standardScoreStringBuilder(StringBuilder score, int tempScore) {
        switch (tempScore) {
            case 0:
                score.append(LOVE);
                break;
            case 1:
                score.append(FIFTEEN);
                break;
            case 2:
                score.append(THIRTY);
                break;
            case 3:
                score.append(FORTY);
                break;
        }
    }

    private void advantageStringBuilder(StringBuilder score) {
        int minusResult = this.m_score1 - this.m_score2;

        if (bothPlayerSameScore(minusResult, 1)) {
            score.append(ADVANTAGE_PLAYER1);
        } else if (bothPlayerSameScore(minusResult, -1)) {
            score.append(ADVANTAGE_PLAYER2);
        } else if (minusResult >= 2) {
            score.append(WIN_FOR_PLAYER1);
        } else {
            score.append(WIN_FOR_PLAYER2);
        }
    }
}