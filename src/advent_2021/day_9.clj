(ns advent-2021.day-9)

(defn- possible-neighbouring-indexes
  [row-idx col-idx]
  (remove (fn [[r c]]
            (or (neg? r)
                (neg? c)))
          (map (fn [[delta-row-idx delta-col-idx]]
                 [(+ row-idx delta-row-idx) (+ col-idx delta-col-idx)])
               [[0 1] [0 -1]
                [1 0] [-1 0]
                ;; [1 1] [-1 -1]
                ;; [1 -1] [-1 1]
                ])))

(defn lowest
  [grid]
  (let [grid-enriched (for [[row-idx row] (map-indexed vector grid)
                            [col-idx col] (map-indexed vector row)]
                        {:row-idx row-idx
                         :col-idx col-idx
                         :value (get-in grid [row-idx col-idx])
                         :neighbour-indexes (possible-neighbouring-indexes row-idx col-idx)
                         :neighbour-values (remove nil?
                                                   (map #(get-in grid %)
                                                        (possible-neighbouring-indexes row-idx col-idx)))})]
    (apply +
           (map (comp inc :value)
                (filter (fn [{:keys [value neighbour-values]}]
                          (every? #(< value %)
                                  neighbour-values))
                        grid-enriched)))))

(defn neighbours
  [grid row-idx col-idx]
  (remove (comp nil? :value)
          (map (fn [[r-idx c-idx]]
                 {:row-idx r-idx
                  :col-idx c-idx
                  :value (get-in grid [r-idx c-idx])})
               (possible-neighbouring-indexes row-idx
                                              col-idx))))

(defn one-lower-neighbours
  [grid row-idx col-idx]
  (let [value (get-in grid
                      [row-idx col-idx])
        lower-neighbours (filter #(= (:value %) (dec value))
                                 (neighbours grid
                                             row-idx
                                             col-idx))]
    {:row-idx row-idx
     :col-idx col-idx
     :value value
     :one-lower-neighbours (map (fn [{:keys [row-idx col-idx value]}]
                                  (one-lower-neighbours grid
                                                        row-idx
                                                        col-idx))
                                lower-neighbours)}))
(defn basin-size
  [{:keys [one-lower-neighbours] :as point}]
  (println point (select-keys point
                              [:row-idx :col-idx :value]))
  (if (not (seq one-lower-neighbours))
    nil
    (cons (select-keys point
                       [:row-idx :col-idx :value])
          (basin-size one-lower-neighbours))))

(defn basin-sizes
  [{:keys [one-lower-neighbours] :as point}]
  (if (not (seq one-lower-neighbours))
    nil
    (for [lower-neighbour one-lower-neighbours]
      (cons 1
         (basin-sizes lower-neighbour)))))

(comment 
  (tree-seq map?
            :one-lower-neighbours
            {:row-idx 0,
             :col-idx 5,
             :value 4,
             :one-lower-neighbours '({:row-idx 0,
                                      :col-idx 6,
                                      :value 3,
                                      :one-lower-neighbours ({:row-idx 0,
                                                              :col-idx 7,
                                                              :value 2,
                                                              :one-lower-neighbours ({:row-idx 0,
                                                                                      :col-idx 8,
                                                                                      :value 1,
                                                                                      :one-lower-neighbours ({:row-idx 0,
                                                                                                              :col-idx 9,
                                                                                                              :value 0,
                                                                                                              :one-lower-neighbours ()})})})})})

    (tree-seq map?
              :one-lower-neighbours
              {:row-idx 0,
               :col-idx 5,
               :value 4,
               :one-lower-neighbours ()})

  
  )

(defn basin
  [grid]
  (let [grid-enriched (for [[row-idx row] (map-indexed vector grid)
                            [col-idx col] (map-indexed vector row)
                            :when (not= 9 (get-in grid
                                                  [row-idx col-idx]))]
                        (one-lower-neighbours grid
                                              row-idx
                                              col-idx))]
    (->> grid-enriched
         (filter (comp seq :one-lower-neighbours))
         
         )))
