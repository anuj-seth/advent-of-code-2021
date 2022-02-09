(ns advent-2021.day-9-test
  (:require [clojure.test :refer :all]
            [advent-2021.day-9 :as day-9]
            [advent-2021.utils :as utils]
            [clojure.string :as string]))

(defn- parse-data
  [lines]
  (mapv (fn [line]
         (mapv (comp read-string str)
              line))
       lines))

(deftest day-9
  (let [test-grid "2199943210
3987894921
9856789892
8767896789
9899965678"]
    (testing "day 9 part 1"
      (is (= 15 (day-9/lowest (parse-data
                               (string/split-lines test-grid)))))
      (let [grid (parse-data (utils/input-lines "day_9.txt"))]
        (day-9/lowest grid)))

    (testing "day 9 part 2"
      (is (= 15 (let [test-grid "2199943210
3987894921
9856789892
8767896789
9899965678"]
                  (second (day-9/basin (parse-data
                                        (string/split-lines test-grid)))))))
      )))










