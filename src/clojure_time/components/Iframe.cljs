(ns clojure-time.iframe
  (:require [clojure-time.config :refer [base-path]]))

(def style {:maxWidth 728 :maxHeight 728 :height "110vw"})

(defn iframe [path]
      [:iframe {:id "iframer" :title "iframe" :src (str base-path path "/iframe.html") :style style}])
