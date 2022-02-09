(ns advent-2021.day-8)

(def digit-segments {0 #{:a :b :c :e :f :g}
                     1 #{:c :f}
                     2 #{:a :c :d :e :g}
                     3 #{:a :c :d :f :g}
                     4 #{:b :c :d :f}
                     5 #{:a :b :d :f :g}
                     6 #{:a :b :d :e :f :g}
                     7 #{:a :c :f}
                     8 #{:a :b :c :d :e :f :g}
                     9 #{:a :b :c :d :f :g}})

(defn crossed-wires
  [segments]
  (let [crossed {:d :a
                 :e :b
                 :a :c
                 :f :d
                 :g :e
                 :b :f
                 :c :g}]
    (set (map (comp crossed keyword str)
              segments))))


(defn segment-count-matching
  [f data]
  (filter f
          (map count data)))

(defn output-matching-digit-count
  [data digits]
  (let [digit-segment-counts (into #{}
                                   (map count
                                        (map val
                                             (select-keys digit-segments
                                                          digits))))]
    (count (segment-count-matching digit-segment-counts
                                   (mapcat :output data)))))

(def encoding {"467889" 0, "89" 1, "47788" 2, "77889" 3, "6789" 4,
               "67789" 5, "467789" 6, "889" 7, "4677889" 8, "677889" 9,
               0 "467889", 1 "89", 2 "47788", 3 "77889", 4 "6789",
               5 "67789", 6 "467789", 7 "889", 8 "4677889", 9 "677889"})

(defn decoder [segments]
  (let [cfs (frequencies (apply str segments))]
    (into {}
          (map (fn [s]
                 [(sort s) (get encoding (->> (map #(get cfs %) s)
                                              sort
                                              (apply str)))])
               segments))))

(defn count-uniques [[input output]]
  (let [d (decoder input)
        nums (map #(get d (sort %)) output)]
    (count (filter #(or (= % 1) (= % 4) (= % 7) (= % 8))
                   nums))))

(defn output-value [[input output]]
  (let [d (decoder input)]
    (Integer. (apply str (map #(get d (sort %)) output)))))


