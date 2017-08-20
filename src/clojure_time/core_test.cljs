(ns clojure-time.core-test
  (:require [cljs.test :refer-macros [deftest is]]))

(deftest clojin_time
  (is (fn? js/window.clojin_time)))

