/* Insert Some Artists */
INSERT INTO ARTIST VALUES (1, 'Pink Floyd');
INSERT INTO ARTIST VALUES (2, 'ABBA');
/* Insert some Genres */
INSERT INTO GENRE VALUES (1, 'Time to Dance!', 'Disco');
INSERT INTO GENRE VALUES (2, 'Rock inspired by psychedelic culture', 'Psychedelic Rock');
/* Insert Some Albums */
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (1, 'https://miro.medium.com/focal/1200/1200/50/40/1*8FkvzbSdSJ4HNxtuZo5kLg.jpeg', 'Dark Side of the Moon', 1, 2);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (2, 'https://images-na.ssl-images-amazon.com/images/I/717QoMwin7L._AC_SY355_.jpg', 'Mamma Mia! Official Soundtrack', 2, 1);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (3, 'https://ifmyalbumscouldtalkdotme.files.wordpress.com/2016/11/pink-floyd-the-wall.jpg', 'The Wall', 1, 2);
/* Add Songs to aforementioned albums */
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (1, 1.05, 'Speak To Me', 1);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (2, 2.50, 'Breathe', 1);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (3, 3.45, 'On The Run', 1);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (4, 6.54, 'Time', 1);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (5, 4.44, 'The Great Gig in the Sky', 1);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (6, 6.23, 'Money', 1);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (7, 7.49, 'Us and them', 1);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (8, 3.26, 'Any Colour You Like', 1);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (9, 3.47, 'Brain Damage', 1);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (10, 2.10, 'Eclipse', 1);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (11, 3.07, 'Honey, Honey', 2);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (12, 3.35, 'Mamma Mia', 2);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (13, 3.18, 'In the flesh?', 2);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (14, 2.26, 'The Thin Ice', 2);
/* Add Songs to playlist */
INSERT INTO PLAYLIST VALUES (1, 'a', 'Number one playlist!', 'Playlist One');
INSERT INTO PLAYLIST VALUES (2, 'b', 'Second best playlist!', 'Playlist 2');