package com.example.musiclibrary.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDao {

    public int createArtist(String name, String genre) throws SQLException {
        String sql = "INSERT INTO artists(name, genre) VALUES (?, ?) RETURNING id";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, genre);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt("id");
            }
        }
    }

    public List<String> getAllArtists() throws SQLException {
        String sql = "SELECT id, name, genre FROM artists ORDER BY id";
        List<String> result = new ArrayList<>();

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String genre = rs.getString("genre");
                result.add("Artist{id=" + id + ", name='" + name + "', genre='" + genre + "'}");
            }
        }
        return result;
    }

    public boolean updateArtistGenre(int artistId, String newGenre) throws SQLException {
        String sql = "UPDATE artists SET genre = ? WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newGenre);
            ps.setInt(2, artistId);

            return ps.executeUpdate() == 1;
        }
    }

    public boolean deleteArtist(int artistId) throws SQLException {
        String sql = "DELETE FROM artists WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            return ps.executeUpdate() == 1;
        }
    }
}
