(ns clojure-time.switcher
  (:require [clojure-time.config :refer [templates]]
            [clojure-time.state :refer [update-current!]]))



(def button-style {:padding 2 :margin 4})
(def selected-style (merge button-style {:backgroundColor "aliceblue"}))
(def style {:textAlign "center" :margin 6})

(defn templater [{:keys [name current]}]
      [:button {:style (if (= name @current) selected-style button-style)
                :onClick #(update-current! current (fn [] name))}
       name])

(defn map-template [current template]
      [templater {:key (:name template)
                  :name (:name template)
                  :current current}])

(defn switcher [current]
      [:div {:style style} (map (partial  map-template current) templates)])
