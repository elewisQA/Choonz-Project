/* Insert Some Artists */
INSERT INTO ARTIST(ID, PICTURE, NAME) VALUES (1, 'https://cdn.britannica.com/64/23164-050-A7D2E9D9/Pink-Floyd.jpg', 'Pink Floyd');
/* Insert some Genres */
INSERT INTO GENRE(ID, PICTURE, DESCRIPTION, NAME) VALUES (1, 'https://www.udiscovermusic.com/wp-content/uploads/2017/04/1000x600oktolikediscoresize-web-optimised.jpg', 'Time to Dance!', 'Disco');
/* Insert Some Albums */
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (1, 'https://miro.medium.com/focal/1200/1200/50/40/1*8FkvzbSdSJ4HNxtuZo5kLg.jpeg', 'Dark Side of the Moon', 1, 2);
/* Add Songs to aforementioned albums */
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (1, 1.05, 'Speak To Me', 1);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (2, 2.50, 'Breathe', 1);
/* Insert a user */
INSERT INTO USER(ID, USERNAME, PASSWORD) VALUES (1, 'test', 'password');
/* Insert some playlists */
INSERT INTO PLAYLIST VALUES (1, 'https://i.pinimg.com/564x/38/41/48/384148d27fb01b2ff9455e05aa7b3557.jpg', 'Number one playlist!', 'Playlist One',1);
/* Add some songs to a playlist */ 
INSERT INTO track_playlists VALUES(1, 1);
