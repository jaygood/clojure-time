(ns clojure-time.app
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure-time.config :refer [app-state templates]]
            [clojure-time.iframe :refer [iframe]]
            [clojure-time.switcher :refer [switcher]]))

(enable-console-print!)

(defn app []
  [:div {:class "container"}
   [switcher (:current @app-state)]
   [iframe (:current @app-state)]
   [:h1 (:text @app-state)]])
