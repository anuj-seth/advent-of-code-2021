(ns advent-2021.day-3-test
  (:require [clojure.test :refer :all]
            [advent-2021.day-3 :as day-3]
            [advent-2021.utils :as utils]))

(deftest day-3-test
  (let [puzzle-data (utils/input-lines "day_3.txt")]
    (testing "day 3 part 1"
      (is (= 198 (day-3/day-3 ["00100"
                               "11110"
                               "10110"
                               "10111"
                               "10101"
                               "01111"
                               "00111"
                               "11100"
                               "10000"
                               "11001"
                               "00010"
                               "01010"])))
      (is (= 4006064 (day-3/day-3 puzzle-data))))
    (testing "day 3 part 2"
      (is (= 230 (day-3/day-3-part-2 ["00100"
                                      "11110"
                                      "10110"
                                      "10111"
                                      "10101"
                                      "01111"
                                      "00111"
                                      "11100"
                                      "10000"
                                      "11001"
                                      "00010"
                                      "01010"])))
      (is (= 5941884 (day-3/day-3-part-2 puzzle-data))))))
