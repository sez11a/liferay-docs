---
header-id: maintaining-liferay
---

# Maintaining @product@

[TOC levels=1-4]

Once you have a @product@ installation, there are some things you must do to
keep it running smoothly. Backing up your installation in case of a hardware
failure protects your data and helps you get your system back in working order
quickly. And if you're a DXP customer, patching your system regularly brings the
latest bug fixes to your running instance.

| **Important:** Updating a @product@ 7.2 GA1 installation requires running 
| the data upgrade tool first, as the Portal Core on GA2 contains a minor data 
| version schema change. While on GA1,
| [run the data upgrade tool](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-product-data)
| and test your system before switching over to a server running a newer 7.2 
| GA. 

| Upgrading @product-ver@ to a new GA version can require
| [data upgrade](/docs/7-2/deploy/-/knowledge_base/d/upgrading-to-product-ver).
| Until you perform all required data upgrades (if any), @product@ startup fails
| with messages like these:
| 
| ```bash
| 2019-03-06 17:22:35.025 INFO  [main][StartupHelper:72] There are no patches installed
| You must first upgrade to @product@ 7210
| 2019-03-06 17:22:35.098 ERROR [main][MainServlet:277] java.lang.RuntimeException: You must first upgrade to @product@ 7201
| ```

Read on to learn about how to keep your system running well. 
