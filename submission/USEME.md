# Image Processing Program

-   Priyanshu Srivastava [srivastava.pri\@northeastern.edu](mailto:srivastava.pri@northeastern.edu){.email}
-   Ronhit Neema [neema.r\@northeastern.edu](mailto:neema.r@northeastern.edu){.email}
-   Assignment 6 Image Processor

------------------------------------------------------------------------------------------------------------------------------------------------------------------
## USEME:

Welcome to the Image Processing Program in Java! Here's a brief summary of the supported commands and how to use them in the USEME file:

### COMMANDS FOR TEXT BASED SCRIPTING:
The below are the commands that are supported by the application. 
These commands can be given by the user line after line or through a script file.

load: loads an image from a specified path or URL to the application. 
syntax: `load [filepath] [load-alias]` 
example: load res/pixel.ppm pixel

brighten: increases the brightness of the loaded image.
syntax: `brighten [value] [aliasOfImageToManipulate] [brighten-alias]`
example: brighten 10 pixel pixel-brighter

vertical-flip: flips the loaded image vertically. 
syntax: `vertical-flip [aliasOfImageToManipulate] [vertical-alias]`
example: vertical-flip pixel pixel-vflip

horizontal-flip: flips the loaded image horizontally. 
syntax: `horizontal-flip [aliasOfImageToManipulate] [vertical-alias]`
example: horizontal-flip pixel pixel-hflip

rgb-split: splits the loaded image into its red, green, and blue color components.
syntax: `rgb-split [aliasOfImageToManipulate] [red-alias] [green-alias] [blue-alias]`
example: rgb-slip pixel red-pixel green-pixel blue-pixel

rgb-combine: combines the red, green, and blue color components of the loaded image. 
syntax: `rgb-split [aliasOfImageToManipulate] [red-alias] [green-alias] [blue-alias]`
example: rgb-slip pixel red-pixel green-pixel blue-pixel

greyscale: converts the loaded image to grayscale with specific component. 
syntax: `greyscale red-component [aliasOfImageToManipulate] [greyscale-alias]`
syntax: `greyscale green-component [aliasOfImageToManipulate] [greyscale-alias]`
syntax: `greyscale blue-component [aliasOfImageToManipulate] [greyscale-alias]`
syntax: `greyscale intensity-component [aliasOfImageToManipulate] [greyscale-alias]`
syntax: `greyscale luma-component [aliasOfImageToManipulate] [greyscale-alias]` 
syntax: `greyscale value-component [aliasOfImageToManipulate] [greyscale-alias]`

example: greyscale red-component pixel greyscale-red-pixel
example: greyscale green-component pixel greyscale-green-pixel
example: greyscale blue-component pixel greyscale-blue-pixel
example: greyscale intensity-component pixel greyscale-intesity-pixel
example: greyscale luma-component pixel greyscale-luma-pixel
example: greyscale value-component pixel greyscale-value-pixel

save: saves the modified image to a specified path or URL. 
syntax: `save [filepath/aliasOfImageToManipulate.ppm] [aliasOfImageToManipulate]`
example: save res/pixel-save.ppm pixel

blur: blurs the loaded image. 
syntax: `blur [aliasOfImageToManipulate] [blur-alias]`
example: blur pixel pixel-blur 

sharpen: sharpens the loaded image.
syntax: `sharpen [aliasOfImageToManipulate] [sharpen-alias]`
example: sharpen pixel pixel-sharpen 

greyscale: applies a greyscale transformation to the loaded image.
syntax: `greyscale [aliasOfImageToManipulate] [greyscale-alias]`
example: greyscale pixel pixel-greyscale

sepia: applies a sepia tone to the loaded image.
syntax: `sepia [aliasOfImageToManipulate] [sepia-alias]`
example: sepia pixel pixel-sepia 

dither: applies dithering to the loaded image.
syntax: `dither [aliasOfImageToManipulate] [dither-alias]`
example: dither pixel pixel-dither 

run: runs a script file containing multiple commands. 
syntax: `run [script-file-path]`
example: run test/testScripts/GenerateAllPPM.txt

quit: exits the application.
syntax: `quit`
example: quit

### CONDITIONS FOR TEXT BASED SCRIPTING:

1. Image should be loaded first before applying any filter or transformation.
2. User should follow the syntax correctly while running any command.
3. The commands (in examples) here works on .ppm file format. But our programs supports other file formats too (example: bmp, jpg and png).
4. Our program is able to switch between file formats (e.g. load a PPM file, work on it and save it as a JPEG file, etc.)
5. After passing command 'quit', user cannot enter more commands and the application gets quit.
6. If a user runs 'Main' method with argument '-file [filepath]' then the application runs the commands present in the script file and quits the application after running it.
7. If a user runs 'Main' method with argument '-text' then the application allows the user to give text based commands.

### TEST SCRIPTS

There are several script files already present inside the `test/testScripts`. These files were used for testing and their results are stored inside `test/res` folder. Additionally, the reader should note that:
1. The file `test/testScripts/GenerateAllPPM.txt` have script commands that stores the output inside res folder and has been used to provide images as asked in the assignment 4.
2. The file `test/testScripts/GenerateFilters.txt` have script commands that stores the output inside res folder and has been used to provide images as asked in the assignment 5.

### TEST SCRIPTS WITH JAR FILE:
1. For file based commands:
i. The file `res/AllScripts.txt` have all working features that are in the application as asked in the assignment 6. The output after running this file will be stored in `res/out`
ii. To run the file, give command in terminal as `java -jar ImageProgram.jar -file AllScripts.txt`

