(ns advent-2021.day-7-test
  (:require [clojure.test :refer :all]
            [advent-2021.day-7 :as day-7]
            [advent-2021.utils :as utils]))

(deftest day-7-test
  (let [puzzle-data (->> (utils/input-lines "day_7.txt")
                         first
                         (format "[%s]")
                         read-string)]
    (testing "day 7 part 1"
      (is (= 37
             (day-7/align #(apply + %)
                          [16 1 2 0 4 2 7 1 2 14])))
      (is (= 344535
             (day-7/align #(apply + %)
                          puzzle-data))))
    (testing "day 7 part 2"
      (is (= 168
             (day-7/align (fn [distances]
                            (apply +
                                   (mapcat #(range (inc %))
                                           distances)))
                          [16 1 2 0 4 2 7 1 2 14])))
      (is (= 95581659
             (day-7/align (fn [distances]
                            (apply +
                                   (mapcat #(range (inc %))
                                           distances)))
                          puzzle-data))))))
