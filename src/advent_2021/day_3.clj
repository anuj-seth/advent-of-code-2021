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

(defn day-3
  [diagnostics]
  (let [bit-frequencies (->> diagnostics
                             (map seq)
                             (apply map (comp frequencies vector)))
        gamma (->> bit-frequencies
                   (map #(first (sort-by val > %)))
                   (map first)
                   (binary-seq-to-decimal))
        epsilon (->> bit-frequencies
                     (map #(first (sort-by val < %)))
                     (map first)
                     (binary-seq-to-decimal))]
    (* gamma epsilon)))

(defn- frequencies-at-position
  [s n]
  (frequencies (map #(nth % n )
                    s)))

(defn- frequency-at-position
  [cmp s n]
  (first (first (sort-by (juxt val key)
                         cmp
                         (frequencies-at-position s n)))))

(defn- max-frequency-at-position
  [s n]
  (frequency-at-position #(compare %2 %1)
                         s
                         n))

(defn- min-frequency-at-position
  [s n]
  (frequency-at-position compare
                         s
                         n))

(defn support-rating
  ([s bit-criteria] (support-rating s bit-criteria 0))
  ([s bit-criteria bit-position]
   (if (= 1 (count s))
     (first s)
     (let [bit (bit-criteria s
                             bit-position)
           remaining (filter #(= bit (nth % bit-position))
                             s)]
       (recur remaining
              bit-criteria
              (inc bit-position))))))

(defn day-3-part-2
  [diagnostics]
  (let [bit-sequences (map seq diagnostics)]
    (->> [max-frequency-at-position min-frequency-at-position]
         (map #(support-rating bit-sequences %))
         (map binary-seq-to-decimal)
         (apply *))))
