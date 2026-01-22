package com.example.musiclibrary.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDao {

    public int createSong(String title, int durationSec, int artistId) throws SQLException {
        String sql = "INSERT INTO songs(title, duration_sec, artist_id) VALUES (?, ?, ?) RETURNING id";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setInt(2, durationSec);
            ps.setInt(3, artistId);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt("id");
            }
        }
    }

    public List<String> getAllSongs() throws SQLException {
        String sql = """
                SELECT s.id, s.title, s.duration_sec, a.name AS artist_name
                FROM songs s
                JOIN artists a ON a.id = s.artist_id
                ORDER BY s.id
                """;

        List<String> result = new ArrayList<>();

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int duration = rs.getInt("duration_sec");
                String artistName = rs.getString("artist_name");

                result.add("Song{id=" + id + ", title='" + title + "', durationSec=" + duration + ", artist='" + artistName + "'}");
            }
        }
        return result;
    }

    public boolean updateSongDuration(int songId, int newDurationSec) throws SQLException {
        String sql = "UPDATE songs SET duration_sec = ? WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newDurationSec);
            ps.setInt(2, songId);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean deleteSong(int songId) throws SQLException {
        String sql = "DELETE FROM songs WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, songId);
            return ps.executeUpdate() == 1;
        }
    }
}
