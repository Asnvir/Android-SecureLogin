<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
  <h1>SecureLogin</h1>
  <p>SecureLogin is an Android application that provides secure login by checking various conditions such as GPS location, battery level, and time. It uses the MVVM pattern and Factory Method pattern to implement its architecture.</p>
  <h2>Features</h2>
  <ul>
    <li>Secure login by checking various conditions.</li>
    <li>Ability to select/deselect GPS location, battery level, and time as login conditions.</li>
    <li>Alert message with detailed condition results.</li>
    <li>The SingleLiveData class is used to observe changes to the condition results and update the UI accordingly. This ensures that the UI is always up to date with the latest condition results and provides a seamless user experience.</li>
  </ul>

  <h2>Patterns</h2>
<ul>
  <li><b>MVVM pattern</b></li>
  <li><b>Singletone</b> <p>is used for Restrictions class that contains all the restrictions for checking the login conditions. This class is responsible for providing the minimum and maximum values for GPS location, battery level, and time. The CombinationStrategy objects use these values to determine whether the login conditions are met or not. By configuring these restrictions in a single class, it becomes easier to modify them if necessary, and it helps to maintain the separation of concerns in the code.</p></li>
  <li><p><b>Factory Method pattern</b> 
  <p>is used for creating a strategy for checking login conditions. The `CombinationStrategyFactory` interface defines the methods that create objects of `CombinationStrategy`. The `ConcreteCombinationStrategyFactory` class implements this interface and provides concrete implementations of these methods, creating different types of `CombinationStrategy` objects based on the input parameters.</p>
  <p>The `CombinationStrategy` interface defines a common interface for different types of `CombinationStrategy` objects. The `GpsAndTimeAndBatteryStrategy` class is a concrete implementation of `CombinationStrategy` that combines three other strategies: `GpsStrategy`, `TimeStrategy`, and `BatteryStrategy`.</p></li>
  </ul>
  <h2>Structure</h2>
  <p>The project has the following file structure:</p>
  <pre>
.
├── factory
│   ├── CombinationStrategyFactory.java
│   ├── CombinationStrategy.java
│   ├── ConcreteCombinationStrategyFactory.java
│   └── strategies
│       ├── baseStrategies
│       │   ├── BatteryStrategy.java
│       │   ├── GpsStrategy.java
│       │   ├── NoComponentsStrategy.java
│       │   └── TimeStrategy.java
│       └── combinationsStrategies
│           ├── GpsAndBatteryStrategy.java
│           ├── GpsAndTimeAndBatteryStrategy.java
│           ├── GpsAndTimeStrategy.java
│           └── TimeAndBatteryStrategy.java
├── Initializer.java
├── StartActivity.java
├── StartModel.java
├── StartViewModelFactory.java
├── StartViewModel.java
└── util
    ├── BatteryUtil.java
    ├── ConditionResult.java
    ├── GpsUtil.java
    ├── Restrictions.java
    ├── SingleLiveData.java
    └── TimeUtil.java
  </pre>
  <h2>Usage</h2>
  <ol>
    <li>Open the app on your Android device.</li>
    <li>Tap on the "Login" button.</li>
    <li>The app will check the conditions and display a message with detailed results.</li>
  </ol>
    <h2>Images of app</h2>
<p align="center">
  <img src="https://user-images.githubusercontent.com/51398263/233739710-cd29427c-7fc9-4a05-a2ab-420346d1fa49.png" width="200" height ="350"/>
  <img src="https://user-images.githubusercontent.com/51398263/233739740-a338e07b-ec57-4b60-aa2d-8837a4d989aa.png" width="200" height ="350"/>
  <img src="https://user-images.githubusercontent.com/51398263/233739773-016e493a-b9d4-42a3-b0f6-8e095d159dce.png" width="200" height ="350"/>
  <img src="https://user-images.githubusercontent.com/51398263/233739796-1dad692d-a611-410c-b126-bfd3892515ce.png" width="200" height ="350"/>
  <img src="https://user-images.githubusercontent.com/51398263/233739812-8da1749d-e793-4d49-9642-fe56aa9e6337.png" width="200" height ="350"/>
  <img src="https://user-images.githubusercontent.com/51398263/233739828-e458b844-98d7-4959-a58e-63b66b1176c4.png" width="200" height ="350"/>
</p>
</body>
</html>
