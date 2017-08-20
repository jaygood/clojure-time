# clojure-time

Project

## Overview

Reload iframes

## Setup

    lein do clean, cljsbuild once min
    figwheel
        (require 'cljs.test)
        (require 'clojure-time.core-test)
        (cljs.test/run-tests 'clojure-time.core-test)
    