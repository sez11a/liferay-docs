# Path Analytics [](id=path-analytics)

<!-- Intro needs work. I want to point out how Path Analytics can be used to
make targeted advertising decisions. Let me know if that's wrong or I should do
more in the introduction.-->
A weary sojourner winds their way through the twisted paths of the internet,
seeking answers to their important questions. Nearly ready to give up their
search and instead look at Grumpy Cat memes, they scroll to the bottom of the
page they're on, skimming the page's content.  Their weary eyes come to
rest on a promising link offered at the very bottom of the page. They click it
in desperation, hoping it offers the solution to their pressing problem.
Through this haphazard placement of an advertisement, they are led to your site,
find exactly the answers they seek, and become a regular visitor, even a
customer. If you saw this become a discernible pattern, with a certain page
consistently driving visitors to your site, you'd want to target advertising on
this page, wouldn't you? Path Analytics identify where the sojourners to your
site are most commonly coming from. Now, instead of hoping they stumble upon
your links as you leave them in the gnarled and twisted paths of the internet,
you can target your placement of links, investing where you're confident they'll
be most effective and abandoning fruitless advertising campaigns.

By knowing the Paths your page Visitors use to come to your Page, you'll make
better decisions about whether to continue, discontinue, or modify your
advertising.

## Viewing Page Path Analytics [](id=viewing-page-path-analytics)

A Page's Path Analytics are reported in the Path tab of a Page's screen. The intuitive
diagram reports the most common pages from which visitors accessed this Page.

![Figure 1: On top of being pretty to look at, the Path Diagram contains important information.](../../images/paths-diagram.png)

After the top three paths to the Page, the remaining paths are aggregated to
show how many views came from _Other_ pages.

If a Page in one of your Liferay DXP sites is also a Path to the current Page,
clicking the path URL brings you to its Overview screen. If it's not a
page that's loaded from the data source, nothing happens.

By default, all of the views of the Page in the selected time period are
represented in the Paths diagram. Filter the Paths being viewed by Location and
Device Type. Click the *Filter* menu and select one Location whose Path diagram
you'd like to see.  The Path diagram is updated to represent the top Paths for
only the selected Location.  The same filtering can be used for Device Type, and
the Device Type and Location filters can be combined. For example, view the Path
diagram for views coming from tablets in Japan. 

As you select filters, they're made visible at the top center of the Path
screen.

![Figure 2: One German smartphone User visited this Page in the last 30 days. The
User came to the Page from `wwww.google.com.de`.](../../images/paths-filters.png)

To remove a filter, click the *x* next to the filter name.

## Assets [](id=assets)

Pages have Assets on them. If a Page containing the Analytics Cloud client is
reported in the Path diagram, a *Show Top 5 Assets* link is displayed. Clicking
it opens a dropdown list. Up to the top five assets on the Page is displayed, as
determined by interaction with the Assets. Each Asset uses the most appropriate
interaction metric available:

- Forms uses Submissions
- Blogs uses Views
- Documents and Media uses Downloads
- Web Content uses Views

Clicking an asset brings you to the Asset's Overview page. See the documentation
on Assets for more information on analyzing Asset metrics <!--Link when possible-->.

![Figure 3: ](../../images/paths-assets.png)

## Location and Device Type [](id=location-and-device-type)

Path Analytics can be filtered by Location and Device Type. Knowing the Location
and Device Type visitors most commonly use to access your site's Pages helps you
determine whether your site and its content are optimized properly.  <!-- Is
there a better way to interpret these metrics? -->

Path Analytics includes analytics on the devices your Users are browsing from,
and their locations. This helps you determine which environments to target and
optimize user experiences on.

