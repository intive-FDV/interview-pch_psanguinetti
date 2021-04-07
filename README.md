## GOAL
For each person in the list, display their personal information and calculate the required number of years
and months to save $1,000,000 USD if they keep 20% of their salary by working 5 days a week for the
entire year.

GENERAL REQUIREMENTS

• Display people in a scrollable list.
• Sort list based on the time required to reach the desired amount of savings (DESC).
• Each item of the list must show:
- A circular thumbnail of the image (crop the image to fit the circle shape and show the
person's face as best as possible).
- First name initial followed by the last name (Format example: S. Jobs).
- The yearly wage (Format example: $1,234,567 USD).
- The amount of time required to save the desired amount of money. (Format example: 2
years, 9 months).
- An edit button:
• When pressed, row should enter “edit” mode.
• The user should be able to update only the hourly wage.
o Updated hourly wage should be persisted locally.
o Even if the app is restarted this value should be read from the disk and
override the one incoming from the server.
− In order to achieve this, each element in the json has a unique ID.
• When the user is done editing, the list must be sorted again.

## Architecture

![Test Image 4](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

- MVVM
- ViewBinding
- Paging library 3 (Beta)
- Retrofit
- Coroutines
- Hilt  (Beta)
- Room
- Extension Functions