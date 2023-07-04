# Asteroid Radar Android App

This Android application is a project developed as part of the EGFWD Scholarship Program in collaboration with Udacity. The app fetches data from the NASA API to display information about asteroids. It utilizes various technologies and libraries such as RecyclerView with ListAdapter, coroutines for handling fetching and storing data, Room database for caching data, and WorkManager for background tasks.

## Features

- Fetches asteroid data from the NASA API
- Displays the fetched data in a RecyclerView using ListAdapter for efficient updates
- Implements coroutines to handle asynchronous tasks such as fetching and storing data
- Utilizes Room database for caching the retrieved asteroid data
- Works in the background using WorkManager for periodic data updates


## Installation

To install and run the Asteroid Radar Android app, follow these steps:

1. Clone the repository: `git clone https://github.com/Migz19/Asteroid_Radar_EGfwd.git`
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

Note: Make sure you have the Android development environment set up and configured properly.

## Configuration

Before running the app, you need to obtain an API key from NASA's API website. Follow these steps to configure the API key:

1. Visit the [NASA API website](https://api.nasa.gov/).
2. Sign up or log in to your account.
3. Create a new API key.
4. Open the project in Android Studio.
5. Replace the value of `BuildConfig.API_KEY` constant with your generated API key.

## Libraries Used

The following libraries and technologies were used in the development of this app:

- Retrofit: For making HTTP API requests and handling responses.
- Moshi: For JSON parsing and serialization.
- Glide: For image loading and caching.
- RecyclerView: For displaying the list of asteroids.
- Room: For local data caching and persistence.
- Coroutines: For handling asynchronous tasks.
- WorkManager: For background task scheduling.

## Contribution

Contributions to the Asteroid Radar Android app are welcome. If you find any bugs or want to suggest improvements, please feel free to submit a pull request or open an issue in the repository.

Before contributing, make sure to familiarize yourself with the project structure and coding guidelines mentioned in the CONTRIBUTING.md file.

## License

The Asteroid Radar Android app is licensed under the [MIT License](LICENSE). You are free to use, modify, and distribute the code as per the terms of the license.

## Acknowledgements

- EGFWD Scholarship Program
- Udacity
- NASA's API

## Contact

For any further information or inquiries, please contact:

Ahmed Magdy\
Email: Migz2304@gmail.com
