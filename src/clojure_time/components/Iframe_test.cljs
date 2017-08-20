(ns clojure-time.components.iframe-test
  (:require [cljs.test :refer-macros [is deftest]]
            [clojure-time.components.iframe :refer [iframe]]))

(deftest iframe-test
  (let [[elem config] (iframe {:file-path "/iframe.html" :base-path "test/"} (atom "test"))]
    (is (= :iframe elem))
    (is (= "test/test/iframe.html" (:src config)))))
