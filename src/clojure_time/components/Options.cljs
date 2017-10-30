(ns clojure-time.components.options
  (:require [clojure-time.state :refer [update-option!]]))

(def style {:display "flex"})
(def span-style {:textAlign "right" :width 150 :display "inline-block" :flexShrink 0})
(def text-style {:width "100%"})

(defn option [config state opts [opt val]]
  [:div {:style style :key opt}
   [:span {:style span-style} opt]
   [:textarea {:style text-style
               :value val
               :onChange (fn [e] (update-option! config state {:opts opts :name opt :value (.-value (.-target e))}))}]])

(defn options [config state opts]
  [:div (doall (->> (into-array @opts)
                    (filter (fn [[_ val]] (not (clojure.string/blank? val))))
                    (map (partial option config state opts))))])
