(ns advent-2021.day-5-test
  (:require [clojure.test :refer :all]
            [advent-2021.day-5 :as day-5]
            [advent-2021.utils :as utils]))

(defn string-to-coords
  [s]
  (let [[x1 y1 _ x2 y2] (read-string (format "[%s]"
                                             s))]
    {:point-1 {:x x1 :y y1}
     :point-2 {:x x2 :y y2}}))

(deftest day-5-test
  (let [puzzle-data (utils/input-lines "day_5.txt")]
    (testing "day 5 part 1"
      (let [line (string-to-coords "0,9 -> 5,9")]
        (is (true? (day-5/horizontal? line)))
        (is (false? (day-5/vertical? line)))
        (is (true? (day-5/horizontal-or-vertical? line))))
      (let [line (string-to-coords "2,2 -> 2,1")]
        (is (false? (day-5/horizontal? line)))
        (is (true? (day-5/vertical? line)))
        (is (true? (day-5/horizontal-or-vertical? line))))
      (let [line (string-to-coords "6,4 -> 2,0")]
        (is (false? (day-5/horizontal? line)))
        (is (false? (day-5/vertical? line)))
        (is (false? (day-5/horizontal-or-vertical? line))))
      (is (= [{:x 1 :y 1} {:x 1 :y 2} {:x 1 :y 3}]
             (day-5/points-on-line (string-to-coords "1,1 -> 1,3"))))
      (is (= [{:x 9 :y 7} {:x 8 :y 7} {:x 7 :y 7}]
             (day-5/points-on-line (string-to-coords "9,7 -> 7,7"))))
      (is (= 5 (day-5/hydrothermal-grid day-5/horizontal-or-vertical?
                                        (map string-to-coords
                                             ["0,9 -> 5,9"
                                              "8,0 -> 0,8"
                                              "9,4 -> 3,4"
                                              "2,2 -> 2,1"
                                              "7,0 -> 7,4"
                                              "6,4 -> 2,0"
                                              "0,9 -> 2,9"
                                              "3,4 -> 1,4"
                                              "0,0 -> 8,8"
                                              "5,5 -> 8,2"]))))
      (is (= 6548 (day-5/hydrothermal-grid day-5/horizontal-or-vertical?
                                           (map string-to-coords
                                                puzzle-data))))
      (is (= 12 (day-5/hydrothermal-grid (constantly true)
                                         (map string-to-coords
                                              ["0,9 -> 5,9"
                                               "8,0 -> 0,8"
                                               "9,4 -> 3,4"
                                               "2,2 -> 2,1"
                                               "7,0 -> 7,4"
                                               "6,4 -> 2,0"
                                               "0,9 -> 2,9"
                                               "3,4 -> 1,4"
                                               "0,0 -> 8,8"
                                               "5,5 -> 8,2"]))))
      (is (= 19663 (day-5/hydrothermal-grid (constantly true)
                                            (map string-to-coords
                                                 puzzle-data)))))))
