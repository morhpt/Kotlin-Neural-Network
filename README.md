[![Build Status](https://travis-ci.org/morhpt/Kotlin-Neural-Network.svg?branch=master)](https://travis-ci.org/buraktaban22/Kotlin-Neural-Network)
[![Dependency Status](https://www.versioneye.com/user/projects/5ac0895a0fb24f0ac49c39be/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5ac0895a0fb24f0ac49c39be)
[![license](https://img.shields.io/github/license/buraktaban22/Kotlin-Neural-Network.svg)](https://github.com/buraktaban22/Kotlin-Neural-Network/blob/v0.1-beta/LICENSE)

# Kotlin Neural Network Library  

Neural Network Kotlin Library for JVM and Android  
  
### Samples  
  
Here are samples on console and Android (Coming Soon..)
  
* [XOR Problem](https://github.com/buraktaban22/Kotlin-Neural-Network/)  
* [Doodle Classifier](https://github.com/buraktaban22/Kotlin-Neural-Network/)  
  
### Installation  
  
​Just download the package from [here](https://github.com/morhpt/Kotlin-Neural-Network/releases) and add it to your project classpath, or just use the maven repo:  
  
Maven:
```xml
<dependency>
  <groupId>com.morhpt.nn</groupId>
  <artifactId>neuralnetwork</artifactId>
  <version>0.1.2</version>
  <type>pom</type>
</dependency>
```

Gradle:
```gradle
implementation 'com.morhpt.nn:neuralnetwork:0.1.2'
```


  
### Usage  
  
Inputs and outputs can be any type of number such as Double, Integer or Float etc.  
  
#### Kotlin Example  
```kotlin  
val nn = NeuralNetwork(3, 5, 1)  
val inputs  = arrayOf<Number>(1, 4, 8)  
val outputs = arrayOf<Number>(0.4) 
```  
```kotlin  
nn.train(inputs, outputs)  
val guess = nn.predict(arrayOf(1,2,4))  
```  
Print the guess output as a Double Array. Use `guess[0]` to access first element of the output.  
```kotlin  
println(guess[0]) // 0.5673437992444852  
```  
  
  
#### Java Example  
```java  
NeuralNetwork nn = new NeuralNetwork(2, 4, 2);  
Number[] inputs = {1,0};  
Number[] outputs = {1.1};  
```  
```java  
nn.train(inputs, outputs);  
Double[] guess = nn.predict(new Number[]{1, 2});  
```  
Print the guess output as a Double Array. Use `guess[0]` to access first element of the output.  
```java  
System.out.println(guess[0]); // 0.7122537311396372  
```

## Methods

You can create create `NeuralNetwork` instance from itself
```kotlin
val nn = NeuralNetwork(2, 4, 1)
val nn2 = NeuralNetwork(nn)
``` 
Use `predict(myArray)` to guess. It returns array of doubles
```kotlin
val myArray = arrayOf<Number>(1, 0)
nn.predict(myArray)
```
Use `train(inputArray, outputArray)` to teach the Neural Network. 
```kotlin
val inputArray = arrayOf<Number>(1, 0)
val outputArray = arrayOf<Number>(1)
nn.train(inputArray, outputArray)
```
To set learning rate
```kotlin
nn.setLearningRate(0.1)
```
To set activation function
```kotlin
nn.setActivationFunction(Activation.sigmoid())
```
To copy 
```kotlin
nn.copy()
```
You can access the `Matrix` class from `NeuralNetwork`
```kotlin
NeuralNetwork.matrix(cols, rows) // Kotlin
NeuralNetwork.Companion.matrix(cols, rows); // Java
```
## Built With
* [Kotlin](https://kotlinlang.org) - The code language used
* [Groovy](https://facebook.github.io/jest/)  - Testing Framework used
## Learn More
* [ Nerual Network video series from The Coding Train](https://www.youtube.com/watch?v=XJ7HLz9VYz0&list=PLRqwX-V7Uu6aCibgK1PTWWu9by6XFdCfh)

## Licensing

This project is open-source via the  [MIT License](https://github.com/buraktaban22/Kotlin-Neural-Network/blob/v0.1-beta/LICENSE).