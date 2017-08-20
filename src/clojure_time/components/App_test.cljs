(ns clojure-time.components.app-test
  (:require [cljs.test :refer-macros [is deftest]]
            [reagent.core :as reagent]
            [clojure-time.components.app :refer [app]]))

(deftest app-test
  (let [[elem & childs] (app {:templates [{:name "test"}]} (reagent/atom {:options [] :templates [] :current "test"}))]
    (is (= :div elem))
    (is (= 3 (count childs)))))
