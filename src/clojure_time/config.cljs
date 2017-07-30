(ns clojure-time.config
      (:require [reagent.core :refer [atom]]))
(enable-console-print!)
(def dev-mode true)
(def base-path "https://tia.timeinc.net/static/jd/")
(def css-regex #"\\/\\*\\s\"?'?\\\\?\\[%(.*)%]\"?'?\\s\\*\\/\\s([^;]*)")
(def html-regex #"<!--\s\\?\[%(.*)%]\s-->\s([^<]*)")
(def templates [
                {:name "adapt-video"}
                {:name "adapt-video-native"}
                {:name "adapt-image-native"}
                {:name "adapt-video-native-logo"}
                {:name "adapt-image-native-logo"}
                {:name "adapt-video-native-nogo"}
                {:name "adapt-image-native-nogo"}
                {:name "adapt-zoom"}
                ])
(println (:name (nth templates 0)))
;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {
                          :text "Hello you"
                          :templates templates
                          :current (:name (nth templates 0))}))

(defn update-current! [f & args]
      (apply swap! app-state update-in [:current] f args))

(println (str "okay" "yo" (:current app-state)))
