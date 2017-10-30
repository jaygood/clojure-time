(ns clojure-time.components.options
  (:require [clojure-time.state :refer [update-option!]]))

(def style {:display "flex"})
(def span-style {:textAlign "right" :width 150 :display "inline-block" :flexShrink 0})
(def text-style {:width "100%"})

(defn option [config state opts opt]
  [:div {:style style :key opt}
   [:span {:style span-style} opt]
   [:textarea {:style text-style
               :value (get @opts opt)
               :onChange (fn [e] (update-option! config state {:opts opts :name opt :value (.-value (.-target e))}))}]])

(defn options [config state opts]
  ; TODO clojure.string/blank?
  [:div (doall (map (partial option config state opts) (keys @opts)))])
