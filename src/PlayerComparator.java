import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PlayerComparator {

    public static class Player {
        public String name;
        public int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    class Checker implements Comparator<Player> {
        class PlayerChi extends Player {

            public PlayerChi(Player p) {
                super(p.name, p.score);
            }

            public PlayerChi(String name, int score) {
                super(name, score);
            }

            public String getName() {
                return name;
            }

            public int getScore() {
                return score;
            }

        }
        // complete this method
        public int compare(Player a, Player b) {
            Comparator<PlayerChi> playerComparator = Comparator.comparing(PlayerChi::getScore).reversed().thenComparing(PlayerChi::getName);
            return playerComparator.compare(new PlayerChi(a), new PlayerChi(b));
        }
    }

    public static void main(String[] args) throws IOException {
    }
}
