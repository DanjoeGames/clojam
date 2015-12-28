(ns gatherer.renderer)

(def spriteSize 225)
(def tileSize 27)

(def sprites {"tree" {:x 0 :y 0}
              "logPile" {:x 1 :y 0}
              "grass1" {:x 0 :y 1}
              "grass2" {:x 1 :y 1}
              "grass3" {:x 2 :y 1}
              "ch" {:x 2 :y 0}})

(def spriteIndex {0 "grass1"
                  1 "logPile"
                  2 "tree"})

(def context (.getContext (.getElementById js/document "canvas")
  "2d"))

(def spritesheet (.getElementById js/document "spritesheet"))

(defn render_cell [cell x y] 
  (let [sprite (sprites (spriteIndex cell))]
    (.drawImage context spritesheet 
                (* (sprite :x) spriteSize) (* (sprite :y) spriteSize)
                spriteSize spriteSize
                (* x tileSize) (* y tileSize)
                tileSize tileSize)))

(defn render [gamestate]
  (let [worldMap (@gamestate :map) 
        chsx ((sprites "ch") :x) 
        chsy ((sprites "ch") :y)
        chdx ((@gamestate :ch) :x)
        chdy ((@gamestate :ch) :y)]
    (doall (map-indexed (fn [y row]
      (doall (map-indexed (fn [x cell] (render_cell cell x y)) row))) worldMap))
    (.drawImage context spritesheet 
        (* chsx spriteSize) (* chsy spriteSize) 
        spriteSize spriteSize 
        (* chdx tileSize) (* chdy tileSize)
        tileSize tileSize)))

