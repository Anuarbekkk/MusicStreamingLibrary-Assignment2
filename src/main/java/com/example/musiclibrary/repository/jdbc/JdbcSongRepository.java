package com.example.musiclibrary.repository.jdbc;

import com.example.musiclibrary.Artist;
import com.example.musiclibrary.Song;
import com.example.musiclibrary.repository.SongRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcSongRepository implements SongRepository {

    private final DataSource dataSource;

    public JdbcSongRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Integer create(Song song) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO songs(title, duration_sec, artist_id) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getDurationSec());
            ps.setInt(3, song.getArtist().getId());

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Song findById(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                SELECT s.id, s.title, s.duration_sec,
                       a.id AS artist_id, a.name, a.genre
                FROM songs s
                JOIN artists a ON s.artist_id = a.id
                WHERE s.id = ?
            """;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return null;

            Artist artist = new Artist(
                    rs.getInt("artist_id"),
                    rs.getString("name"),
                    rs.getString("genre")
            );

            return new Song(
                    rs.getInt("id"),
                    rs.getString("title"),
                    artist,
                    rs.getInt("duration_sec")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Song> findAll() {
        return findAllWithArtist();
    }

    @Override
    public List<Song> findAllWithArtist() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                SELECT s.id, s.title, s.duration_sec,
                       a.id AS artist_id, a.name, a.genre
                FROM songs s
                JOIN artists a ON s.artist_id = a.id
                ORDER BY s.id
            """;

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Song> songs = new ArrayList<>();

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getInt("artist_id"),
                        rs.getString("name"),
                        rs.getString("genre")
                );

                songs.add(new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        artist,
                        rs.getInt("duration_sec")
                ));
            }

            return songs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Integer id, Song song) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE songs SET title = ?, duration_sec = ?, artist_id = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getDurationSec());
            ps.setInt(3, song.getArtist().getId());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM songs WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
