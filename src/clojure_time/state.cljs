(ns clojure-time.state
  (:require [ajax.core :refer [GET]]))

(defn create-regex-map [reg res]
  (reduce (fn [r [_ k v]] (if (and (not= "''" v) (not= "" v)) (assoc r k (clojure.string/replace v #"['\"]" "")) r)) {} (re-seq reg res)))

;FIXME quit passin config and state to updaters
(defn update-options! [state options]
  (swap! state update-in [:options] (fn [] options)))

(defn handler! [config state response]
  (let [options (merge (create-regex-map (:css-regex config) response) (create-regex-map (:html-regex config) response))]
    (update-options! state options)))

(defn create-iframe [content]
  (-> (js/document.createElement "iframe")
      (js/Object.assign #js{:sandbox "allow-scripts"
                            :srcdoc content
                            :referrerpolicy "origin"
                            :id "iframer2"})
      (->> (.appendChild (js/document.getElementById "div-id2")))))

(defn update-iframe! [state srcdoc]
  (swap! state update-in [:srcdoc] (fn [] srcdoc)))


(defn switch-template! [config state name]
  (do (.then ((:render config) name) (partial update-iframe! state))
      (GET (str (:base-path config) name (:file-path config)) {:handler (partial handler! config state)})
      (aset js/window.location "hash" name)))

(defn update-current! [config state current f & args]
  (let [name (apply f args)]
    (switch-template! config state name)
    (swap! current f)))

(defn poster [options]
  (.postMessage (.-contentWindow (.getElementById (.-document js/window) "iframer")) (.stringify js/JSON (clj->js {:type "STYLER_MODE" :payload {:options options}})) "*"))

(defn update-option! [{:keys [opts name value]}]
  (poster (swap! opts update-in [name] (fn [] value))))

;(apply swap! app-state update-in [:current] f args))

