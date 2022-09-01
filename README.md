# PlatformScience

## Introduction
Welcome! Thanks for taking the time to review my project, I had a lot of fun with it. 
Below I will explain the basic features of the app, what technical highlights there are, and the architecture I used. Excited to answer any questions you might have!

## Technical Highlights
### Dependency Injection Using Hilt
with the creation of the SuitabilityCalculator, I wanted to make sure that the fragment didnt have to instantiate it every time it was created, so I injected it using hilt. This is the main place that I utilized DI. 

_Considering there was no local storage and no networking I thought it would be inappropriate to create a view model since the main logic was in its own service (SuitabilityCalculator). I also wanted to make sure that it was as 
lightweight as independent as possible so I did not use any other interfaces or implementations_

###Unit Tests
While I admit I could have spent a long time creating unit tests for the functions in the calculator I wanted to make sure I at least touched on some basic test cases that could identify immediate problems in the algorithms.

###XML Layouts
This was a personal preference as I am keeping my finger on the pulse with Jetpack Compose, but I prefer to use the most current mature technology when it comes to android. It can be a tough 
line to walk but in my experience using mature technology makes steady scalability and flexibility much more convienient long term. 

###Architecture
I did everything I could to follow the current Android standards of MVVM, however there was a large issue that I had to traverse: Context. Since the core or the data required context in order to get it I decided that a view model was not the best route (since view models should in most cases never have access to context). With that being said I wanted to make sure that the SuitabilityCalculator was able to be independent of lifecycles and easily injectable so I decided that I would parse the JSON file in the fragment where the context is easily accessible and pass the json file to the calculator. Using this approach allowed me to unit test the calculator and inject the calculator easily into the fragment.

###Missing Features
I would have loved to add a Jetpack Compose screen and a CI/CD pipeline using github actions, however I didnt have as much bandwidth as I anticipated. I would however, be more than willing to go into greater detail regarding these as well.
