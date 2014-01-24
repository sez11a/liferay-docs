# Working With liferay-docs From Within Your Browser

If you've needed to edit or review one of the Liferay's official documents and
you'd like to do so from the browser, you're in luck! You can work with the
[liferay-docs](https://github.com/liferay/liferay-docs) project, to view files,
modify files, and submit modifications, from within your browser. 

The following documentation teaches you how to get your GitHub account, get
copies of projects, view and modify project files, and submit modified files. 

---

 ![Note](images/tip.png) **Note:** This documentation does not cover making
 changes using Git, Git related desktop tools, or Markdown editors. For making
 lots of edits or complex editing, the browser based editor is very limited in
 functionality. In addition, deleting or replacing image files requires Git.
 Learning how to use Git, Git related desktop tools, and a Markdown editor will
 be worthwhile. To learn how to use Git, see GitHub's
 [Help](https://help.github.com/) site. 

---

Let's get started using the liferay-docs GitHub project in the browser. 

## Sign Up for GitHub

In order to manipulate the liferay-docs project and receive updates from
contributors, you need to become a member of GitHub. 

Becoming a member of GitHub allows you to *fork* repositories to your own
account so that you can manipulate them as you wish. You'll be able to receive
committed changes from other GitHub members via *pull requests*, and you'll be
able to send your own changes as pull requests to others. 

To sign up for GitHub, go to <https://github.com> and click on *Sign up
for GitHub*. 

Now that you're signed up, you'll want to copy the liferay-docs repository, so
that you can work with it in your account. Let's copy, or *fork*, the
liferay-docs repository next. 

## Fork a Repository

Copying a repository to your account is known as *forking* that repository.
We'll demonstrate forking the
[liferay/liferay-docs](https://github.com/liferay/liferay-docs) repository. 

1. Navigate to the
   [liferay/liferay-docs](https://github.com/liferay/liferay-docs) repository. 

   You can go straight to its URL (e.g.,
   <https://github.com/liferay/liferay-docs>). 

   If you didn't know its location, you could search for Liferay's repositories
   in GitHub, by entering `liferay` in the search field and clicking *Search*. 

   ![Here are the results from searching for `liferay`.](images/searchForLiferayInGitHub.png)

2. Click on *Fork* to copy the repository to your GitHub user's account. 

   ![Here's how you fork a repository.](images/forkTheRepository.png)

Great! Now you have your own copy of the liferay-docs repository. 

Note, that you have a copy the `master` branch. It's a good practice to make
changes in a branch separate from the `master` branch. 

Let's create a branch for you to work in. 

## Create a Branch for Your Work

Creating branches is easy. Let's create a branch called `my-new-feature`
by clicking on the *branch* dropdown button and entering `my-new-feature` as the
name for the new branch. 

![Here's how you create a new branch.](images/my-new-branch.png)

Now that you have a working branch, let's modify a file. 

## Modifying a File

The liferay-docs project organizes files by document (e.g., `userGuide`,
`devGuide`, etc.).

Here's an abbreviated version of the liferay-docs project directory structure: 

    liferay-docs/
     |____ [document dir]/ (e.g. userGuide/, devGuide/, soffice/)
     |      |____ en
     |      |      |
     |      |      |____ chapters/
     |      |      |      |____ [0-9][0-9]-[subject].markdown
     |      |      |            (e.g., 01-intro.markdown)        
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

Liferay's offical documents are written the Multi-Markdown syntax variation of
Markdown. To learn the Multi-Markdown syntax, consult this
[syntax cheatsheet](https://rawgithub.com/fletcher/human-markdown-reference/master/index.html). 

Let's find and modify some text in the devGuide's portlets chapter:

1. Navigate to the `chapters/` folder for the `devGuide` document, from within
your working branch.

   ![Navigate to the `chapters/` folder.](images/chaptersFolder.png)

2. Click on the link of the `.markdown` file (e.g.,
`03-portlet-development.markdown`) you want to edit. 

3. Click *Edit* to bring up the editor for the file. 

   ![Edit the file.](images/editInGitHub.png)

4. Modify the file's Markdown text. Remember to consult this [syntax
cheatsheet](https://rawgithub.com/fletcher/human-markdown-reference/master/index.html)
for guidance. 

   Toggle between *Code* and *Preview* views, to make sure your changes look
   just right. 

   ![Toggle between Code and Preview views.](images/fileInEditor.png)

5. To *commit* your edits, scroll down to the bottom of the page, enter a
description for your changes, and click *Commit Changes*. 

   ![Commit your changes.](images/myCommit.png)

Great! You've modified the file and committed those modifications to your
branch. 

Now you're ready to share those changes with others, so they can merge them into
their branches. To submit your changes for review by Liferay, you'll send them
to the `liferay` user. 

Let's send a *pull request* for the changes we made to the portlets `devGuide`
chapter. 

## Sending a Pull Request

Once you're done committing changes in your branch, you can send a pull request
to Liferay. We'll review your changes for merging into an official Liferay
branch. 

Here's how to submit a pull request from your branch:

1. Make sure your branch is selected in the `branch` dropdown button.

2. Navigate to your branch's root folder (i.e., `liferay-docs/`). 

3. Click on *Pull Request*. 

   ![Click Pull Request](images/clickPullRequest.png)

5. Select a recipient (e.g., `liferay`) for your changes. 

6. Describe your changes in the text field. 

7. Click on *Send Pull Request*. 

   ![Send Pull Request.](images/sendPullRequest.png)

It's that easy to submit your changes to Liferay! 

## Summary

In this document, you've learned how to create a copy of a GitHub repository,
create a working branch of a repository, make changes to text files, and share
those changes with others using pull requests. 

As you work more with GitHub projects you'll want to become familiar with using
Git and Git related desktop tools. Visit GitHub's
[Help](https://help.github.com/) site to learn more. 

