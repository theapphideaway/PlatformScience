# PlatformScience

## Introduction
Welcome! Thanks for taking the time to review my project, I had a lot of fun with it. 
Below I will explain the basic features of the app, what technical highlights there are, and the architecture I used. Excited to answer any questions you might have!

## Technical Highlights
### Finding a Balance
One of the first things that you might find in the codebase is that the algorithms in the SuitabilityCalculator are not optimized very well. This was a calculated risk as I wanted to strike a good balance between a scalable, well made android app and optimized algorithms. To my suprise the algorithms were more challenging than they appeared on the onset so I wanted to make sure that I had them working reliably. After I could get the expected functionality and they were unit tested to a basic degree I wanted to make sure that the groundwork for a scalable android app was in place. So at the end of the day there were compromises to both the algorithms and the architecture in order to achieve a good balance given the constraints. For instance the algorithms could have had less loops to improve efficiency, used less expensive data types, and the app could have been broken into base modules, more unit tests could have been added, and I'm sure I could have seperated the file reading logic into its own service and created an instrumented test for it. With these things missing, I am still very happy with the outcome given the time constraints and since I believe that all the criteria has been met, I would happily send this to QA.
### Dependency Injection Using Hilt
With the creation of the SuitabilityCalculator, I wanted to make sure that the fragment didnt have to instantiate it every time it was created, so I injected it using hilt. This is the main place that I utilized DI. 

_Considering there was no local storage and no networking I thought it would be inappropriate to create a view model since the main logic was in its own service (SuitabilityCalculator). I also wanted to make sure that it was as 
lightweight as independent as possible so I did not use any other interfaces or implementations_

### Unit Tests
While I admit I could have spent a long time creating unit tests for the functions in the calculator I wanted to make sure I at least touched on some basic test cases that could identify immediate problems in the algorithms.

### XML Layouts
This was a personal preference as I am keeping my finger on the pulse with Jetpack Compose, but I prefer to use the most current mature technology when it comes to android. It can be a tough 
line to walk but in my experience using mature technology makes steady scalability and flexibility much more convienient long term. 

### Architecture
I did everything I could to follow the current Android standards of MVVM, however there was a large issue that I had to traverse: Context. Since the core or the data required context in order to get it I decided that a view model was not the best route (since view models should in most cases never have access to context). With that being said I wanted to make sure that the SuitabilityCalculator was able to be independent of lifecycles and easily injectable so I decided that I would parse the JSON file in the fragment where the context is easily accessible and pass the json file to the calculator. Using this approach allowed me to unit test the calculator and inject the calculator easily into the fragment.

### Missing Features
I would have loved to add a Jetpack Compose screen and a CI/CD pipeline using github actions, however I didnt have as much bandwidth as I anticipated. I would however, be more than willing to go into greater detail regarding these as well.
