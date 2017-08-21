(defproject clojure-time "1.1.0"
  :description "Iframe reload"
  :url "https://github.com/jaygood/clojure-time"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [org.clojure/core.async  "0.3.442"
                  :exclusions [org.clojure/tools.reader]]
                 [reagent "0.6.0"]
                 [cljs-ajax "0.6.0"]
                 [lein-doo "0.1.6"]
                 [binaryage/devtools "0.9.4"]]


  :plugins [[lein-figwheel "0.5.10"]
            [lein-doo "0.1.6"]
            [lein-cljsbuild "1.1.5" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :cljsbuild {:builds
              [{:id "test"
                :source-paths ["src"]
                :compiler {:main clojure-time.test
                           :output-to
                                 "resources/public/js/compiled/testing_test.js"
                           :optimizations :none}}
               {:id "dev"
                :source-paths ["src"]

                :figwheel {:on-jsload "clojure-time.core/on-js-reload"
                           :open-urls ["http://localhost:3449/"]}

                :compiler {:main clojure-time.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/clojure_time.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           :source-map true
                           :preloads [devtools.preload]}}
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/clojure_time.js"
                           :main clojure-time.core
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {};; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"

  ;; Start an nREPL server into the running figwheel process
  ;; :nrepl-port 7888

  ;; Server Ring Handler (optional)
  ;; if you want to embed a ring handler into the figwheel http-kit
  ;; server, this is for simple ring servers, if this

  ;; doesn't work for you just run your own server :) (see lein-ring)

  ;; :ring-handler hello_world.server/handler

  ;; To be able to open files in your editor from the heads up display
  ;; you will need to put a script on your path.
  ;; that script will have to take a file path and a line number
  ;; ie. in  ~/bin/myfile-opener
  ;; #! /bin/sh
  ;; emacsclient -n +$2 $1
  ;;
  ;; :open-file-command "myfile-opener"

  ;; if you are using emacsclient you can just use
  ;; :open-file-command "emacsclient"

  ;; if you want to disable the REPL
  ;; :repl false

  ;; to configure a different figwheel logfile path
  ;; :server-logfile "tmp/logs/figwheel-logfile.log"

  ;; to pipe all the output to the repl
  ;; :server-logfile false


  ;; setting up nREPL for Figwheel and ClojureScript dev
  ;; Please see:
  ;; https://github.com/bhauman/lein-figwheel/wiki/Using-the-Figwheel-REPL-within-NRepl
  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.2"]
                                  [figwheel-sidecar "0.5.10"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src" "dev"]
                   ;; for CIDER
                   ;; :plugins [[cider/cider-nrepl "0.12.0"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   ;; need to add the compliled assets to the :clean-targets
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
