(ns clojure-time.core
  (:require [reagent.core :as reagent]
            [clojure-time.config :refer [config]]
            [clojure-time.app :refer [app]]))

(aset js/window "clojin_time"
      (fn [conf]
        (reagent/render-component [app config] (.getElementById js/document "app"))))
(.clojin_time js/window)
(defn on-js-reload [])
;; optionally touch your app-state to force rerendering depending on
;; your application
;; (swap! app-state update-in [:__figwheel_counter] inc)
