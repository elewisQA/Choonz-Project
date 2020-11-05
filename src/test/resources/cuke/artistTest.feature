Feature: testArtists
As a user
I want to browse artists on the website
So i can add to playlist
I want to use the search bar to search for playlists, songs
artists, genres and albums

  Scenario: Can browse the website and test out functionality
    Given The correct web address
    When I navigate to the 'Artist' page
    And I can click on an artist
    Then I can select an album from the artist
    And I can add a song to my desired playlist
    Then I navigate to the 'Playlist' page
    And I can click on my desired playlist
    Then I can check the previously added song exists in the playlist
    And I can delete the song from the playlist
    Then I navigate to the search bar
    And I can search for an artist
    Then I can search for an album
    And I can search for an song
    Then I can search for an playlist
    And I can search for an genre