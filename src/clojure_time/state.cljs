(ns clojure-time.state
  (:require [reagent.core :refer [atom]]
            [clojure-time.config :refer [templates base-path file-path css-regex html-regex]]
            [ajax.core :refer [GET]]))

(enable-console-print!)

(defn create-regex-map [reg res]
  (reduce (fn [r [_ k v]] (if (and (not= "''" v) (not= "" v)) (assoc r k (clojure.string/replace v #"['\"]" "")) r)) {} (re-seq reg res)))

(defn update-options! [state options]
  (swap! state update-in [:options] (fn [] options)))

(defn handler! [state response]
  (let [options (merge (create-regex-map css-regex response) (create-regex-map html-regex response))]
    (update-options! state options)))

(defn switch-template! [state name]
  (GET (str base-path name file-path) {:handler (partial handler! state)})
  (aset js/window.location "hash" name))

(defn update-current! [state current f & args]
  (let [name (apply f args)]
    (switch-template! state name)
    (swap! current f)))

(defn poster [options]
  (.postMessage (.-contentWindow (.getElementById (.-document js/window) "iframer")) (.stringify js/JSON (clj->js {:type "STYLER_MODE" :payload {:options options}})) "*"))

(defn update-option! [{:keys [opts name value]}]
  (poster (swap! opts update-in [name] (fn [] value))))

;(apply swap! app-state update-in [:current] f args))

