# Image Processing Program

-   Priyanshu Srivastava [srivastava.pri\@northeastern.edu](mailto:srivastava.pri@northeastern.edu){.email}
-   Ronhit Neema [neema.r\@northeastern.edu](mailto:neema.r@northeastern.edu){.email}
-   Assignment 6 Image Processor

------------------------------------------------------------------------------

## README

### GENERAL INFORMATION

This README is for the Image Processing Program created for Northeastern University's CS5010 Program Design Paradigm course, a part of the MSCS program. 
This program currently takes in image input in ppm, png, bmp and jpg format and has a GUI. 
It can perform several operations on the image and can save the image again in any specified format (ppm, png, bmp, jpg).

This is the third release of the Image Processing Program. 
The third release of the Image Processing Program includes a graphical user interface (GUI) that allows users to manipulate images and view histograms for that respective image. 
It also supports console input from the user, user can either choose to manually input the required manipulation commands or can load a script file that has predefined commands in a .txt file (using -file argument when running the Main method).

To run the program, you will needs to execute the main method. You can then use the program in 3 ways. 
Way 1: To load the images and manipulate it by using single line user inputs commands. When you run the Main (with `-text` argument), the control will go to the controller that accepts user inputs, user can write and run the commands he/she wish to enter. 
Way2: To run your script file with the pre written commands you can pass argument '-file' followed by the path of your script file when running the main method. 
Way3: User can Interact directly through GUI if he/she dows not give nay argument when running the Main method.

The program currently supports several commands that you can use. Use the following command in the defined manner to generate output. 
The commands are as follows: 
`load [filepath] [load-alias]` ,
`run [script-file-path]`, 
`brighten [value] [aliasOfImageToManipulate] [brighten-alias]` , 
`vertical-flip [aliasOfImageToManipulate] [vertical-alias]` , 
`horizontal-flip [aliasOfImageToManipulate] [horizontal-alias]`, 
`greyscale red-component [aliasOfImageToManipulate] [greyscale-alias]`, 
`greyscale green-component [aliasOfImageToManipulate] [greyscale-alias]`, 
`greyscale blue-component [aliasOfImageToManipulate] [greyscale-alias]`, 
`greyscale intensity-component [aliasOfImageToManipulate] [greyscale-alias]`, 
`greyscale luma-component [aliasOfImageToManipulate] [greyscale-alias]` , 
`greyscale value-component [aliasOfImageToManipulate] [greyscale-alias]`, 
`rgb-split [aliasOfImageToManipulate] [red-alias] [green-alias] [blue-alias]` , 
`rgb-combine [combine-alias] [red-alias] [green-alias] [blue-alias]` ,
`save [filepath/aliasOfImageToManipulate.ppm] [aliasOfImageToManipulate]`, 
`quit`, `blur [aliasOfImageToManipulate] [blur-alias]`, 
`sharpen [aliasOfImageToManipulate] [sharpen-alias]`, 
`greyscale [aliasOfImageToManipulate] [greyscale-alias]`, 
`sepia [aliasOfImageToManipulate] [sepia-alias]` and 
`dither [aliasOfImageToManipulate] [dither-alias]`. 

The user must enter the commands as written above with the substituted items in brackets. Otherwise, the program will generate an exception. 
After running a script file (using '-file' argument), the program quits the application and exits the program.

### ABOUT MVC DESIGN
MVC stands for Model-View-Controller and it is a design pattern commonly used in software development, including in Java applications. 
The purpose of the MVC pattern is to separate the different components of an application into distinct responsibilities, making the code easier to maintain, modify, and test.

In the MVC design pattern, the Model represents the data and the business logic of the application. 
It is responsible for retrieving and updating data from a database or other data source, 
and it can also perform calculations or other complex operations on that data.

The View represents the user interface of the application. 
It is responsible for displaying data to the user and capturing user input. 
The View communicates with the Model to retrieve data and update the interface as needed.

