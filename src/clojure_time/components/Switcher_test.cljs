(ns clojure-time.components.switcher-test
  (:require [cljs.test :refer-macros [is deftest]]
            [clojure-time.components.switcher :refer [switcher]]))

(deftest switcher-test
  (let [[elem] (switcher {:templates ["test"]} (atom {}) (atom "test"))]
    (is (= :div elem))))
