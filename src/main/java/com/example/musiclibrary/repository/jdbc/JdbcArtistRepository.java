package com.example.musiclibrary.repository.jdbc;

import com.example.musiclibrary.Artist;
import com.example.musiclibrary.repository.ArtistRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcArtistRepository implements ArtistRepository {

    private final DataSource dataSource;

    public JdbcArtistRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Integer create(Artist artist) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO artists(name, genre) VALUES (?, ?) RETURNING id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, artist.getName());
            ps.setString(2, artist.getGenre());

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Artist findById(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, genre FROM artists WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return null;

            return new Artist(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("genre")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Artist> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, genre FROM artists ORDER BY id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Artist> artists = new ArrayList<>();

            while (rs.next()) {
                artists.add(new Artist(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("genre")
                ));
            }

            return artists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Integer id, Artist artist) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE artists SET name = ?, genre = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, artist.getName());
            ps.setString(2, artist.getGenre());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM artists WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
