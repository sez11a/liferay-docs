---
header-id: patching-liferay
---

# Patching @product@

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

While we strive for perfection with every @product@ release, the reality of the
human condition dictates that releases may not be as perfect as originally
intended. But we've planned for that. Included with every @product@ bundle is a
Patching Tool that handles installing two types of patches: fix packs and
hotfixes. 

| **Important:** Make sure to
| [back up your @product@ installation and database](/docs/7-0/deploy/-/knowledge_base/d/backing-up-a-liferay-installation)
| regularly, especially before patching. The patching tool installs code changes
| and some of these make data changes (if necessary) automatically on startup.
| 
| Certain fix packs (service packs) can include data/schema micro
| changes---they're optional and revertible. Module upgrades are applied at server
| startup by default, or can be applied manually by
| [disabling the `autoUpgrade` property](/docs/7-0/deploy/-/knowledge_base/d/running-the-upgrade-process#configuring-non-core-module-upgrades).
| You can apply any upgrades using the
| [upgrade tool](/docs/7-0/deploy/-/knowledge_base/d/upgrading-to-liferay-7)
| before server startup.

| **Note:**
| [Patching a cluster](/docs/7-0/deploy/-/knowledge_base/d/updating-a-cluster)
| requires additional considerations.
