(ns clojure-time.config
  (:require [reagent.core :refer [atom]]))

(def base-path "https://tia.timeinc.net/static/jd/")
(def file-path "/iframe.html")
(def css-regex #"/\*\s\"?'?\\?\[%(.*)%]\"?'?\s\*/\s([^;]*)")
(def html-regex #"<!--\s\\?\[%(.*)%]\s-->\s([^<]*)")
(def templates [
                {:name "adapt-video"}
                {:name "adapt-video-native"}
                {:name "adapt-image-native"}
                {:name "adapt-video-native-logo"}
                {:name "adapt-image-native-logo"}
                {:name "adapt-video-native-nogo"}
                {:name "adapt-image-native-nogo"}
                {:name "adapt-zoom"}])

(def config #js {:base-path base-path :file-path file-path :css-regex css-regex :html-regex html-regex :templates templates})
