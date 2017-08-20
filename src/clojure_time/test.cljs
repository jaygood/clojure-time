(ns clojure-time.test
  (:require [cljs.test]
            [clojure-time.core-test]
            [clojure-time.components.app-test]
            [clojure-time.components.switcher-test]
            [clojure-time.components.options-test]
            [clojure-time.components.iframe-test]))

;(defn run-tests []
;  (doall
;    (for [t '[clojure-time.core-test
;              clojure-time.components.iframe-test]]
;      (do (println "testing:" (ns-name t))
;          (cljs.test/run-tests t)))))

; FIXME why am I spellin this all out?
(defn run-tests []
  (cljs.test/run-tests 'clojure-time.core-test)
  (cljs.test/run-tests 'clojure-time.components.app-test)
  (cljs.test/run-tests 'clojure-time.components.options-test)
  (cljs.test/run-tests 'clojure-time.components.switcher-test)
  (cljs.test/run-tests 'clojure-time.components.iframe-test))
