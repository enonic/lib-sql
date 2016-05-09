SQL lib for Enonic XP
=====================

[![Build Status](https://travis-ci.org/enonic/lib-sql.svg?branch=master)](https://travis-ci.org/enonic/lib-sql)
[![License](https://img.shields.io/github/license/enonic/lib-sql.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Coverage Status](https://coveralls.io/repos/github/enonic/lib-sql/badge.svg?branch=master)](https://coveralls.io/github/enonic/lib-sql?branch=master)

This is a library for accessing SQL databases from Enonic XP. It uses plain JDBC drivers under the hood and can connect to
any database which there's a suitable driver.


Usage
-----

First, you will need to add Enonic repository to the repository list:

    repositories {
        maven {
            url 'http://repo.enonic.com/public'
        }
    }

After this, add the following dependency (where ``<version>`` is the actual version to use):

    dependencies {
        include 'com.enonic.lib:lib-sql:<version>'
    }

The last step is to include the actual JDBC driver in your app. This is done by adding it as an ``include`` dependency. Here's
an example on how to include the postgresql JDBC driver (it's perfectly legal to add multiple JDBC drivers to your app):

    dependencies {
        include 'org.postgresql:postgresql:9.4.1208'
    }

