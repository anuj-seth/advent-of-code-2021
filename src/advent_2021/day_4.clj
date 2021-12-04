(ns advent-2021.day-4)

(defn score
  [board last-drawn]
  (* last-drawn
     (apply +
            (filter int?
                    (flatten board)))))

(defn any-fully-marked?
  [v-of-v]
  (boolean (some (fn [v]
                   (every? #(not (int? %))
                           v))
                 v-of-v)))

(defn row-marked?
  [board]
  (any-fully-marked? board))

(defn column-marked?
  [board]
  (any-fully-marked? (apply map
                            vector
                            board)))

(defn winning-board
  [boards]
  (let [board (first boards)]
    (cond
      (nil? board) nil
      (or (row-marked? board)
          (column-marked? board)) board
      :else (recur (rest boards)))))

(defn mark-boards
  [boards number]
  (for [board boards]
    (map #(replace {number :*}
                   %)
         board)))

(defn bingo
  ([numbers-drawn boards] (bingo numbers-drawn
                                 boards
                                 nil))
  ([numbers-drawn boards last-drawn]
   (if-let [winner (winning-board boards)]
     (score winner last-drawn)
     (recur (rest numbers-drawn)
            (mark-boards boards
                         (first numbers-drawn))
            (first numbers-drawn)))))

(defn remove-winning-boards
  [boards]
  (if-let [winner (winning-board boards)]
    (recur (remove #(= winner %)
                   boards))
    boards))

(defn bingo-loser
  ([numbers-drawn boards] (bingo-loser numbers-drawn
                                       boards
                                       nil))
  ([numbers-drawn boards last-drawn]
   (if (and (= 1 (count boards))
            (winning-board boards))
     (score (first boards)
            last-drawn)
     (let [remaining-boards (remove-winning-boards boards)]
       (recur (rest numbers-drawn)
              (mark-boards remaining-boards
                           (first numbers-drawn))
              (first numbers-drawn))))))

