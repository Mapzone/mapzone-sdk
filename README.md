# Developing plugins for mapzone.io

## Introduction

mapzone.io is an open and extensible platform for processing and publishing of geospatial data. It is based on the open source [Polymap4](https://github.com/Polymap4) project, which in turn is based on Eclipse and the [Equinox](http://www.eclipse.org/equinox/) plugin framework. On top of this foundation mapzone.io provides an API that allows to develop plugins and run them on the platform. Plugins can extend mapzone.io in every conceivable way, like importing new data formats, extending the style editor or adding a new sharelet. Plugins can be privat to a user or organization or they can be publicly available. Public plugins can be used by all users of mapzone.io.

## Using plugins

A user with admin privileges can choose a plugin to be used by an organization. If there is a fee for the plugin, then the admin is prompted to confirm that the monthly bill will be increased. Once activated a plugins can be used for **all projects** of an organization (or all personal projects of a user).

A plugin can be installed in one or more projects of the organization. The dashboard of the organization lists all plugins currently in use. A user can terminate using a plugin at any time.

## Providing plugins

Everybody can develop plugins for mapzone.io and run them in all projects on mapzone.io he/she has admin permissions for. If you want to provide your plugins to other users of mapzone.io, you must agree to the [Developer Agreement](DeveloperAgreement.md). Basically the agreement says the following:

  * you own your product, 
  * you support your product
  * you choose price, ..., termination
  * mapzone.io displays your product to users on your behalf
  * mapzone.io provides payment system and charges a transaction fee

A developer can provide **new versions** of the plugin at any time. The [Eclipse plugin versioning](https://wiki.eclipse.org/Version_Numbering) is used by the runtime system to ensure that the plugins are compatible to the main client version and other plugins. Project admins are informed about the update but the new version ist not installed automatically to any project.

## Pricing model, billing, termination

Just like the basic mapzone.io fee the plugin fees are billed **per members (users)** of an organization **per month**. The minimum time to use (and be billed for) a plugin is **one month**.

A user can **terminate** using a plugin at any time. The plugin is available until the end of the billing period (month). If a user needs the functions for just one month, then he/she can select and activate the plugin and deactivate right afterwards. The plugin is then billed for just one month. The plugin can be re-activated at any time.

mapzone.io charges a **transaction fee of 15%** of the plugin fee.

### Example monthly costs

In the follwoing **examples** the basic plugin fee is: **15€**

One user with just personal private projects:
```
  + mapzone.io: 9.00€ (1 x 9€ per user)
  + plugin:    15.00€ (1 x 15€ per user)
  =            24.00€
  
    15%         3.60€ transaction fee
    85%        21.40€ payed out to developer
```

The basic mapzone.io fee (9€) is not billed if there are no private projects. However, the plugin fee is billed regardless if there are private projects or not.

## Order processing

### Buyer cancellations

Buyers will have two hours to cancel after purchasing a product. After this cancellation period expires, mapzone.io automatically charges the card and initiates payments to your account as per the applicable payout schedule.

### Monthly payouts and reporting

Any orders processed, refunded, or charged-back from the first of a given month to the end of the month will get paid out around the 15th of the following month. Note that it may take several days for your bank to credit your account. To learn more about turnaround time for electronically deposited funds, please contact your financial institution.

