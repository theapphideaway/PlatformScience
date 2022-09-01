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

