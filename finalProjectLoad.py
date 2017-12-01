import json
import sqlite3

#database
db = sqlite3.connect('movies.sqlite')
cursor = db.cursor()

cursor.executescript('''
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS director;
DROP TABLE IF EXISTS writer;
DROP TABLE IF EXISTS language;
DROP TABLE IF EXISTS actor;
DROP TABLE IF EXISTS rating;
DROP TABLE IF EXISTS country;

CREATE TABLE movie(
    imdbId      TEXT NOT NULL PRIMARY KEY UNIQUE,
    title       TEXT UNIQUE,
    year        INTEGER,
    mpaa_rating TEXT,
    released    DATE,
    runtime     INTEGER,
    plot        TEXT,
    poster      TEXT,
    metascore   INTEGER,
    imdbrating  REAL,
    imdbvotes   INT,
    mtype       TEXT,
    dvd         DATE,
    boxoffice   INTEGER,
    production  TEXT,
    website     TEXT
);

CREATE TABLE genre(
    genreId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    genre   TEXT,
    imdbId  TEXT,
    FOREIGN KEY(imdbId) REFERENCES movie(imdbId)
);

CREATE TABLE director(
    directorId      INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    directorName    TEXT,
    imdbId          TEXT,
    FOREIGN KEY(imdbId) REFERENCES movie(imdbId)
);

CREATE TABLE writer(
    writerId    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    writerName  TEXT,
    imdbId      TEXT,
    FOREIGN KEY(imdbId) REFERENCES movie(imdbId)
);

CREATE TABLE language(
    languageId  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    language    TEXT,
    imdbId      TEXT,
    FOREIGN KEY(imdbId) REFERENCES movie(imdbId)
);

CREATE TABLE actor(
    actorId     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    actorName   TEXT,
    imdbId      TEXT,
    FOREIGN KEY(imdbId) REFERENCES movie(imdbId)
);

CREATE TABLE rating(
    ratingId    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    source      TEXT,
    value       INTEGER,
    imdbId      TEXT,
    FOREIGN KEY(imdbId) REFERENCES movie(imdbId)
);

CREATE TABLE country(
    countryId   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    imdbId      TEXT,
    country     TEXT,
    FOREIGN KEY(imdbId) REFERENCES movie(imdbId)
)
''')

# JSON
fname = 'movies_data.json'
str_data = open(fname).read()
json_data = json.loads(str_data)

for entry in json_data:
    title = entry[0]
    year = entry[1]
    mpaa_rating = entry[2]
    released = entry[3]
    runtime = entry[4]
    genres = entry[5]
    directors = entry[6]
    writers = entry[7]
    actors = entry[8]
    plot = entry[9]
    languages = entry[10]
    countries = entry[11]
    awards = entry[12]
    poster = entry[13]
    ratings = entry[14]
    metascore = entry[15]
    imdbrating = entry[16]
    imdbvotes = entry[17]
    imdbId = entry[18]
    # imdbid candidate key
    mtype = entry[19]
    dvd = entry[20]
    boxoffice = entry[21]
    production = entry[22]
    website = entry[23]
    response = entry[24]

    print("importing..." + title)

    # for i in range(24):
        # print(entry[i])
    
    
    cursor.execute('''INSERT OR IGNORE INTO movie(
        imdbId,
        title,
        year,
        mpaa_rating,
        released,
        runtime,
        plot,
        poster,
        metascore,
        imdbrating,
        imdbvotes,
        mtype,
        dvd,
        boxoffice,
        production,
        website)
        VALUES
        (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
        ''', (imdbId, title, year, mpaa_rating, released,
              runtime, plot, poster, metascore,
              imdbrating, imdbvotes, mtype, dvd,
              boxoffice, production, website) )

    writerArray = writers.split(', ')
    for name in writerArray:
        cursor.execute('''INSERT OR IGNORE INTO writer(
            imdbId,
            writerName)
            VALUES
            (?,?)
        ''', (imdbId, name) )

    genreArray = genres.split(', ')
    for genre in genreArray:
        cursor.execute('''INSERT OR IGNORE INTO genre(
            imdbId,
            genre)
            VALUES
            (?,?)
        ''', (imdbId, genre) )

    directorArray = directors.split(', ')
    for director in directorArray:
        cursor.execute('''INSERT OR IGNORE INTO director(
            imdbId,
            directorName)
            VALUES
            (?,?)
        ''', (imdbId, director) )

    languageArray = languages.split(', ')
    for language in languageArray:
        cursor.execute('''INSERT OR IGNORE INTO language(
            imdbId,
            language)
            VALUES
            (?,?)
        ''', (imdbId, language) )

    actorArray = actors.split(', ')
    for actor in actorArray:
        cursor.execute('''INSERT OR IGNORE INTO actor(
            imdbId,
            actorName)
            VALUES
            (?,?)
        ''', (imdbId, actor) )

    
    for rating in ratings:
        cursor.execute('''INSERT OR IGNORE INTO rating(
            imdbId,
            source,
            value)
            VALUES
            (?,?,?)
        ''', (imdbId, rating[0], rating[1] ) )

    countryArray = countries.split(', ')
    for country in countryArray:
        cursor.execute('''INSERT OR IGNORE INTO country(
            imdbId,
            country)
            VALUES
            (?,?)
        ''', (imdbId, country) )
    
    db.commit()

db.close()