The Controller acts as an intermediary between the Model and the View. 
It receives input from the user through the View and then communicates with the Model to retrieve or update data as needed. 
Once the data has been updated, the Controller then updates the View to display the new data.

In Java, the MVC pattern can be implemented using various frameworks and libraries, such as Spring MVC and JavaFX. 
These frameworks provide tools and APIs that make it easier to separate the responsibilities of the Model, View, and Controller, 
and to manage the flow of data between them. 
By using the MVC pattern, Java developers can create robust and scalable applications that are easier to maintain and extend over time.

### ABOUT COMMAND DESIGN PATTERN
The Command Design Pattern is a behavioral design pattern that encapsulates a request as an object, 
thereby allowing it to be parameterized with different requests, queued, logged, and supports undoable operations.

### ABOUT ADAPTER DESIGN PATTERN
The Adapter design pattern is a structural design pattern in Java that allows incompatible interfaces to work together by converting one interface into another. It is also known as the Wrapper pattern.

In this pattern, an adapter class is created that acts as a bridge between two incompatible interfaces. The adapter class implements the interface of the target class and converts the interface of the adaptee class into the target interface.

The adapter pattern has three main components:

Target: This is the interface that the client code uses to interact with the system.

Adaptee: This is the interface that needs to be adapted so that it can work with the target interface.

Adapter: This is the class that implements the target interface and adapts the adaptee interface so that it can be used by the client code.

To implement the adapter pattern in Java, you can create an adapter class that extends or implements the target interface and has a reference to the adaptee object. The adapter class then maps the methods of the target interface to the methods of the adaptee interface, so that the client code can use the adapter class as if it were the target interface.

### ABOUT GRAPHICAL USER INTERFACE
GUI stands for Graphical User Interface, which is the visual interface that allows users to interact with an application. 
In the context of the Model-View-Controller (MVC) design pattern in Java, the GUI is part of the View layer.

In MVC, the Model represents the data and business logic, the View represents the user interface, and the Controller acts as an intermediary between the Model and the View. 
The View layer is responsible for rendering the user interface and handling user input.

In the MVC design pattern, the GUI is responsible for rendering the View, which is typically composed of multiple components or widgets. 
The View receives updates from the Model layer, which it then displays to the user. The user can interact with the View, which then sends user input to the Controller layer. 
The Controller processes the user input and updates the Model accordingly.

### ABOUT HISTOGRAM
A histogram is a graphical representation of the distribution of pixel values in an image. 

In the context of RGB images, a histogram can be used to show the distribution of the red, green, and blue color components of each pixel.

### CONTROLLER DESIGN
##### Interface: ImageController
About: It provides a contract for classes that will control the processing and management of images. 
It has two methods: "start()" and "startScript(Reader)" which are responsible for initiating the processing of the images.

##### Class: ImageControllerImpl
About: It is the ImageController implementation that processes and manages images based on user input. 
It has methods to load images, apply various transformations/filters to them, and save the resulting images. 
It implements the Command Design Pattern with the use of an interface that defines a single method, execute(), which is implemented by each command.

The start() and startScript() methods read input from the user or a script and execute commands accordingly. 
The executeStart() method parses the input and executes the appropriate command by calling its execute() method.
The ImgModel and ImgView objects are passed as parameters to each command's execute() method, 
allowing the commands to modify the model and update the view.

Overall, the code is well-organized and follows good programming practices, such as encapsulation and separation of concerns. The use of an interface for commands and a map to store them provides flexibility and extensibility to the code.

##### Class: ImageControllerGuiImpl
About: This is a Java class that implements an image processing controller with a graphical user interface (GUI) to execute commands on images. 
The class uses several other classes and interfaces to manage image processing, including ImgModel, ImgViewInteractive, ImgUtil, and Image. 

##### Reading/Writing
##### Class: ImgUtil
About: This Java utility class ImgUtil provides methods for reading and writing image files in various formats.  
When load or save commands are triggered in the application, our controller handles the loading and saving of data from and to the OS file System.
As a good design expects the responsibility of reading and saving the image from the OS file system should be from the controller, but interpreting the contents of the file that is read is to be done by the model. Additionally, our controller does not know anything about the inner workings of the model. 
In fact, it only knows about the capabilities of the model from the method signatures of the model interface. 

