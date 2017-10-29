(ns clojure-time.components.app
  (:require [reagent.core :as reagent :refer [cursor]]
            [clojure-time.components.options :refer [options]]
            [clojure-time.components.iframe :refer [iframe]]
            [clojure-time.components.switcher :refer [switcher]]))

(defn app [config state]
  (let [opts (cursor state [:options])
        srcdoc (cursor state [:srcdoc])
        current (cursor state [:current])]
    [:div
     [switcher config state current]
     [options config opts]
     [:div {:id "div-id"}
      [:div
       [iframe srcdoc]]]]))
