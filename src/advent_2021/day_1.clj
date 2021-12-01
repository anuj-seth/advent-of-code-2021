(ns advent-2021.day-1)

(defn windows
  [partition-widths]
  (fn [data]
    (reduce (fn [acc width]
              (partition width
                         1 
                         acc))
            data
            partition-widths)))

(defn day-1
  [sliding-windows slice-fn depths]
  (->> depths
       ((windows sliding-windows))
       (map (fn [[d1 d2]]
              (if (< (slice-fn d1) (slice-fn d2))
                :increased
                :decreased)))
       frequencies
       :increased))
