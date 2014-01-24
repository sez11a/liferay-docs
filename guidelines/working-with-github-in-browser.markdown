# Working with GitHub Repositories From Your Browser

Have you ever been asked to contribute to a project on GitHub? Have you ever
wanted to review changes to project files on GitHub? Would you like to be able
to make changes to a project's files without having to install additional
desktop tools?

If you answer *yes* to any of these questions, you're in luck! You can work with
GitHub projects, to view files, modify files, and submit modifications, from
within your browser.

The following documentation teaches you how to get your GitHub account, get
copies of projects, view and modify project files, and submit project file
changes. 

---

 Note: This documentation does not cover making changes using Git or other
 desktop tools. For editing lots of files or changing image files, you'll
 the brower based editors are very limited in functionality. Learning how to use
 Git and possibly other desktop tools will be worthwhile. To learn how to use
 Git, see GitHub's [Help](https://help.github.com/) site. 

---

Let's get started using GitHub. 

## Signup for GitHub

In order to manipulate GitHub projects and receive updates from other project
contributers, you need to become a member of GitHub.

Becoming a member of GitHub allows you to *fork* repositories to your own
account so that you can manipulate them as you wish. You'll be able to receive
committed changes from other GitHub members via *pull requests*, and you'll be
able to send your own changes as pull requests to others. 

To Signup for GitHub, go to <https://github.com> and click on *Sign up
for GitHub*. 

Now that you're signed up, you'll want to copy a repository so that you can work
with it in your account. Let's copy, or *fork*, a repository next. 

## Fork a Repository

Copying a repository to your account is known as *forking* that repository.
We'll demonstrate forking the
[liferay/liferay-docs](https://github.com/liferay/liferay-docs) repository. 

1. Navigate to the
   [liferay/liferay-docs](https://github.com/liferay/liferay-docs) repository. 

   If you know it's URL (e.g., <https://github.com/liferay/liferay-docs>), go
   straight there. 

   Otherwise, you can search for Liferay's repositories in GitHub, by entering
   `liferay` in the search field and clicking *Search*. 

   ![Here are the results from searching for `liferay`.](images/searchForLiferayInGitHub.png)

   Click on the [liferay/liferay-docs](https://github.com/liferay/liferay-docs)
   repository. 

2. Click on *Fork* to fork the repository for your GitHub user. 

   ![Here's how you fork a repository.](images/forkTheRepository.png)

Great! Now you have your own copy of the repository. 

Note, that you have a copy the the `master` branch. It's a good practice to make
changes in a branch separate from the `master` branch. 

Let's create a branch for you to work in. 

## Create a Branch for Your Work

Creating branches is easy. Let's create a branch called `my-new-feature`
by clicking on the `branch` dropdown button and entering `my-new-feature` as the
name for the new branch. 

![Here's how you create a new branch.](images/my-new-branch.png)

Now that you have a working branch, let's modify a file. 

## Modifying a File

The liferay-docs project organizes files by document (e.g., userGuide, devGuide,
etc.).

Here are some important parts of the liferay-docs project directory
structure: 

    liferay-docs/
     |____ [document dir]/ (e.g. userGuide/, devGuide/, soffice/)
     |      |____ [language dir]/ (e.g. 'en' for English, 'es' for Spanish)
     |      |      |
     |      |      |____ chapters/
     |      |      |      |____ [0-9][0-9]-[subject].markdown
     |      |      |            (e.g. 01-intro.markdown)        
     |      |      |
     |      |      |____ images/ (language specific versions of the default images)
     |      |
     |      |____ images/ (images for the document)
     |
     |____ guidelines/ (instructions on tools, Markdown conventions, and writing)
     |
     |____ README

The text files for each document are written in `.markdown` files in the
document's `chapters/` folder. The document's image files are kept separately in
the document's `images/` folder. 

Liferay's offical documents are written in Markdown. In particular, we use
the Multi-Markdown syntax variation of Markdown. To learn the Multi-Markdown
syntax, consult the cheatsheet
[here](https://rawgithub.com/fletcher/human-markdown-reference/master/index.html). 

Let's find and modify some text in the devGuide's chapter on portlets:

1. Navigate to the `chapters/` folder for the `devGuide` document from within
your branch on GitHub.

   ![Navigate to the `chapters/` folder.](images/chaptersFolder.png)

2. Click on the link to the `.markdown` file (e.g.,
`03-portlet-development.markdown`) you want to edit. 

3. Click *Edit* to bring up the editor for the file. 

   ![Edit the file.](images/editInGitHub.png)

   ![Here's what the text looks like in the editor.](images/sampleMarkdown.png)

4. Scroll down to the bottom of the screen, enter a description for your
changes, and clikc *Commit* to commit your changes to your branch.  

   ![Commit your changes.](images/myCommit.png)

Great! You've modified the file and commited those modifications to your branch. 

Now you're ready to share those changes with others, so they can merge them into
their branches, or the `liferay` user for merging the changes into an official
Liferay branch. 

Let's send a *pull request* for the changes we made to the `devGuide` chapter. 

## Sending a Pull Request

Once you're done committing changes in your branch, you can send a pull request
to Liferay. Liferay will review your changes for merging into an official
Liferay branch. 

Here's how to submit a pull request from your branch:

1. Make sure your branch is selected in the `branch` dropdown button.
2. Navigate to your branch's root folder (e.g., `liferay-docs/`). 
3. Click on *Pull Request*. 
4. Select a recipient (e.g., `liferay`) for your changes. 
4. Describe your changes in the text field. 
5. Submit your pull request. 

It's that easy to submit your changes to Liferay! 

## Summary

In this document, you've learned how to create a copy of a GitHub repository,
create a working branch of a repository, make changes to text files, and share
those changes with others using a pull requests. 

As you work more with GitHub projects you'll want to become familiar with using
Git and desktop tools these projects. Visit GitHub's
[Help](https://help.github.com/) site to learn more. 

