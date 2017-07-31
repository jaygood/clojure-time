(ns clojure-time.options
  (:require [clojure-time.state :refer [update-option!]]))

(def style {:display "flex"})
(def span-style {:textAlign "right" :width 150 :display "inline-block" :flexShrink 0})
(def text-style {:width "100%"})

(defn option [opts opt]
      [:div {:style style :key opt}
       [:span {:style span-style} opt]
       [:textarea {:style text-style
                   :value (get @opts opt)
                   :onChange (fn [e] (update-option! {:opts opts :name opt :value (.-value (.-target e))}))}]])

(defn options [opts]
      [:div (map (partial option opts) (keys @opts))])
