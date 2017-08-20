(ns clojure-time.components.app
  (:require [reagent.core :as reagent :refer [cursor]]
            [clojure-time.components.options :refer [options]]
            [clojure-time.components.iframe :refer [iframe]]
            [clojure-time.components.switcher :refer [switcher]]))

(enable-console-print!)

;(aset js/window "time_dfp" #js [#(.init (.-time_dfp js/window) #js {:ix false :onebot false :resizenative true})])
;(.appendChild (.-body js/document)
;              (js/Object.assign (.createElement js/document "script") #js {:src "//tia.timeinc.net/static/jd/tia.js"}))

(defn app [config state]
  (let [opts (cursor state [:options]) current (cursor state [:current])]
       [:div
        [switcher config state current]
        [options config opts]
        [:div {:id "div-id"}
         [:div
          [iframe config current]]]]))
