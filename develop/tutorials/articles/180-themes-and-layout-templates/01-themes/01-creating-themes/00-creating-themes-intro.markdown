# Creating Themes [](id=creating-themes)

The Liferay Theme Generator lets you create themes, themelets, layout templates 
and more. It is just one of Liferay JS Theme Toolkit's 
[tools](https://github.com/liferay/liferay-themes-sdk/tree/master/packages). 
There are a few dependencies required to run the generator. If you have NodeJS 
installed, you're already one step ahead. 

Follow these steps to install the Liferay Theme Generator and generate a theme:

1.  Install [Node.js](http://nodejs.org/). We recommend installing the Long Term 
    Support (LTS) version. Note that Node Package Manager (npm) is installed 
    with this as well. You'll use npm to install the remaining dependencies and 
    generator. 

2.  Use npm to install 
    [Yeoman](http://yeoman.io/) 
    and 
    [gulp](https://gulpjs.com/):

        npm install -g yo gulp

3.  Install the Liferay Theme Generator. @product-ver@ requires v8.x.x of the 
    Liferay JS Theme Toolkit:

        npm install -g generator-liferay-theme 

    Each version corresponds to a version of @product@:
    
    | Capability                     | Required toolkit version |
    | ------------------------------ | ------------------------ |
    | Import a 6.2 theme             | v8.x                     |
    | Create themes for 7.0          | v8.x                     |
    | Create themes for 7.1          | v8.x                     |
    | Upgrade a theme from 6.2 to 7.0 | v8.x                     |
    | Upgrade a theme from 7.0 to 7.1 | v8.x                     |
    | Create themes for 7.2          | v9.x                     |
    | Upgrade a theme from 7.1 to 7.2 | v9.x                     |
   
    If you're on Windows, follow the instructions in step 4 to install Sass, 
    otherwise you can skip to step 5.

4.  The generator uses node-sass. If you are on Windows, you must also install 
    [node-gyp and Python](https://github.com/nodejs/node-gyp#installation).

5.  Run the generator and follow the prompts to create your theme:

        yo liferay-theme

    ![Figure 1: You can generate a theme by answering just a few configuration questions.](../../../../images/theme-generator-theme-prompt.png)

6.  Navigate to your theme folder and run `gulp deploy` to deploy your new theme 
    to the server.
 
Now you have a powerful theme development tool at your disposal. The sky is the 
limit!

![Figure 2: The tools are in your hands to create any theme you can imagine.](../../../../images/theme-generator-theme-example.png)
