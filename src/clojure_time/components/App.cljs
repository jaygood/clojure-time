(ns clojure-time.app
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure-time.state :refer [app-state]]
            [clojure-time.config :refer [templates]]
            [clojure-time.options :refer [options]]
            [clojure-time.iframe :refer [iframe]]
            [clojure-time.switcher :refer [switcher]]))

(enable-console-print!)

(aset js/window "time_dfp" #js [#(.init (.-time_dfp js/window) #js {:ix false :onebot false :resizenative true})])
(.appendChild (.-body js/document)
              (js/Object.assign (.createElement js/document "script") #js {:src "//tia.timeinc.net/static/jd/tia.js"}))

(defn app []
  (let [opts (:options @app-state) current (:current @app-state)]
       [:div
        [switcher current]
        [options opts opts]
        [:div {:id "div-id"}
         [:div
          [iframe current]]]]))
