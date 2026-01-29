What was done:
Created PostgreSQL DB music_streaming
Created tables: artists, songs
Implemented JDBC connection (PostgreSQL driver via Maven)
Implemented CRUD operations using DAO classes: ArtistDao, SongDao
Demonstrated CRUD in Main.java (insert/select/update/delete)

How to run:
Create DB music_streaming in PostgreSQL
Run SQL script to create tables (see below)
Set DB credentials in Db.java (URL/USER/PASSWORD)
Run Main.java
SQL (tables):
CREATE TABLE IF NOT EXISTS artists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS songs (
    id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    duration_sec INT NOT NULL CHECK (duration_sec > 0),
    artist_id INT NOT NULL REFERENCES artists(id) ON DELETE CASCADE
);
