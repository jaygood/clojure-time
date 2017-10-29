(ns clojure-time.components.iframe)

(def style {:maxWidth 728 :maxHeight 728 :height "110vw"})

(defn iframe [srcdoc]
  [:iframe {:id "iframer" :title "iframe" :srcDoc @srcdoc :style style}])
