/* Insert Some Artists */
INSERT INTO ARTIST(ID, PICTURE, NAME) VALUES (1, 'https://cdn.britannica.com/64/23164-050-A7D2E9D9/Pink-Floyd.jpg', 'Pink Floyd');
INSERT INTO ARTIST(ID, PICTURE, NAME) VALUES (2, 'https://i.pinimg.com/736x/f9/8b/c0/f98bc0f9c2ed2ec01816665ccc0a9d1a.jpg', 'ABBA');
INSERT INTO ARTIST(ID, PICTURE, NAME) VALUES (3,'https://i.pinimg.com/originals/81/bd/9f/81bd9f9ecfbdcba163154073ab44eaaa.jpg' ,'Gorillaz');
INSERT INTO ARTIST(ID, PICTURE, NAME) VALUES (4,'https://pyxis.nymag.com/v1/imgs/7d4/7d3/09dd244022bc7205ec55fd6b09e53c40b1-the-weeknd.rsquare.w1200.jpg' ,'The Weeknd');
/* Insert some Genres */
INSERT INTO GENRE(ID, PICTURE, DESCRIPTION, NAME) VALUES (1, 'https://www.udiscovermusic.com/wp-content/uploads/2017/04/1000x600oktolikediscoresize-web-optimised.jpg', 'Time to Dance!', 'Disco');
INSERT INTO GENRE(ID, PICTURE, DESCRIPTION, NAME) VALUES (2, 'https://m.media-amazon.com/images/I/71i0QeO0IOL._SS500_.jpg', 'Rock inspired by psychedelic culture', 'Psychedelic Rock');
INSERT INTO GENRE(ID, PICTURE, DESCRIPTION, NAME) VALUES (3, 'https://cdn.dribbble.com/users/1931394/screenshots/9780708/image.png', 'Rhythem and Blues - previously soul & funk', 'R&B');
/* Insert Some Albums */
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (1, 'https://miro.medium.com/focal/1200/1200/50/40/1*8FkvzbSdSJ4HNxtuZo5kLg.jpeg', 'Dark Side of the Moon', 1, 2);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (2, 'https://images-na.ssl-images-amazon.com/images/I/717QoMwin7L._AC_SY355_.jpg', 'Mamma Mia! Official Soundtrack', 2, 1);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (3, 'https://ifmyalbumscouldtalkdotme.files.wordpress.com/2016/11/pink-floyd-the-wall.jpg', 'The Wall', 1, 2);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (4, 'https://upload.wikimedia.org/wikipedia/en/thumb/e/ed/The_Weeknd_-_Kiss_Land.png/220px-The_Weeknd_-_Kiss_Land.png', 'Kiss Land', 4, 3);
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
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (13, 6.09, 'Professional', 4);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (14, 5.07, 'The Town', 4);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (15, 4.43, 'Adaptation', 4);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (16, 4.27, 'Love In The Sky', 4);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (17, 5.07, 'Belong To The World', 4);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (18, 3.44, 'Live For (feat. Drake)', 4);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (19, 5.06, 'Wanderlust', 4);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (20, 7.35, 'Kiss Land', 4);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (21, 6.15, 'Pretty', 4);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (22, 7.24, 'Tears In The Rain', 4);
/* Insert some playlists */
INSERT INTO PLAYLIST VALUES (1, 'https://i.pinimg.com/564x/38/41/48/384148d27fb01b2ff9455e05aa7b3557.jpg', 'Number one playlist!', 'Playlist One');
INSERT INTO PLAYLIST VALUES (2, 'https://i.pinimg.com/564x/d0/5b/98/d05b98026f6628b5f23c33c65a3d36b5.jpg', 'Second best playlist!', 'Playlist 2');
INSERT INTO PLAYLIST VALUES (3, 'https://images.ctfassets.net/cnu0m8re1exe/kz2EOc6Ql3Ls4DHgaZuV5/49800604018f6bfa14abde25e4f2cb06/broken-heart.jpg?w=650&h=433&fit=fill', 'When you feel down', 'HeartBreak');
/* Add some songs to a playlist */ 
INSERT INTO track_playlists VALUES(1, 1);
INSERT INTO track_playlists VALUES(11, 1);
INSERT INTO track_playlists VALUES(15, 3);
INSERT INTO track_playlists VALUES(13, 3);
INSERT INTO track_playlists VALUES(22, 3);