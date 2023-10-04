package com.skillbox.service;

import com.skillbox.model.League;
import com.skillbox.model.Player;
import com.skillbox.model.Race;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeagueManagerImpl implements LeagueManager {
    private final Map<String, Player> players;

    public LeagueManagerImpl() {
        players = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized void addPlayer(Player player) {
        players.put(player.getNickName(), player);
    }

    @Override
    public synchronized void removePlayer(Player player) {
        players.remove(player.getNickName());
    }

    @Override
    public Player getPlayer(String nickName) {
        return players.get(nickName);
    }

    @Override
    public synchronized Player[] getAllPlayers() {
        return players.values().toArray(Player[]::new);
    }

    @Override
    public synchronized Player[] getPlayers(League league) {
        return players.values().stream().filter(player -> player.getLeague() == league).toArray(Player[]::new);
    }

    @Override
    public synchronized Player[] getPlayers(Race race) {
        return players.values().stream().filter(player -> player.getRace() == race).toArray(Player[]::new);
    }

    @Override
    public synchronized void addPoints(String nickName, int points) {
        Player player = players.get(nickName);
        if (player != null) {
            player.setPoints(player.getPoints() + points);
        }
    }
}
