/**
 * Simulation du score d'un jeu de tennis entre deux joueurs.
 */
public class TennisGame2 implements TennisGame {

    private static final String[] SCORE_NAMES = {"Love", "Fifteen", "Thirty", "Forty"};
    private static final int POINTS_TO_WIN = 4;
    private static final int DEUCE_THRESHOLD = 3;

    private int player1Points = 0;
    private int player2Points = 0;

    private final String player1Name;
    private final String player2Name;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    @Override
    public String getScore() {
        if (player1Points == player2Points) {
            return equalScore();
        }
        if (player1Points >= POINTS_TO_WIN || player2Points >= POINTS_TO_WIN) {
            return advantageOrWinScore();
        }
        return SCORE_NAMES[player1Points] + "-" + SCORE_NAMES[player2Points];
    }

    private String equalScore() {
        if (player1Points >= DEUCE_THRESHOLD) {
            return "Deuce";
        }
        return SCORE_NAMES[player1Points] + "-All";
    }

    private String advantageOrWinScore() {
        int difference = player1Points - player2Points;
        String leader = difference > 0 ? player1Name : player2Name;
        if (Math.abs(difference) == 1) {
            return "Advantage " + leader;
        }
        return "Win for " + leader;
    }

    @Override
    public void wonPoint(String playerName) {
        if (player1Name.equals(playerName)) {
            player1Points++;
        } else if (player2Name.equals(playerName)) {
            player2Points++;
        } else {
            throw new IllegalArgumentException("Joueur inconnu : " + playerName);
        }
    }
}