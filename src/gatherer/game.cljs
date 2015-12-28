(ns gatherer.game
  (:require [gatherer.renderer :as renderer]))

(def gamestate (atom {:map
                      [[2 2 2 2 2 2 2 2 2 2 2 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [1 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 1 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 1 0 0 0 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 2 2 2 2 2 2 2 2 2 2 2]]
                      :ch {:x 3 :y 3}
                      }))

(defn tic [] 
  (renderer/render gamestate) 
  (.setTimeout js/window tic (/ 1000 60)))


