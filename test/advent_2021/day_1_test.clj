(ns advent-2021.day-1-test
  (:require [clojure.test :refer :all]
            [advent-2021.day-1 :as day-1]
            [advent-2021.utils :as utils]))


(deftest day-1-test
  (let [puzzle-data (map #(Integer/parseInt %)
                         (utils/input-lines "day_1.txt"))]
    (testing "day 1 part 1"
      (let [sliding-windows [2]
            pre-comparison-fn identity]
        (is (= 7 (day-1/day-1 sliding-windows
                              pre-comparison-fn
                              [199 200 208 210 200 207 240 269 260 263])))
        (is (= 1446 (day-1/day-1 sliding-windows
                                 pre-comparison-fn
                                 puzzle-data)))))

    (testing "day 1 part 2"
      (let [sliding-windows [3 2]
            pre-comparison-fn #(apply + %)]
        (is (= 5 (day-1/day-1 sliding-windows
                              pre-comparison-fn
                              [199 200 208 210 200 207 240 269 260 263])))
        (is (= 1486 (day-1/day-1 sliding-windows
                                 pre-comparison-fn
                                 puzzle-data)))))))

