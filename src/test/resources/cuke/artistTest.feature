Feature: testArtists
As a user
I want to browse artists on the website
So i can add to playlist

  Scenario: Browse artists on website to add song to playlist
    Given The correct web address
    When I navigate to the 'Artist' page
    And I can click on an artist
    Then I can select an album from the artist
    And I can add a song to my desired playlist
    
   Scenario: Check Song has been added to playlist
    Given The correct web address
    When I navigate to the 'Playlist' page
    And I can click on my desired playlist
    Then I can check the previously added song exists in the playlist
