# Shapes

A simple android app that allows users to create shapes on a canvas. 

## What it does

The required task is to build an Android application consisting of two screens:

*Editor screen*

1. Display 3 buttons (square, circle, and triangle). Each time you tap on a button: an object of that shape will be created, and displayed in a random position on screen.
2. The user should be able to tap each shape which will cause it to become another shape: tapping a square will make it a circle, tapping a circle will make it a triangle, tapping a triangle will make it a square.
3. Display an "Undo" button. Each time you press undo it will cancel the last action performed; which will be either the creation or the transformation of a shape object.
4. Display a "Stats" button that will open the second screen of the app.
5. Remove shape: in the canvas, if you long tap on a shape you can delete it. You should also be able to undo the deletion of an object.

*Stats screen*

1. Show a list that will display the count of all the objects placed on the canvas, grouped by shape.
2. Delete all: in the stats screen, implement a functionality to delete a row. It will cause all the shapes of that specific kind to be removed from the canvas.

