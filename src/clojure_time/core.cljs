(ns clojure-time.core
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure-time.app :refer [app]]))

(enable-console-print!)

(println "Sup yo")

(reagent/render-component [app]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
