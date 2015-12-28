(ns gatherer.core
  (:require [clojure.browser.repl :as repl]
            [gatherer.game :as game]))

;; (defonce conn
;;   (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

(println "Hello world!")

(game/tic)
