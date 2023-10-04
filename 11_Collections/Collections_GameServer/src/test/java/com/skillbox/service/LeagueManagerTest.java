package com.skillbox.service;

import com.skillbox.model.League;
import com.skillbox.model.LeagueComparator;
import com.skillbox.model.Player;
import com.skillbox.model.Race;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LeagueManagerTest {
    private LeagueManager leagueManager;

    @Before
    public void setUp() throws Exception {
        Class<?> stackInterface = LeagueManager.class;
        Reflections reflections = new Reflections("com.skillbox.service", new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class)
                .stream()
                .collect(Collectors.toSet());
        for (Class<?> clazz : classes) {
            HashSet<Class<?>> interfaces = new HashSet<>(Arrays.asList(clazz.getInterfaces()));
            for (Class<?> i : interfaces) {
                if (i.equals(stackInterface)) {
                    Object o = clazz.newInstance();
                    leagueManager = (LeagueManager) o;
                }
            }
        }
    }

    @Test
    public void testAddPlayer() {
        Player player = generatePlayer();
        leagueManager.addPlayer(generatePlayer());
        assertNotNull(leagueManager.getPlayer(player.getNickName()));
    }

    @Test
    public void testRemovePlayer() {
        Player player = generatePlayer();
        leagueManager.addPlayer(player);
        assertNotNull(leagueManager.getPlayer(player.getNickName()));
        leagueManager.removePlayer(player);
        assertNull(leagueManager.getPlayer(player.getNickName()));
    }

    @Test
    public void testGetPlayer() {
        Player player = generatePlayer();
        leagueManager.addPlayer(player);
        Player actual = leagueManager.getPlayer(player.getNickName());
        assertEquals(player, actual);
    }

    @Test
    public void testGetAllPlayers() {
        Player player1 = new Player("John", 34);
        Player player2 = new Player("Jane", 39);
        leagueManager.addPlayer(player1);
        leagueManager.addPlayer(player2);
        Player[] allPlayers = leagueManager.getAllPlayers();
        assertEquals(2, allPlayers.length);
        assertTrue(containsPlayer(allPlayers, player1));
        assertTrue(containsPlayer(allPlayers, player2));
    }

    @Test
    public void testGetPlayersByRace() {
        Player player1 = new Player("John", 78, League.PLATINUM, Race.HUMAN);
        Player player2 = new Player("Jane", 32, League.PRO, Race.ELF);
        leagueManager.addPlayer(player1);
        leagueManager.addPlayer(player2);
        Player[] humanPlayers = leagueManager.getPlayers(Race.HUMAN);
        assertEquals(1, humanPlayers.length);
        assertTrue(containsPlayer(humanPlayers, player1));
    }

    @Test
    public void testGetPlayersByLeague() {
        Player player1 = new Player("John", 78, League.PLATINUM, Race.HUMAN);
        Player player2 = new Player("Jane", 32, League.PRO, Race.ELF);
        leagueManager.addPlayer(player1);
        leagueManager.addPlayer(player2);
        Player[] proPlayers = leagueManager.getPlayers(League.PRO);
        assertEquals(1, proPlayers.length);
        assertTrue(containsPlayer(proPlayers, player2));
    }

    @Test
    public void testAddPoints() {
        Player player = generatePlayer();
        int expected = player.getPoints();
        leagueManager.addPlayer(player);
        int points = 10;
        expected += points;
        leagueManager.addPoints(player.getNickName(), points);
        Player actual = leagueManager.getPlayer(player.getNickName());
        assertEquals(expected, actual.getPoints());
    }

    @Test
    public void testSortingByLeague() {
        LeagueComparator leagueComparator = new LeagueComparator();
        Set<Player> players = new TreeSet<>(leagueComparator);
        for (League league : League.values()) {
            leagueManager.addPlayer(new Player("name" + league.ordinal(), 11, league, Race.HUMAN));
        }
        players.addAll(List.of(leagueManager.getAllPlayers()));
        System.out.println(players);
        assertEquals(League.PRO, players.iterator().next().getLeague());
    }

    @Test
    public void testSortingByPoints() {
        for (int i = 10; i > 0; i--) {
            leagueManager.addPlayer(new Player("name" + i, i));
        }
        Set<Player> players = new TreeSet<>(List.of(leagueManager.getAllPlayers()));
        System.out.println(players);
        assertEquals(1, players.iterator().next().getPoints());
    }


    Player generatePlayer() {
        return new Player("name", 1, League.BRONZE, Race.ELF);
    }

    private boolean containsPlayer(Player[] players, Player player) {
        return Arrays.asList(players).contains(player);
    }
}
