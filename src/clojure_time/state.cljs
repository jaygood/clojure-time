(ns clojure-time.state
  (:require [ajax.core :refer [GET]]))

;FIXME quit passin config and state to updaters
(defn update-iframe! [state srcdoc]
  (swap! state update-in [:srcdoc] (fn [] srcdoc)))

(defn render-template! [config state name props]
  (.then ((:render config) name props) (partial update-iframe! state)))

(defn switch-template! [config state name]
  (render-template! config state name {})
  (aset js/window.location "hash" name))

(defn update-current! [config state current f & args]
  (let [name (apply f args)]
    (switch-template! config state name)
    (swap! current f)))

(defn update-option! [config state {:keys [opts name value]}]
  (println "opts" name opts)
  (let [options (swap! opts update-in [name] (fn [] value))]
    (render-template! config state (:current @state) options)))

;(apply swap! app-state update-in [:current] f args))

