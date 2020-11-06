# Choonz-Starter

'Choonz' is a Music website, designed and tested as part of the final project for SDET.

## Concept

The 'Choonz' website will allow a user to browse music, being able to look at tracks, artists, albums, genres - as well as being able to view and make playlists.

This should be scaleable from 3 to 5+ entities:

- **MUST HAVE** - Track, Artist, Album
- **SHOULD HAVE** - Genre, Playlist
- **COULD HAVE** - Login System (restrict playlist creation to logged in users)

## Specifications
### Technical Requirements
- Maven
- Java 14
- Some IDE either Eclipse or Intellij
- Springboot for your IDE
- [Chromedriver] (https://chromedriver.chromium.org/)

**Required Libraries:** (included in *pom.xml*)
- Springboot Framework (v 2.3.4)
- Model Mapper (v 2.3.5)
- Selenium (v 3.141.59)
- Cucumber (v 1.2.4)
- JUnit 5 (v 5.6.2)
- Springfox (3.0.0)

### Client Requirements / Feature-Set
**General**
- Multiple users can sign up to the system
- Many users can be signed-in similtaneously
- Users can browse without logging in but won't be able to make any changes.
- Users can search for albums, artists, songs, etc.  
  
**Albums**
- Users can browse all Albums
- Users can see and navigate to the artist, tracks, genre an album has or belongs to.

**Artists**
- Users can view details about an artist
- Users can see all albums released by an artist

**Tracks**
- Users can browse all tracks
- Tracks can be added to the playlist of a logged-in user
- Track information such as play-time and lyrics can be viewed.

**Genres**
- Users can browse albums that fit certain genres.

## ERD

![Entity Relationship Diagram](documentation/ERD.png)

## UML

TBD

## Setting-Up Development
1. Ensure you have the required tools (see Technical requirements)
2. Clone this repository or download it as a *.zip* file and un-pack it. Open the project in Eclipse or Intellij, setting it up as a Maven project.
3. Run the 'ChoonzApplication.java' file in `src/com/qa/choonz` - this is the main runner for the Springboot application.
4. The application should be hosted as 'localhost:8082/'
  
**Note:** To run Selenium tests, you will need to download ['Chromedriver.exe'](https://chromedriver.chromium.org/downloads) (*or a web-driver for your preferred browser*) and place it under `src/test/resources/drivers/`  

## Authors

### Training Team

- **Client** - [**Angelica Charry**](https://github.com/acharry) - **Software Delivery Manager**
- **Product Owner** - [**Nick Johnson**](https://github.com/nickrstewarttds) - **Initial work (backend & frontend development, specification)**
- **Product Owner** - [**Edward Reynolds**](https://github.com/Edrz-96) - **Initial work (testing, specification)**
- [**Jordan Harrison**](https://github.com/JHarry444) - **General Java wizardry**
- [**Alan Davies**](https://github.com/MorickClive)
- [**Savannah Vaithilingham**](https://github.com/savannahvaith)
- [**Vinesh Ghela**](https://github.com/vineshghela)
- [**Piers Barber**](https://github.com/PCMBarber)

### Development Team

- [**Cristina Lopez Canoyra**](https://github.com/clc15735)
- [**Nikolett Bajna**](https://github.com/nikolettbajna)
- [**Liam Harrold**](https://github.com/LHarroldQA)
- [**Avi Hart**](https://github.com/AviNissimHart)
- [**Elizabeth Lewis**](https://github.com/elewisQA)

## Acknowledgements
- [**Baeldung**](https://www.baeldung.com/java-random-string) - for Random-String generation code
- [**Chromedriver**](https://chromedriver.chromium.org/)

