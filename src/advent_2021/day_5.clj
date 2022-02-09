(ns advent-2021.day-5)

(defn vertical?
  [line]
  (= (get-in line [:point-1 :x])
     (get-in line [:point-2 :x])))

(defn horizontal?
  [line]
  (= (get-in line [:point-1 :y])
     (get-in line [:point-2 :y])))

(defn horizontal-or-vertical?
  [line]
  (or (horizontal? line)
      (vertical? line)))

(defn points-on-axis
  [start end]
  (let [slope (- end start)
        f (cond
            (pos? slope) inc
            (neg? slope) dec
            (zero? slope) identity)]
    (iterate f start)))

(defn points-on-line
  [{:keys [point-1 point-2]}]
  (let [{x-1 :x y-1 :y} point-1
        {x-2 :x y-2 :y} point-2
        points-on-x-axis (points-on-axis x-1 x-2)
        points-on-y-axis (points-on-axis y-1 y-2)
        points-to-take (max (Math/abs (- x-1 x-2))
                            (Math/abs (- y-1 y-2)))]
    (take (inc points-to-take)
          (map (fn [x y]
                 {:x x :y y})
               points-on-x-axis
               points-on-y-axis))))

(defn hydrothermal-grid
  [filter-fn lines]
  (->> lines
       (filter filter-fn)
       (mapcat points-on-line)
       (frequencies)
       (filter #(>= (val %) 2))
       (count)))
