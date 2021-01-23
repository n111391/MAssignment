Feature: Scenario1

@positivetests
 Scenario Outline: Fetch user ids with valid user names
    Given I should get the id of the user "<name>"
	
Examples:
|name|
|Bret|
|Antonette|
|Samantha|
|Karianne|
|Kamren|
|Leopoldo_Corkery|	
|Elwyn.Skiles|
|Maxime_Nienow|
|Delphine|
|Moriah.Stanton|

@positivetests
 Scenario Outline: Fetch the posts written by the users(for all users)
    Given I should get the id of the user "<name>"
    Then I should get the posts written by the user
	
Examples:
|name|
|Bret|
|Antonette|
|Samantha|
|Karianne|
|Kamren|
|Leopoldo_Corkery|	
|Elwyn.Skiles|
|Maxime_Nienow|
|Delphine|
|Moriah.Stanton|

@positivetests
 Scenario Outline: Validate email format in the comments section(for all users)
    Given I should get the id of the user "<name>"
    Then I should get the posts written by the user
    And I should see the emails in proper format
	
Examples:
|name|
|Bret|
|Antonette|
|Samantha|
|Karianne|
|Kamren|
|Leopoldo_Corkery|	
|Elwyn.Skiles|
|Maxime_Nienow|
|Delphine|
|Moriah.Stanton|

@negativetests
 Scenario Outline: Fetch user ids with invalid user names
    Given I should get null body when user "<name>" doesn't exist
	
Examples:
|name  |
|Breet |
|      |
|^&&   |
|123456|

@negativetests
 Scenario Outline: Fetch the posts written by the invalid Users
    Given I should get the id of the user "<name>"
    Then I should not get the posts for invalid userid "<userid>"
	
Examples:
|name          |userid|
|Maxime_Nienow |00|
|Delphine      |abc|
|Moriah.Stanton|^&&|

@negativetests
 Scenario Outline: Validate comments with invalid postid 
    Given I should get the id of the user "<name>"
    Then I should get the posts written by the user
    And I should not get the comments for invalid postid "<postid>"
	
Examples:
|name          |postid|
|Maxime_Nienow |00|
|Delphine      |abc|
|Moriah.Stanton|^&&|