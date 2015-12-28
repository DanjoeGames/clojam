(ns gatherer.game
  (:require [gatherer.renderer :as renderer]))

(def gamestate (atom {:map
                      [[2 2 2 2 2 2 2 2 2 2 2 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
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

(defn genMoveCrossHair [x y]
  (fn [state] (let [chx ((state :ch) :x) chy ((state :ch) :y)]
                (assoc state :ch {:x (+ x chx) :y (+ y chy)}))))

(defn tic [] 
  (renderer/render gamestate) 
  (.setTimeout js/window tic (/ 1000 60)))

(defn handleKeyDown [event] 
  (println [(.-keyCode event) "DOWN"]))

(defn handleKeyUp [event] 
  (swap! gamestate (genMoveCrossHair 1 0)))

(defn init []
(set! (.-onkeydown js/window) handleKeyDown)
(set! (.-onkeyup js/window) handleKeyUp))


