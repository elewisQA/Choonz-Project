/* Insert Some Artists */
INSERT INTO ARTIST(ID, PICTURE, NAME) VALUES (1, 'https://cdn.britannica.com/64/23164-050-A7D2E9D9/Pink-Floyd.jpg', 'Pink Floyd');
INSERT INTO ARTIST(ID, PICTURE, NAME) VALUES (2, 'https://i.pinimg.com/736x/f9/8b/c0/f98bc0f9c2ed2ec01816665ccc0a9d1a.jpg', 'ABBA');
INSERT INTO ARTIST(ID, PICTURE, NAME) VALUES (3, 'https://i.pinimg.com/originals/81/bd/9f/81bd9f9ecfbdcba163154073ab44eaaa.jpg', 'Gorrilaz');
INSERT INTO ARTIST(ID, PICTURE, NAME) VALUES (4,'https://pyxis.nymag.com/v1/imgs/7d4/7d3/09dd244022bc7205ec55fd6b09e53c40b1-the-weeknd.rsquare.w1200.jpg' ,'The Weeknd');
INSERT INTO ARTIST(ID, PICTURE, NAME) VALUES (5, 'https://cdn.thebeat.online/art/74/aa/1b/a1/a11baa74ee50560a67de3b445a1da8fbffd21171.jpg', 'Our Last Night');
INSERT INTO ARTIST(ID, PICTURE, NAME) VALUES (6, 'https://f4.bcbits.com/img/a3321951232_16.jpg', 'HOME');

/* Insert some Genres */
INSERT INTO GENRE(ID, PICTURE, DESCRIPTION, NAME) VALUES (1, 'https://www.udiscovermusic.com/wp-content/uploads/2017/04/1000x600oktolikediscoresize-web-optimised.jpg', 'Time to Dance!', 'Disco');
INSERT INTO GENRE(ID, PICTURE, DESCRIPTION, NAME) VALUES (2, 'https://m.media-amazon.com/images/I/71i0QeO0IOL._SS500_.jpg', 'Rock inspired by psychedelic culture', 'Psychedelic Rock');
INSERT INTO GENRE(ID, PICTURE, DESCRIPTION, NAME) VALUES (3, 'https://cdn.dribbble.com/users/1931394/screenshots/9780708/image.png', 'Rhythem and Blues - previously soul & funk', 'R&B');
INSERT INTO GENRE(ID, PICTURE, DESCRIPTION, NAME) VALUES (4, 'https://images.8tracks.com/avatar/i/006/855/696/71931.original-7338.jpg?rect=98,0,500,500&q=98&fm=jpg&fit=max', 'Post-hardcore is a punk rock music genre that maintains the aggression and intensity of hardcore punk but emphasizes a greater degree of creative expression initially inspired by post-punk and noise rock.', 'Post-Hardcore');
INSERT INTO GENRE(ID, PICTURE, DESCRIPTION, NAME) VALUES (5, 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.22Js-TEmfKfi2FpGa-bxiwHaE8%26pid%3DApi&f=1', 'A microgenre taht emerged in the late 2000s, characterized by faded or dreamy retro pop sound.', 'Chillwave');

/* Insert Some Albums */
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (1, 'https://miro.medium.com/focal/1200/1200/50/40/1*8FkvzbSdSJ4HNxtuZo5kLg.jpeg', 'Dark Side of the Moon', 1, 2);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (2, 'https://images-na.ssl-images-amazon.com/images/I/717QoMwin7L._AC_SY355_.jpg', 'Mamma Mia! Official Soundtrack', 2, 1);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (3, 'https://ifmyalbumscouldtalkdotme.files.wordpress.com/2016/11/pink-floyd-the-wall.jpg', 'The Wall', 1, 2);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (4, 'https://upload.wikimedia.org/wikipedia/en/thumb/e/ed/The_Weeknd_-_Kiss_Land.png/220px-The_Weeknd_-_Kiss_Land.png', 'Kiss Land', 4, 3);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (5, 'https://images.genius.com/97af0d73036c03720a29d2e7e8219da0.960x960x1.jpg', 'Younger Dreams', 5, 4);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (6, 'https://images.genius.com/4e0c59f8f455e0b45107938e39fe7884.640x640x1.jpg', 'Oak Island', 5, 4);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (7, 'https://f4.bcbits.com/img/a3321951232_16.jpg', 'Odyssey', 6, 5);
INSERT INTO ALBUM(ID, COVER, NAME, ARTIST_ID, GENRE_ID) VALUES (8, 'https://f4.bcbits.com/img/a4025651648_16.jpg', 'Before The Night', 6, 5);

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
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (23, 3.33, 'Imaginary Monster', 5);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (24, 3.56, 'Barricades', 5);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (25, 4.05, 'Same Old War', 6);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (26, 4.03, 'Dark Storms', 6);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (27, 3.50, 'Sunrise', 6);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (28, 3.09, 'Intro', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (29, 4.02, 'Native', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (30, 4.11, 'Decay', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (31, 3.25, 'Oort Cloud', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (32, 3.57, 'Tides', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (33, 3.05, 'Nights (I Wish I Could Be There', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (34, 6.09, 'Odyssey', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (35, 2.57, 'New Machines', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (36, 3.32, 'Resonance', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (37, 4.53, 'Come Back Down', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (38, 4.20, 'Half Moon', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (39, 4.01, 'On The Way Out', 7);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (40, 4.32, 'We''re Finally Landing', 8);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (41, 4.00, 'Synchronize', 8);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (42, 5.22, 'Overflow', 8);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (43, 3.33, 'Without a Sound', 8);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (44, 3.46, 'Above All', 8);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (45, 3.37, 'Pyxis', 8);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (46, 3.53, 'Before The Night', 8);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (47, 2.55, 'If I''m Wrong', 8);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (48, 3.54, 'Nosebleed', 8);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (49, 2.36, 'Sun', 8);
INSERT INTO TRACK(ID, DURATION, NAME, ALBUM_ID) VALUES (50, 4.34, 'Sleep', 8);

/* Insert a user */
INSERT INTO USER(ID, USERNAME, PASSWORD) VALUES (1, 'test', 'password');

/* Insert some playlists */
INSERT INTO PLAYLIST VALUES (1, 'https://i.pinimg.com/564x/38/41/48/384148d27fb01b2ff9455e05aa7b3557.jpg', 'Number one playlist!', 'Playlist One', 1);
INSERT INTO PLAYLIST VALUES (2, 'https://i.pinimg.com/564x/d0/5b/98/d05b98026f6628b5f23c33c65a3d36b5.jpg', 'Second best playlist!', 'Playlist 2', 1);
INSERT INTO PLAYLIST VALUES (3, 'https://images.ctfassets.net/cnu0m8re1exe/kz2EOc6Ql3Ls4DHgaZuV5/49800604018f6bfa14abde25e4f2cb06/broken-heart.jpg?w=650&h=433&fit=fill', 'When you feel down', 'HeartBreak', 1);
/* Add some songs to a playlist */ 
INSERT INTO track_playlists VALUES(1, 1);
INSERT INTO track_playlists VALUES(11, 1);
INSERT INTO track_playlists VALUES(15, 3);
INSERT INTO track_playlists VALUES(13, 3);
INSERT INTO track_playlists VALUES(22, 3);