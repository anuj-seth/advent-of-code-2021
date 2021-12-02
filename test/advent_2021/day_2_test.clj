(ns advent-2021.day-2-test
  (:require [clojure.test :refer :all]
            [advent-2021.day-2 :as day-2]
            [advent-2021.utils :as utils]))

(deftest day-2-test
  (let [puzzle-data (utils/input-lines "day_2.txt")]
    (testing "day 2 part 1"
      (is (= 150 (day-2/day-2 ["forward 5"
                               "down 5"
                               "forward 8"
                               "up 3"
                               "down 8"
                               "forward 2"])))
      (is (= 2150351 (day-2/day-2 puzzle-data))))
    (testing "day 2 part 2"
      (is (= 900 (day-2/day-2-part-2 ["forward 5"
                                      "down 5"
                                      "forward 8"
                                      "up 3"
                                      "down 8"
                                      "forward 2"])))
      (is (= 1842742223 (day-2/day-2-part-2 puzzle-data))))))