2. For text based commands:
i. The res folder has sample images that can be used during text based scripting commands.
ii. To run the file, give command in the terminal as `java -jar ImageProgram.jar -text`, after this command, user is entered into text based scripting command line. 
iii. User can then give command as `load pixel.ppm pixel` then `blur pixel pixel-blur` then `save pixel-blur.png pixel-blur` then `quit`. User can use any command they like mentioned in the list of commands above for text based scripting

3. For GUI:
i. To run the file, give command in the terminal as `java -jar ImageProgram.jar`
ii. This will allow the user to interact with the GUI, he/she can click on `LOAD IMAGE FROM FILES` button and then a window pops up, 
where the user can navigate to the res folder and find the image `ImageGUI.png` and hit `Open`.
iii. The selected image will be uploaded to the GUI and can be seen by user then the user can click on different buttons to perform various transforms and filters.

### COMMANDS FOR GUI:
The below is the way the graphical user interface can be used:

`LOAD IMAGE FROM FILES` button: This button is used to load image into the the GUI. As soon as the button is clicked, a window with files appear, user can navigate and select the file and can click on `Open` button.
`BRIGHTEN IMAGE` button: This button is used to brighten image, if user click on the button, an input box pops up which asks user to enter the integer value by which he/she wants the brighten image, only after entering the proper integer value and clicking on `Ok` Button the image is brightened.
`FLIP IMAGE VERTICALLY` button: This button is used to flip the loaded image vertically. The image flips as soon as the user clicks the button.
`LOAD IMAGE HORIZONTALLY` button: This button is used to flip the loaded image horizontally. The image flips as soon as the user clicks the button.
`SPLIT IMAGE INTO RGB` button: This button is used to Split image into RGB component, if user click on the button, an window pops up which asks user to select the color component he/she wants the to see the splitted image, only after selecting that button the image is splitted. The other two (unselected) splitted image stays in the memory.
`COMBINE IMAGE FROM RGB` button: This button is used to combine splitted images into one and after clicking on the button, the user is shown the combined image.
`BLUR IMAGE` button: This button is used to blur the loaded image. The image blurs as soon as the user clicks the button.
`SHARPEN IMAGE` button: This button is used to sharpen the loaded image. The image sharpens as soon as the user clicks the button.
`GREYSCALE IMAGE` button:This button is used to greyscale the loaded image, if user click on the button, an window pops up which asks user to select the component he/she wants the to see the greyscale image, only after selecting (clicking the button) that the image is greyscaled according to the user selection.
`SEPIA IMAGE` button: This button is used to sepia the loaded image.The image sepia filters happens as soon as the user clicks the button.
`DITHER IMAGE` button: This button is used to dither the loaded image. The image dithers as soon as the user clicks the button.
`SAVE IMAGE` button: This button is used to save the loaded image. A window appears where user need to navigate, give file name and proper format of the file, and after clicking on `Save` button, the image (use is currently seeing) gets saved.
`CLEAR IMAGE PANE` button: This button is used to clear the loaded image from the GUI. The image/pane/area clears as soon as the user clicks the button.
`QUIT PROGRAM` button: This button is used to quit the program. The program quits as soon as user clicks the button.

Note: There is a top pane in the application. The File section helps in load, quit and save funtions and they happen similarly as explained above
The pane also has About section that gives help and abouve program sub section to gain extra information about the application. A window pops up with info as soon as user clicks on the sub-sections.

### CONDITIONS AND INFORMATION FOR GUI:
1. Image should be loaded first before applying any filter or transformation.
2. User cannot combine an image without splitting it first.
3. When user click on `BRIGHTEN IMAGE` button, a window pops up for the user to enter the value by which he/she wants to increase or decrease the brightness. Only after giving the proper input, the image is brightened and darkened.
4. When user click on `GREYSCALE IMAGE` button, a window pops up for the user to select which type of greyscale component he wants to see the image of. Only after selecting the proper component, the image is greyscaled.
5. When user click on `SPLIT IMAGE INTO RGB` button, a window pops up for the user to select which type of split he wants to see of the image. Only after selecting the proper split, the image is shown. Note: The other two splitted images are with the program but not shown to the user.
6. The GUI here works on various file formats such as (ppm, bmp, jpg and png).
7. Our program is able to switch between file formats (e.g. load a PPM file, work on it and save it as a JPEG file, etc.)
8. After clicking the `QUIT PROGRAM` button, user cannot interact with GUI and the application gets quit.
9. If a user runs 'Main' method with no argument then the application runs with the GUI for the user to interact.
10. The GUI only shows one image at a time, this means the user can only manipulate the image that is shown in the UI.
11. The GUI if the image is bigger that area allocated the user can scroll the image.
12. The histogram of the visible image is visible as a line chart on the GUI screen at all times. 
If the image is manipulated, the histogram automatically refreshs. 
The histogram shows the red, green, blue and intensity components.
13. The user is able to specify suitably the image to be loaded and saved that the user is going to process. That is, the program not assume a hardcoded file or folder to load and save.
14. Any error conditions is suitably displayed to the user, through pop-up messages and clearly visible text as appropriate.
15. A good suggestion would be to use image `ImageGUI.png` that is present in `res` folder to play around with the GUI.
16. On the top pane of the program, there is an `About` section, it will help user to understand a gist of the application and 
help him to contract the right person if they find any issues.
17. User can Load, Save or Quit program by button as well as using `File` section present at the top pane.

### IMAGES PROVIDED
There are six image files inside the `res` folder namely, dot.ppm, pixel.ppm, chase.png, discover.jpg, photo.bmp and ImageGUI.png that can be used for testing purpose.

### COPYING / LICENSE:

*The following images are owned by the author and are authorized for use:* 
1. Neema, Ronhit "dot.ppm", "chase.png", "photo.bmp"
2. Srivastava, Priyanshu "pixel.ppm", "discover.jpg", "ImageGUI.png"