(ns clojure-time.switcher
  (:require [clojure-time.state :refer [update-current!]]))

(def button-style {:padding 2 :margin 4})
(def selected-style (merge button-style {:backgroundColor "aliceblue"}))
(def style {:textAlign "center" :margin 6})

(defn templater [{:keys [name current state config]}]
      [:button {:style (if (= name @current) selected-style button-style)
                :onClick #(update-current! config state current (fn [] name))}
       name])

(defn map-template [state config current template]
      [templater {:key (:name template)
                  :state state
                  :config config
                  :name (:name template)
                  :current current}])

(defn switcher [config state current]
      [:div {:style style} (map (partial map-template state config current) (:templates config))])
