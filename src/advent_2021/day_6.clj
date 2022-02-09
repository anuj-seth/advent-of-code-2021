(ns advent-2021.day-6)

(defn next-iteration
  [shoal-frequencies]
  (let [new-fish (if-let [eight-days-cnt (shoal-frequencies 0)]
                   {8 eight-days-cnt}
                   {})
        new-shoal (map (fn [[days cnt]]
                         {({-1 6} days days) cnt})
                       (map (fn [[days cnt]]
                              [(dec days) cnt])
                            shoal-frequencies))]
    (apply merge-with
           +
           (cons new-fish new-shoal))))

(defn lantern-fish
  [n shoal]
  (nth (iterate next-iteration
                (frequencies shoal))
       n))
