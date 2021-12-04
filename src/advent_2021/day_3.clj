(ns advent-2021.day-3)

(defn binary-seq-to-decimal
  [s]
  (->> s
       reverse
       (map {\1 1 \0 0})
       (map (fn [exponent bit]
              (* bit (int (Math/pow 2 exponent))))
            (range))
       (apply +)))

(defn- frequent-bit
  [key-fn cmp s]
  (first (first (sort-by key-fn
                         cmp
                         (frequencies s)))))
(defn day-3
  [diagnostics]
  (let [bit-sequences (map seq diagnostics)
        gamma (->> bit-sequences
                   (apply map (fn [& s]
                                (frequent-bit val > s)))
                   (binary-seq-to-decimal))
        epsilon (->> bit-sequences
                     (apply map (fn [& s]
                                  (frequent-bit val < s)))
                     (binary-seq-to-decimal))]
    (* gamma epsilon)))

(defn support-rating
  ([s bit-criteria] (support-rating s bit-criteria 0))
  ([s bit-criteria bit-position]
   (if (= 1 (count s))
     (first s)
     (let [bits-at-position (map #(nth % bit-position)
                                 s) 
           bit (bit-criteria bits-at-position)
           remaining (filter #(= bit (nth % bit-position))
                             s)]
       (recur remaining
              bit-criteria
              (inc bit-position))))))

(defn day-3-part-2
  [diagnostics]
  (let [bit-sequences (map seq diagnostics)
        most-frequent-bit (partial frequent-bit (juxt val key) #(compare %2 %1))
        least-frequent-bit (partial frequent-bit (juxt val key) compare)]
    (->> [most-frequent-bit least-frequent-bit]
         (map #(support-rating bit-sequences %))
         (map binary-seq-to-decimal)
         (apply *))))
