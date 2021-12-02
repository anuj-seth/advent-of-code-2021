(ns advent-2021.day-2
  (:require [clojure.string :as string]))

(defn- read-instructions
  [instructions]
  (map (fn [instruction]
         (let [[direction distance] (string/split instruction
                                                  #" ")]
           [direction (Integer/parseInt distance)]))
       instructions))

(defn day-2
  [instructions]
  (let [final-coords (reduce (fn [[x y] [direction distance]]
                               (let [[delta-x delta-y] (condp = direction
                                                         "forward" [distance 0]
                                                         "down" [0 distance]
                                                         "up" [0 (- distance)])]
                                 [(+ x delta-x) (+ y delta-y)]))
                             [0 0]
                             (read-instructions instructions))]
    (apply * final-coords)))

(defn day-2-part-2
  [instructions]
  (let [final-coords (reduce (fn [[x y aim] [direction distance]]
                               (let [[delta-x delta-y delta-aim] (condp = direction
                                                                   "forward" [distance (* aim distance) 0]
                                                                   "down" [0 0 distance]
                                                                   "up" [0 0 (- distance)])]
                                 [(+ x delta-x) (+ y delta-y) (+ aim delta-aim)]))
                             [0 0 0]
                             (read-instructions instructions))]
    (apply * (take 2 final-coords))))
