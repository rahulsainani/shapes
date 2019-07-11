# Shapes

A simple android app that allows users to create shapes on a canvas. 

## What it does

The required task is to build an Android application consisting of two screens:

*Editor screen*

1. Display 3 buttons (square, circle, and triangle). Each time you tap on a button: an object of that shape will be created, and displayed in a random position on screen.
2. The user should be able to tap each shape which will cause it to become another shape: tapping a square will make it a circle, tapping a circle will make it a triangle, tapping a triangle will make it a square.
3. Display an "Undo" button. Each time you press undo it will cancel the last action performed; which will be either the creation or the transformation of a shape object.
4. Display a "Stats" button that will open the second screen of the app.

*Stats screen*

1. Show a list that will display the count of all the objects placed on the canvas, grouped by shape.

## Suggested improvements
1. Delete all: in the stats screen, implement a functionality to delete a row. It will cause all the shapes of that specific kind to be removed from the canvas.
2. Remove shape: in the canvas, if you long tap on a shape you can delete it. You should also be able to undo the deletion of an object.

## Notes
- You are encouraged to use Kotlin, but you are free to use either Java or Kotlin, or a mix of both, depending on what you feel more comfortable with.
- While not required, you are welcome to use code from third party libraries.
- The application you will create should run on Android Lollipop and above (API 21+), but you are free to support older versions of Android if you wish.

## How we judge what you create
- Your app must compile.
- We’re looking at the quality of your code, your code style, consistency of your code, architecture, design, and application of best practices.
- Don't focus too much on the user interface, using default Android views and/or basic custom views is fine.