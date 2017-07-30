(ns clojure-time.state
  (:require [reagent.core :refer [atom]]
            [clojure-time.config :refer [templates base-path css-regex html-regex]]
            [ajax.core :refer [GET]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {
                          :options {}
                          :templates templates
                          :current (:name (nth templates 0))}))

(defn create-regex-map [reg res]
  (reduce (fn [r [_ k v]] (if (and (not= "''" v) (not= "" v)) (assoc r k (clojure.string/replace v #"['\"]" "")) r)) {} (re-seq reg res)))

(defn update-options! [options]
  (swap! app-state update-in [:options] (fn [] options)))

(defn handler! [response]
  (let [options (merge (create-regex-map css-regex response) (create-regex-map html-regex response))]
    (.dir js/console (clj->js options))
    (update-options! options)))

(defn switch-template! [name]
  (GET (str base-path name "/iframe.html") {:handler handler!})
  (aset js/window.location "hash" name))

(defn update-current! [f & args]
  (let [name (apply f args)]
    (switch-template! name)
    (apply swap! app-state update-in [:current] f args)))

(defn poster [options]
  (.postMessage (.-contentWindow (.-iframer (.-frames js/window))) (.stringify js/JSON (clj->js {:type "STYLER_MODE" :payload {:options options}})) "*"))

(defn update-option! [{:keys [name value]}]
  (poster (:options (swap! app-state update-in [:options] (fn [options] (assoc options name value))))))

;(apply swap! app-state update-in [:current] f args))

; FIXME call in didMount or somethin
(switch-template! (:current @app-state))
