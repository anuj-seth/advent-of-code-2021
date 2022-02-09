(ns advent-2021.day-7)

(defn align
  [cost-fn positions]
  (->> (range (inc (apply max positions)))
       (map (fn [p]
              [p (map #(Math/abs (- p %))
                      positions)]))
       (map (fn [[k v]]
              [k (cost-fn v)]))
       (into {})
       (sort-by val)
       first
       second))