### MODEL DESIGN
##### Interface: Image
About: This is an interface for representing an image in a program. The interface defines four methods that can be used to retrieve information about the image and its data. Our model knows its data, and work on its data, without knowing where the data originated from.

##### Class: ImageImpl
About: The class has four methods that implement the methods of the "Image" interface. 
The "getHeight()", "getWidth()", and "getMaxValue()" methods return the corresponding instance variables, 
while the "getImage()" method returns the "image" instance variable.

The "image" instance variable is a 3D array of integers, which represents the actual image data. 
The first dimension of the array represents the height of the image, the second dimension represents the width of the image, and 
the third dimension represents the color channels (e.g., red, green, blue).

##### Interface: ImgModel
About: This is an interface named ImgModel that represents a model for image processing. 
It defines a set of methods for performing various image manipulation operations and checking the validity of image files.

##### Class: ImgModelImpl
About: This Java class is an implementation of an image processing application that can perform various operations on various images formats (ppm, png, jpg, bmp).
The class ImgModelImpl implements the ImgModel interface, which defines several methods for image manipulation.

##### Interface: ImgModelAdaptive
About: the ImgModelAdaptive interface is an abstraction of an adapter design pattern implementation in Java for manipulating and transforming image files. 

##### Class: ImgModelAdaptiveImpl
About: Class ImgModelAdaptiveImpl implements the ImgModelAdaptive interface. 
This adapter class adapts an ImgModel object to the ImgModelAdaptive interface.
The class provides implementations for all the methods of the ImgModelAdaptive interface. 
These methods call the corresponding methods on the model object and use the returned values to create and return an Image object.

##### Interface: ImgFilter
About: This interface ImgFilter has two methods: filterApplication and filterDitherApplication. 
It provides a contract for classes that represent an image filter and can be used to apply various filters to a given image.

##### Class: ImgFilterImpl
About: The class defines the implementation of the ImgFilter interface, 
which provides methods for filtering images and applying dithering to them.

##### Interface: ImgTransform
About: This is an interface for image transformation, 
which defines a method for transforming an image using a matrix and 
returns the transformed image as a 3D array of integers.

##### Class: ImgTransformImpl
About: This class implements the ImgTransform interface for image transformation 
using matrix multiplication to apply filters to the pixels of the image.

##### Interface: ImgHist
About: Interface ImgHistdefines a method to calculate a histogram of an Image. 
The method takes an Image object as input and returns a 2D array representing the histogram data, where the first dimension represents the color channel.

##### Class: ImgHistImpl
About: Java class ImgHistImpl implements the ImgHist interface to calculate histograms for an Image object. The class calculates separate histograms for red, green, blue, and overall intensity values of each pixel in the image.

### VIEW DESIGN
##### Interface: ImgView
About: This interface is for an image view that can display the status of an image operation, 
taking in the name of the image and the operation being performed on it as input parameters.

##### Class: ImgViewImpl
About: This java class implements the ImgView interface for displaying status messages related to image operations. 
It uses a HashMap to map image operation names to corresponding Command objects, 
which execute the appropriate actions and print success messages to the console.

##### Interface: ImgViewInteractive
About:Theinterface called ImgViewInteractive has methods that represent the behavior of an ImageView. The methods include setting an image, making objects visible, setting a command callback, getting a command, showing error messages, and refreshing the view. The interface can be implemented by any class that wants to use an ImageView.

##### Class: ImgViewInteractiveImpl
About: Java class ImgViewInteractiveImpl implements the ImgViewInteractive interface. 
It is a graphical user interface (GUI) for an image processing program. 
The program allows the user to load, save, and manipulate images using filters and transformations.
The class provides a functional and user-friendly GUI for an image processing program.

