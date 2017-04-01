package ohtuesimerkki;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerReader implements Reader {

    private Scanner scanner;
    private int playerInfoParts = 3;
    private int firstPartIndex = 0;
    private int seocndPartIndex = 1;
    private int thirdPartIndex = 2;
    private int fourthPartIndex = 3;
    private int fifthPartIndex = 4;

    
    public PlayerReader(String pageUrl) {
        try {
            URL url = new URL(pageUrl);
            scanner = new Scanner(url.openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();

        while (scanner.hasNextLine()) {
            String[] parts =  scanner.nextLine().split(";");            
            
            if (parts.length > playerInfoParts) {
                players.add(new Player(parts[firstPartIndex].trim(), parts[seocndPartIndex], 
                        extractInt(parts[fourthPartIndex]), extractInt(parts[fifthPartIndex])));
            }
        }

        return players;
    }

    private int extractInt(String str) {
        return Integer.parseInt(str.trim());
    }
}
