/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tapio
 */
public class StatisticsTest {
    
        Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
        
    private Statistics stats;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchAndFindPlayer() {
        assertEquals(new Player("Semenko", "EDM", 4, 12), stats.search("Semenko"));
    }
    
    @Test
    public void searchPlayerNotFound() {
        assertNull(stats.search("Tapio"));
    }
    
    @Test
    public void returnTeam() {
        Player[] EDMTeam = { new Player("Semenko", "EDM", 4, 12),
            new Player("Kurri",   "EDM", 37, 53),
            new Player("Gretzky", "EDM", 35, 89)
        };
        List<Player> team = stats.team("EDM");    
        assertArrayEquals(EDMTeam, team.toArray());
    }
    
    @Test
    public void getTopScorer() {
        Player gretzky = new Player("Gretzky", "EDM", 35, 89);
        assertEquals(gretzky, stats.topScorers(1).get(0));
    }
       
    
}
