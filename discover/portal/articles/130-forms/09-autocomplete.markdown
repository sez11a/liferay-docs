# Autocompleting Text Fields

It's been scientifically proved that Internet users are lazy (not you or
I--other Internet users). Citizens of some countries might not fill out your
form if you make them type the entire title of their country in an employment
application. This is doubly true if they're filling out the form on their mobile
device. In this article you'll learn to make users' lives easier by configuring
autocomplete on a form's text fields.

Why not just use a select field with a data provider to guide user input?
Sometimes a data provider can't encompass all possible field entries. If your
data provider doesn't include 
[mythical countries founded on old sea platforms](https://en.wikipedia.org/wiki/Principality_of_Sealand),
for example, users from that country wouldn't be able to enter anything into the
select field. An alternative to this is using a text field with the autocomplete
feature so they can begin typing their country's name, and select it from a list
if it appears. Using a text field with autocomplete gives you the flexibility of
a text field (accepting any response that meets your validation criteria) while
giving users common choices to select from. It's a win-win situation.

## Configuring Autocomplete

Before configuring autocomplete for your text fields, 
[create a form and add a text field](/discover/portal/-/knowledge_base/7-1/creating-and-managing-forms). 
If you want the autocomplete options to be populated by a REST data provider,
[configure one](/discover/portal/-/knowledge_base/7-1/data-providers)
before creating your form. Now you're ready to configure autocomplete for the
field:

1. In the field configuration sidebar, click the *Properties* tab.

2. Click the *Autocomplete* switcher so it's enabled.

3. Now select a data provider or create one manually.

    -  You can set up a data provider from a 
        [REST service](/discover/portal/-/knowledge_base/7-1/data-providers).

    -  Alternatively, manually enter the options users should see when they
        start typing in the text field.

4. Save and Publish the form.

![Figure x: You can configure a manual data provider to specify the options
users can select from.](../../../images/forms-autocomplete-manually.png)

Once users begin entering text into the field, a selection list of options
appears. As they enter additional text, the list is refined to include only
options that contain the text currently entered. For example, the imaginary
users from Sealand (all two of them) begin reluctantly typing their country of
origin by entering an *S*. They're delighted to see a selection list with a
bunch of countries containing the letter *S* appear for their selection
convenience.  If they continue typing and enter *e*, the list is refined and
they now only see options that have *se* in their name (for example *Se*rbia and
*Se*negal). If they continue typing and enter *a* they'll now only see one
option, Sealand, if it's in your data provider. Selecting it from the list after
typing the first three letters is much easier than typing the remaining letters.

![Figure x: When typing in a field with autocomplete, users are presented a list of selections from the configured data provider. The displayed results are filtered to include only selections containing the text entered by the user.](../../../images/forms-autocomplete-filtering.png)

What will the Forms team think of next? Configuring telepathic connections to
the Forms application would be nice. Then users could just think their form
field entries into existence. Stay tuned.

