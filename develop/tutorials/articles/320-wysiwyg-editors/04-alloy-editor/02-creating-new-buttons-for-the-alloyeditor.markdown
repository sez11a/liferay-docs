# Creating and Contributing new Buttons to AlloyEditor [](id=creating-and-contributing-new-buttons-to-alloyeditor)

It is possible to add additional AlloyEditor functionality through OSGi bundles. 
This tutorial demonstrates how to add a button to the editor. 

In this tutorial, you will learn how to

- Create an OSGi bundle for your own button
- Create a custom button for `AlloyEditor`
- Contribute your button to the list of available buttons

Go ahead and get started by creating the OSGi bundle next.

## Creating the OSGi Bundle [](id=creating-the-osgi-bundle)

AlloyEditor is built on `React.js` and uses `jsx` to render each button in the 
editor. Below is the folder structure for a module that adds a new button:

- `frontend-editor-my-button-web`
	- `src`
	    - `main`
	        - `java`
                    - `com/liferay/frontend/editor/my/button/web/`
		        - `editor`
    			    - `configuration`
  			        - `AlloyEditorMyButtonConfigContributor.java`
        			- `servlet`
        			    - `taglib`
        			        - `AlloyEditorMyButtonDynamicInclude.java`
      		- `resources`
      		    - `META-INF`
      		        - `resources`
          			    - `js`
          			        - `my_button.jsx`

	- `.babelrc` - needed since `JSX` is being compiled

	- `bnd.bnd`(example configuration shown below)

		Bundle-Name: Liferay Frontend Editor AlloyEditor My Button Web
		Bundle-SymbolicName: com.liferay.frontend.editor.alloyeditor.my.button.web
		Bundle-Version: 1.0.0
		Liferay-Releng-Module-Group-Description:
		Liferay-Releng-Module-Group-Title: Rich Text Editors
		Web-ContextPath: /frontend-editor-alloyeditor-my-button-web


	- `build.gradle`(contents shown below)

		configJSModules {
			enabled = false
		}

		dependencies {
			provided group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
			provided group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
			provided group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
		}

		transpileJS {
			bundleFileName = "js/buttons.js"
			globalName = "AlloyEditor.Buttons"
			modules = "globals"
			srcIncludes = "**/*.jsx"
		}

	- `package.json`(contents shown below)

		{
			"devDependencies": {
				"babel-preset-react": "^6.11.1",
				"metal-cli": "^2.0.0"
			},
			"name": "frontend-editor-alloyeditor-my-button-web",
			"version": "1.0.0"
		}

  - `.babelrc` (contents shown below)

    {
      "presets": ["babel-preset-react"]
    }

The contents of some of the files have been added as well, since the 
`build gradle` file requires some customizing. 

Now that your OSGi bundle is configured, you can learn how to create buttons for 
the AlloyEditor next. 

## Creating the Button [](id=creating-the-button)

Below is an example configuration for a JSX file that creates a new button:

    /* global React, ReactDOM AlloyEditor */

    (function() {
            'use strict';

            var React = AlloyEditor.React;

            var ButtonMyButton = React.createClass(
                    {
                            mixins: [AlloyEditor.ButtonStateClasses],

                            displayName: 'ButtonMyButton',

                            propTypes: {
                                    editor: React.PropTypes.object.isRequired
                            },

                            statics: {
                                    key: 'myButton'
                            },

                            /**
                             * Lifecycle. Renders the UI of the button.
                             *
                             * @method render
                             * @return {Object} The content which should be rendered.
                             */
                             render: function() {
                                     var cssClass = 'ae-button ' +
                                     this.getStateClasses();

                                     var svg = Liferay.Util.getLexiconIconTpl('view');

                                     return (
                                             <button className="ae-button"
                                             data-type="button-embed-video"
                                             onClick={this._doSomething}
                                             tabIndex={this.props.tabIndex}
                                             dangerouslySetInnerHTML={{__html: svg}} />
                                     );
                             },

                            /**
                             * @protected
                             * @method  _doSomething
                             * @param {MouseEvent} event
                             */
                            _doSomething: function(event) {
                                    console.log('do something!');
                            }
                    }
            );

            AlloyEditor.Buttons[ButtonMyButton.key] = AlloyEditor.ButtonMyButton
            = ButtonMyButton;
    }());

The configuration above creates a new button called `ButtonMyButton`. The key 
aspects to note here are the lines that reference the global `AlloyEditor`. You 
can create your own JavaScript functions to interact with your button. 

Now that you've seen how you can use a JSX file to create a new button, you can 
learn how to contribute your button to the editor next. 

## Contributing the Button [](id=contributing-the-button)

The next step is to add your button to the list of already available buttons. 
This can be achieved thanks to some smartly placed 
`<liferay-util:dynamic-include />` tags in the editor's infrastructure. To make 
your button available in the AlloyEditor, you must extend the 
[`BaseDynamicInclude` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/BaseDynamicInclude.html). 
Below is an example configuration that extends this class:

    package com.liferay.frontend.editor.alloyeditor.my.button.web.servlet.taglib;

    import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
    import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
    import com.liferay.portal.kernel.theme.ThemeDisplay;
    import com.liferay.portal.kernel.util.PortalUtil;
    import com.liferay.portal.kernel.util.StringBundler;
    import com.liferay.portal.kernel.util.WebKeys;

    import java.io.IOException;
    import java.io.PrintWriter;

    import javax.servlet.ServletContext;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

    @Component(immediate = true, service = DynamicInclude.class)
    public class AlloyEditorMyButtonDynamicInclude extends BaseDynamicInclude {

            @Override
            public void include(
                            HttpServletRequest request, HttpServletResponse response,
                            String key)
                    throws IOException {

                    ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
                            WebKeys.THEME_DISPLAY);

                    PrintWriter printWriter = response.getWriter();

                    StringBundler sb = new StringBundler(7);

                    sb.append("<script src=\"");
                    sb.append(themeDisplay.getPortalURL());
                    sb.append(PortalUtil.getPathProxy());
                    sb.append(_servletContext.getContextPath());
                    sb.append("/js/buttons.js");
                    sb.append("\" ");
                    sb.append("type=\"text/javascript\"></script>");

                    printWriter.println(sb.toString());
            }

            @Override
            public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
                    dynamicIncludeRegistry.register(
                            "com.liferay.frontend.editor.alloyeditor.web#alloyeditor#" +
                                    "additionalResources");
            }

            @Reference(
                    target = "(osgi.web.symbolicname=com.liferay.frontend.editor.alloyeditor.my.button.web)"
            )
            private ServletContext _servletContext;
    }

Now that your button is included, you can follow the steps covered in 
[Adding Buttons to the AlloyEditor's Toolbars](adding-buttons-to-alloyeditor-toolbars) 
tutorials to add the button to the editor's toolbars. 

## Related Topics [](id=related-topics)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-1/adding-new-behavior-to-an-editor)

[Embedding Content into the Alloy Editor](/develop/tutorials/-/knowledge_base/7-1/embedding-content-in-the-alloy-editor)

[WYSIWYG Editor Dynamic Includes](/develop/tutorials/-/knowledge_base/7-1/wysiwyg-editor-dynamic-includes)