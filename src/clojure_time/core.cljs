(ns clojure-time.core
  (:require [reagent.core :as reagent]
            [clojure-time.state :refer [switch-template!]]
            [clojure-time.app :refer [app]]))

(defn clojin-time [c] (let [conf (js->clj c :keywordize-keys true)
                            app-state (reagent/atom {:options {}
                                                     :templates (:templates conf)
                                                     :current (:name (nth (:templates conf) 0))})]
                        ; FIXME call in didMount or somethin
                        (switch-template! conf app-state (:current @app-state))
                        (reagent/render-component [app conf app-state] (.getElementById js/document "app"))))

(aset js/window "clojin_time" clojin-time)

(defn on-js-reload [])
;; optionally touch your app-state to force rerendering depending on
;; your application
;; (swap! app-state update-in [:__figwheel_counter] inc)
