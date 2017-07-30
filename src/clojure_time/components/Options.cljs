(ns clojure-time.options
  (:require [clojure-time.state :refer [update-option!]]))

(def style {:display "flex"})
(def span-style {:textAlign "right" :width 150 :display "inline-block" :flexShrink 0})
(def text-style {:width "100%"})

(defn option [defaultOpts opts opt]
      [:div {:style style :key opt}
       [:span {:style span-style} opt]
       [:textarea {:style text-style
                   :value (get opts opt (get defaultOpts opt))
                   :onChange (fn [e] (update-option! {:name opt :value (.-value (.-target e))}))}]])

(defn options [defaultOpts opts]
      [:div (map (partial option defaultOpts opts) (keys opts))])
