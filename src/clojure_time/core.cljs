(ns clojure-time.core
  (:require [reagent.core :as reagent]
            [clojure-time.app :refer [app]]))

(reagent/render-component [app]
                          (.getElementById js/document "app"))

(defn on-js-reload [])
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)

