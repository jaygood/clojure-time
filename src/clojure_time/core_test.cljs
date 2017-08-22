(ns clojure-time.core-test
  (:require [cljs.test :refer-macros [deftest is use-fixtures async]]
            [clojure-time.core]))

(use-fixtures
  ;:each
  :once
  {:before #(async done                                     ; in case asynchronity is needed
              (println "sup yo")
              (done))
   :after #(println "bye yo")})

(deftest clojin_time
  (is (fn? js/window.clojin_time)))

(deftest async-stuff
  (async done
    (-> (js/Promise. #(% 12))
        (.then #(is (= % 12)))
        (.then #(println "promised"))
        (.then done))))
