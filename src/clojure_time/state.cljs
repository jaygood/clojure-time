(ns clojure-time.state
  (:require [ajax.core :refer [GET]]
            [reagent.core :as reagent :refer [cursor]])
  (:import [goog.async Debouncer]))

;FIXME quit passin config and state to updaters
(defn update-iframe! [state srcdoc]
  (swap! state update-in [:srcdoc] (fn [] srcdoc)))

(defn render-template! [config state name props]
  (.then ((:render config) name props) (partial update-iframe! state)))

(def debounced-render-template! (Debouncer. render-template! 200))

(defn switch-template! [config state name]
  (render-template! config state name {})
  (aset js/window.location "hash" name))

(defn get-template [templates name]
  (first (filter (fn [{:keys [TEMPLATE]}] (= TEMPLATE name)) templates)))

(defn update-current! [config state current f & args]
  (let [name (apply f args)
        current-t (cursor state [:current-t])]
    (switch-template! config state name)
    (swap! current f)
    (swap! current-t #(get-template (:templates @state) name)))) ; TODO

(defn update-option! [config state {:keys [opts name value]}]
  (let [options (swap! opts update-in [name] (fn [] value))]
    (.fire debounced-render-template! config state (:current @state) options)))

;(apply swap! app-state update-in [:current] f args))

