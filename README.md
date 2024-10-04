StocksApp



https://github.com/user-attachments/assets/6582a0ac-fea3-4f74-b0e8-b32be2680aa2


## **stockapp** using Clean Android Architecture

Android App:
- StockApp lists out the Top 50 stocks available with the country and currency, the user can add and remove their favourite stock to their own watch list
- The app also has offline caching via a local db that allows the user to use the device offline to see their favourite stocks.


This repository contains an implementation of a clean architecture for Android applications using Compose, MVVM, Hilt, Coroutines, Kotlin Flow, Repository, Room, Retrofit, Mockk and JUnit.

The project is divided into several modules, including:

- `app`: The main application module, responsible for defining the UI using Compose and coordinating with the presentation layer.
- `commonDomain`: The domain module, responsible for defining the business logic of the application and exposing it through interfaces.
  - `interactor`: The Interactors are responsible for call repository functions and do the buisness logics and return required models to viewmodel.
- `commonData`: The data module, responsible for defining the data repository, api services and DTOs.
  - `data-repository`: The repository module, responsible for implementing the interfaces defined in the domain module and providing data from both the local and remote data sources.
  - `data-local`: The local data source module, responsible for implementing the logic to access data stored locally, using Room as the database.
  - `data-remote`: The remote data source module, responsible for implementing the logic to access data from a remote API, using Retrofit as the network client.
 
Diagram of Stock App Archetecture:

![stock app diagram](https://github.com/user-attachments/assets/f3055f05-afae-439c-87fa-1d97578b49d3)


The project follows a layered architecture approach, with each layer (presentation, domain, repository and data) having its own set of responsibilities and being completely decoupled from the other layers. The communication between the layers is done through well-defined interfaces, allowing for easy testing and future modifications.

### Dependencies
Public API: 
https://api.twelvedata.com/stocks 
[Documentation] - https://twelvedata.com/docs#getting-started

Unit test coverage 100% for VM and repository

