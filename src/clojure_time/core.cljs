(ns clojure-time.core
  (:require [reagent.core :as reagent]
            [clojure-time.state :refer [switch-template!]]
            [clojure-time.components.app :refer [app]]))
(enable-console-print!)

(defn clojin-time [conf]
  (let [app-state (reagent/atom {:options {}
                                 :templates (:templates conf)
                                 :current-t (nth (:templates conf) 0) ;TODO
                                 :current (let [hash (.-hash js/location)]
                                            (if (clojure.string/blank? hash)
                                              (:TEMPLATE (nth (:templates conf) 0))
                                              (subs hash 1)))})]
    ; FIXME call in didMount or somethin
    (do (switch-template! conf app-state (:current @app-state))
        (reagent/render-component [app conf app-state] (.getElementById js/document "app")))))

(aset js/window "clojin_time" clojin-time)

(defn on-js-reload [])
;; optionally touch your app-state to force rerendering depending on
;; your application
;; (swap! app-state update-in [:__figwheel_counter] inc)
