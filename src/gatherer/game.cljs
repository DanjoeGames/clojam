(ns gatherer.game
  (:require [gatherer.renderer :as renderer]))

(def gamestate (atom {:map
                      [[2 2 2 2 2 2 2 2 2 2 2 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 0 3 3 3 0 2]
                       [2 0 0 0 1 0 0 3 0 3 0 2]
                       [2 0 0 0 0 0 0 3 3 3 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 1 0 0 0 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 0 0 0 0 0 0 0 0 0 0 2]
                       [2 2 2 2 2 2 2 2 2 2 2 2]]
                      :ch {:x 3 :y 3}
                      :gnomes []
                      }))

(defn newGnome [x y] ({:x x :y x}))

(defn genAddGnome [x y] (let [gnomes (@gamestate :gnomes)]
  (fn [state] assoc state :gnomes (conj gnomes (newGnome x y)))))

(defn genMoveCrossHair [x y]
  (fn [state] (let [chx ((state :ch) :x) chy ((state :ch) :y)]
                (assoc state :ch {:x (+ x chx) :y (+ y chy)}))))

(defn moveUp [] (swap! gamestate (genMoveCrossHair 0 -1)))
(defn moveDown [] (swap! gamestate (genMoveCrossHair 0 1)))
(defn moveLeft [] (swap! gamestate (genMoveCrossHair -1 0)))
(defn moveRight [] (swap! gamestate (genMoveCrossHair 1 0)))

(def keymap {
             87 moveUp
             65 moveLeft
             83 moveDown
             68 moveRight
  })

(defn tic [] 
  (renderer/render gamestate) 
  (.setTimeout js/window tic (/ 1000 60)))

(defn handleKeyDown [event] 
  ((keymap (.-keyCode event))))

(defn handleKeyUp [event] ) 
  ;((keymap (.-keyCode event))))

(defn init []
(set! (.-onkeydown js/window) handleKeyDown)
(set! (.-onkeyup js/window) handleKeyUp))