##### Class: ValidCheckedException
About: A custom checked exception called ValidCheckedException, extends the built-in Exception class. 
This exception is meant to be thrown when there is an error while validating user input or processing image operations.

### MAIN METHOD
This Java code is the entry point for a program that can run in three modes: file, text, or graphical user interface (GUI). 
It initializes the Model, View, and Controller objects based on the command line arguments and starts the appropriate mode. 
In file mode, it reads commands from a file, while in text mode, it prompts the user to enter commands in the console. In default mode, it prompts the user to interact with GUI.

## Updates in program as asked in Assignmnet 5:
1. Addition of filters: blur and sharpen.
2. Addition of color tranforms: sepia and greyscale.
3. Addition of feature to dither the image.
4. Application now allows user to work on various formats (ppm, png, jpg, bmp).
5. Application now allows user to run 'main' method with argument `-file [filepath]` that run the commands present in the txt file and exits the program after execution.
6. Application now allows user to load an image in one format, process it and then save it in another format. example: load ppm, work on it and save it in png format. This can be applied with all the formats. (png, ppm, jpg, bmp)

## Design Changes and Justification in Assignmnet 5:
Change 1: Moving the Util class outside MVC
Justification: In our previous Design, the util class was a part of model and model was performing the input output operation.
But now our controller is performing input and output operation using ImgUtil class(i.e reading and writing images) as expected.
As a good design expects the responsibility of reading and saving the image from and to the OS file system should be from the controller, but interpreting the contents of the file that is read is to be done by the model, our deaign follows that. 
Additionaly, our controller also does not know anything about the inner workings of the model. 

## Enhancements and Justification in Assignmnet 5:
Enhancement 1: Incorporation of Image Class
Justification: The Image class inside the model is now responsible for getting the image data (height, width, maxValue and 3D array).
Now the data is being passed as an image object instead of 3D array. Now as a result, our program is more object oriented.
The images are now stored in the model to hide the inner workings. Our model knows its data, and work on its data, 
without knowing where the data originated from.

## Updates in program as asked in Assignmnet 6:
1. Addition of GUI (that supports all the features) for the user to interact with (on the current Image)
2. Addition of Histogram line chart.
3. The GUI should load and save image that the user processes.
4. Addition of error conditions to show in GUI, if any.

## Enhancements and Justification in Assignment 6:
Enhancement 1: Addition of Adapter Class
Justification:  The Adapter design pattern is a useful technique in Java that promotes flexibility and modularity in applications. It achieves this by separating the interface from the implementation of a class. This separation enables modifications or replacements of a class's implementation without affecting the clients that rely on it.

In our project, we encountered a scenario where the interface ImgModel's methods had a return type of void, but we required a return type of Image to implement the GUI. Since the ImgModelImpl's methods worked with 3D int arrays, we decided to use the Adapter design pattern. We created another interface, similar to ImgModel, with all methods having a return type of Image. We then implemented the adapter class by calling the methods from the original model in the respective adapter model implementation.

Adopting this approach improved the code's design without requiring us to alter the previously implemented code. This approach will enhance the code's maintainability and facilitate future updates. Additionally, separating the interface and implementation makes testing and debugging simpler since we can test each component separately.

## Change Log
### First Release
Our first release had basic funtionalities on PPM format image file.
The functionalities were loading, saving, flipping, brightening, darkening, splitting, 
greyscale and combining through text based commands

### Second Release
With our second release we added more functionalities like blur, sharpen, dither, sepia and grayscale transform. The program also supported PPM, JPG, JPEG, PNG and BMP image file formats.
The progrom also took script as a argument to run all the commands at once.

### Third Release
With our third release, we added histogram feature and a GUI, for the user to interact with application and see updated histograms as he/she apply various filters and transforms to the image. All the previous work is still supported by the application.

## Copying / License

*The following images are owned by the author and are authorized for use:* 
1. Neema, Ronhit "dot.ppm", "chase.png", "photo.bmp"
2. Srivastava, Priyanshu "pixel.ppm", "discover.jpg", "ImageGUI.png"
