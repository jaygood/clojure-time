(ns clojure-time.iframe
  (:require [clojure-time.config :refer [base-path]]))

; TODO
(def style {:maxWidth 728 :maxHeight 72 :height "110vw"})

(defn iframe [path]
      [:iframe {:id "iframer" :title "iframe" :src (str base-path path "/iframe.html") :style style}])

;; connect(state => ({path: state.template.name}))

