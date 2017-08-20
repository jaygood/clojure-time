(ns clojure-time.components.iframe)

(def style {:maxWidth 728 :maxHeight 728 :height "110vw"})

(defn iframe [config current]
  [:iframe {:id "iframer" :title "iframe" :src (str (:base-path config) @current (:file-path config)) :style style}])
